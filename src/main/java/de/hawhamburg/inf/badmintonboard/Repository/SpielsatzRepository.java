/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Repository;

import de.hawhamburg.inf.badmintonboard.Models.Spielsatz;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daria Zhdanova
 */
@Repository
public interface SpielsatzRepository extends JpaRepository<Spielsatz, Long> {
    List<Spielsatz> findAllBySpielId(Long spielId);
    
}
