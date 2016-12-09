package com.epages.experiment.availability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean // making this a real bean breaks everything
public interface AvailabilityRepository extends JpaRepository<Availability, UUID> {
}
