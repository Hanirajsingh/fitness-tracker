package com.project.fitwith.Repository;

import com.project.fitwith.Entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation , String> {

    List<Recommendation> findByuserId(String UserId);
    List<Recommendation> findByactivityId(String activityId);
}
