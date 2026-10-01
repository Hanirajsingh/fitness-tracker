package com.project.fitwith.Service;

import com.project.fitwith.Entity.Activity;
import com.project.fitwith.Entity.Recommendation;
import com.project.fitwith.Entity.User;
import com.project.fitwith.Repository.ActivityRepository;
import com.project.fitwith.Repository.RecommendationRepository;
import com.project.fitwith.Repository.UserRepository;
import com.project.fitwith.dto.ActivityResponse;
import com.project.fitwith.dto.RecommendationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private UserRepository userRepository;
    private ActivityRepository activityRepository;
    private RecommendationRepository recommendationRepository;

    public RecommendationService(UserRepository userRepository,
                                 ActivityRepository activityRepository,
                                  RecommendationRepository recommendationRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.recommendationRepository= recommendationRepository;
    }

    public Recommendation generateRecommendatiion(RecommendationRequest request) {

        User user = userRepository.findById(request.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"+request.getUserid()));

        Activity activity = activityRepository.findById(request.getActivityId())
                .orElseThrow( () -> new RuntimeException("Activity not found" + request.getActivityId()));

        Recommendation recommendation =Recommendation.builder().
                user(user)
                .activity(activity)
                .recommendation(request.getRecommendation())
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return savedRecommendation;
    }
    //get recommendation in service class
    public List<Recommendation> getRecommendation(String UserId){
        List<Recommendation> recommendationList = recommendationRepository.findByuserId(UserId);
         return recommendationList;
    }

    public  List<Recommendation> getActivityRecommendation(String activityId){
        List<Recommendation> activityrecommendationList = recommendationRepository.findByactivityId(activityId);
        return  activityrecommendationList;
    }

}
