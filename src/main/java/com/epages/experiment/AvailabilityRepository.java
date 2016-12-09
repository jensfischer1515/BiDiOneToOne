package com.epages.experiment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvailabilityRepository extends JpaRepository<Product, UUID> {
}
