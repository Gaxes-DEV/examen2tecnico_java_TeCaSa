package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.repo.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopServiceImpl implements WorkshopService{

    @Autowired
    WorkshopRepository repo;

    @Override
    public void saveWorkshop(Workshop workshop) { repo.save(workshop); }

    @Override
    public List<Workshop> getAllWorkshops() {
        return repo.findAll();
    }

    @Override
    public Optional<Workshop> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteWorkshop(Long id) {
        repo.deleteById(id);
    }
}
