/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Service;

import de.hawhamburg.inf.badmintonboard.Models.Spieler;
import de.hawhamburg.inf.badmintonboard.Repository.SpielerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daria Zhdanova
 */
@Service
public class SpielerService {
    private final SpielerRepository spielerRepository;

    @Autowired
    public SpielerService(SpielerRepository spielerRepository) {
        this.spielerRepository = spielerRepository;
    }
    
    public List<Spieler> getSpieler(){
        return this.spielerRepository.findAll();
    }
    
    public void save(Spieler spieler) {
        spielerRepository.save(spieler);
    }
    
    public Optional<Spieler> getSpielerById(Long id) {
        return spielerRepository.findById(id);
    }
}
