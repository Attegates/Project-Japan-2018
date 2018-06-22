/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.repository;

import com.atteg.MeasurementPersistJPA.model.Measurement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Atte
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(value = "SELECT * FROM measurement WHERE user_id = (SELECT id FROM user WHERE username = :username)", nativeQuery = true)
    List<Measurement> findByUsername(@Param("username") String username);
    
}
