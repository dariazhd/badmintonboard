/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.hawhamburg.inf.projectb.Repository;

import de.hawhamburg.inf.projectb.Models.Spiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daria Zhdanova
 */
@Repository
public interface SpielRepository extends JpaRepository<Spiel, Long>{
    
}
