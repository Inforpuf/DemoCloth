package com.example.demoCloth.repositories;

import com.example.demoCloth.model.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface that queries the embedded database.
 */
public interface ClothRepository extends JpaRepository<Price, Long> {
}
