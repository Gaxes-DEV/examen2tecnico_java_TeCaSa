package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Activity;
import com.cenfotec.tecasa.repo.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceLmpl implements ActivityService{
    @Autowired
    ActivityRepository repo;

    @Override
    public void saveActivity(Activity activity){ repo.save(activity); }

    @Override
    public List<Activity> getAllActivities(){ return repo.findAll(); }

    @Override
    public Optional<Activity> findById(Long id){ return repo.findById(id); }

    @Override
    public void deleteActivity(Long id){ repo.deleteById(id); }
}
