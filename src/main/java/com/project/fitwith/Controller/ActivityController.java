package com.project.fitwith.Controller;


import com.project.fitwith.Service.ActivityService;
import com.project.fitwith.dto.ActivityRequest;
import com.project.fitwith.dto.ActivityResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController (ActivityService activityService){
        this.activityService = activityService;
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityResponse> activiy(@RequestBody ActivityRequest activityRequest) {

        ActivityResponse response = activityService.createActivity(activityRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getactivity/{userId}")
    public ResponseEntity<List<ActivityResponse>> get(@PathVariable String userId){
        List<ActivityResponse> responseList = activityService.getactivity(userId);
        return ResponseEntity.ok(responseList);
    }

}
