package com.project.fitwith.dto;
import com.project.fitwith.Entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRequest {

    private String userId;
    private ActivityType type;

    private Integer duration;
    private Integer caloriesburned;
    private LocalDateTime startTime;

}
