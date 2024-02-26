/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Repository;

import de.hawhamburg.inf.badmintonboard.Models.Spielfeld;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daria Zhdanova
 */
@Repository
public interface SpielfeldRepository extends JpaRepository<Spielfeld, Long>{

    /**
     *
     * @param spielId
     * @return
     */
    Optional<Spielfeld> findBySpielId(Long spielId);
}
