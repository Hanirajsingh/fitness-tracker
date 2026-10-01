package com.project.fitwith.Service;

import com.project.fitwith.Entity.Activity;
import com.project.fitwith.Entity.User;
import com.project.fitwith.Repository.ActivityRepository;
import com.project.fitwith.Repository.UserRepository;
import com.project.fitwith.dto.ActivityRequest;
import com.project.fitwith.dto.ActivityResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private ActivityRepository activityRepository;
    private UserRepository userRepository;

    public ActivityService (ActivityRepository activityRepository,UserRepository userRepository){
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    public ActivityResponse createActivity (ActivityRequest request){
        User user = userRepository.findById
                (request.getUserId()).
                orElseThrow( () -> new RuntimeException( "User Not Found" + request.getUserId()));

        Activity activity = new Activity();
        activity.setUser(user);
        activity.setType(request.getType());
        activity.setDuration(request.getDuration());
        activity.setCaloriesburned(request.getCaloriesburned());
        activity.setStartTime(request.getStartTime());

        Activity savedActivity = activityRepository.save(activity);

        return mapToResponse(savedActivity);
    }

    public List<ActivityResponse> getactivity ( String userId){
        List<Activity> activitylist = activityRepository.findByuserId(userId);

        return activitylist.stream()
                .map(this:: mapToResponse)
                .collect(Collectors.toList());
    }
      private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();

        response.setId(activity.getId());
        response.setUserId(activity.getId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesburned(activity.getCaloriesburned());
        response.setStartTime(activity.getStartTime());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
         return  response;
      }
}

