package com.project.fitwith.dto;

import com.project.fitwith.Entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {

    private String userid;

    private String activityId;

    private String recommendation;

    private List<String> improvements;

    private List<String> suggestions;

    private List<String> safety;


}
