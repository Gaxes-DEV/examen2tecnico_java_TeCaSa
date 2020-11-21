package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    public void saveActivity (Activity activity);
    public List<Activity> getAllActivities();
    public Optional<Activity> findById(Long id);
    public void deleteActivity(Long id);
}
