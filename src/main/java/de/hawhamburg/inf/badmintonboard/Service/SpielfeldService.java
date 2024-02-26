/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Service;

import de.hawhamburg.inf.badmintonboard.Models.Spielfeld;
import de.hawhamburg.inf.badmintonboard.Repository.SpielfeldRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daria Zhdanova
 */
@Service
public class SpielfeldService {
    private final SpielfeldRepository spielfeldRepository;
    private final SpielerService spielerService;

    @Autowired
    public SpielfeldService(SpielfeldRepository spielfeldRepository, SpielerService spielerService) {
        this.spielfeldRepository = spielfeldRepository;
        this.spielerService = spielerService;
    }
    
    public List<Spielfeld> getSpielfeld(){
        return this.spielfeldRepository.findAll();
    }
    
    public void save(Spielfeld spielfeld) {
        spielfeldRepository.save(spielfeld);
    }    
    
    public Spielfeld getBySpielId(Long spielId) {
        return spielfeldRepository.findBySpielId(spielId).get();
    }
    
    public Spielfeld getBySpielfeldId(Long id) {
        Spielfeld spielfeld = spielfeldRepository.findById(id).get();
        spielfeld.setAufschlagender(spielerService.getSpielerById(spielfeld.getAufschlagenderId()).get());
        spielfeld.setObenLinkerSpieler(spielerService.getSpielerById(spielfeld.getObenLinkerSpielerId()).get());
        spielfeld.setObenRechterSpieler(spielerService.getSpielerById(spielfeld.getObenRechterSpielerId()).get());
        spielfeld.setUntenLinkerSpieler(spielerService.getSpielerById(spielfeld.getUntenLinkerSpielerId()).get());
        spielfeld.setUntenRechterSpieler(spielerService.getSpielerById(spielfeld.getUntenRechterSpielerId()).get());
        
        return spielfeld;
    }
}
