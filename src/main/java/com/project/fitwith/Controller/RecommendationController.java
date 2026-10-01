package com.project.fitwith.Controller;

import com.project.fitwith.Entity.Recommendation;
import com.project.fitwith.Entity.User;
import com.project.fitwith.Service.RecommendationService;
import com.project.fitwith.dto.ActivityResponse;
import com.project.fitwith.dto.RecommendationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService =  recommendationService;
    }
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestBody RecommendationRequest request){

        Recommendation recommendation = recommendationService.generateRecommendatiion(request);
        return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/getrecommendation/{UserId}")
    public ResponseEntity<List<Recommendation>> getRecommendation(@PathVariable String UserId ){
        List<Recommendation> recommendationList = recommendationService.getRecommendation(UserId);
        return ResponseEntity.ok(recommendationList);

    }
    @GetMapping("/getactivity/{activityid}")

    public ResponseEntity<List<Recommendation>> getActivity(@PathVariable String activityid){
        List<Recommendation> recommendationList = recommendationService.getActivityRecommendation(activityid);
        return ResponseEntity.ok(recommendationList);
    }
}
