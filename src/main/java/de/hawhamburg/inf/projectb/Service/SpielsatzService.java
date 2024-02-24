/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.projectb.Service;

import de.hawhamburg.inf.projectb.Models.Spielsatz;
import de.hawhamburg.inf.projectb.Repository.SpielsatzRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daria Zhdanova
 */
@Service
public class SpielsatzService {
    private final SpielsatzRepository spielsatzRepository;
    private final TeamService teamService;

    @Autowired
    public SpielsatzService(SpielsatzRepository spielsatzRepository, TeamService teamService) {
        this.spielsatzRepository = spielsatzRepository;
        this.teamService = teamService;
    }
    
    public List<Spielsatz> getSpielsatz(){
        return this.spielsatzRepository.findAll();
    }
    
    public void save(Spielsatz spielsatz) {
        spielsatzRepository.save(spielsatz);
    }
    
    public List<Spielsatz> getSpielsatzBySpielId(Long spielId){
        return this.spielsatzRepository.findAllBySpielId(spielId);
    }
    
    public Spielsatz getSpielsatzById(Long id) {
        Spielsatz spielsatz = this.spielsatzRepository.findById(id).get();
        if (spielsatz.getGewinnerId() != 0) {
            spielsatz.setGewinner(teamService.getTeamById(spielsatz.getGewinnerId()));
        }
        return spielsatz;       
    }
}
