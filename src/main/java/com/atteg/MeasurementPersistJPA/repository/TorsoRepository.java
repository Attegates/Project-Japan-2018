/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.repository;

import com.atteg.MeasurementPersistJPA.model.Torso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Atte
 */
@Repository
public interface TorsoRepository extends JpaRepository<Torso, Long> {
    
}
