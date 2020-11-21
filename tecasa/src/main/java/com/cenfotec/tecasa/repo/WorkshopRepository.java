package com.cenfotec.tecasa.repo;

import com.cenfotec.tecasa.domain.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopRepository extends JpaRepository<Workshop, Long> {
}
