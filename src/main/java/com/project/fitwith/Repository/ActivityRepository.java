package com.project.fitwith.Repository;

import com.project.fitwith.Entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity , String> {

    List<Activity> findByuserId(String userId);
}
