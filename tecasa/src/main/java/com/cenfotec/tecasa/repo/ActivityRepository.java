package com.cenfotec.tecasa.repo;

import com.cenfotec.tecasa.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
