package com.cenfotec.tecasa.service;

import com.cenfotec.tecasa.domain.Workshop;

import java.util.List;
import java.util.Optional;

public interface WorkshopService {
    public void saveWorkshop (Workshop workshop);
    public List<Workshop> getAllWorkshops();
    public Optional<Workshop> findById(Long id);
    public void deleteWorkshop(Long id);
}
