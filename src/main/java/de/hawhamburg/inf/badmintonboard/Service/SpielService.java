/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Service;

import de.hawhamburg.inf.badmintonboard.Models.Spiel;
import de.hawhamburg.inf.badmintonboard.Models.Spieler;
import de.hawhamburg.inf.badmintonboard.Models.Spielfeld;
import de.hawhamburg.inf.badmintonboard.Models.Spielsatz;
import de.hawhamburg.inf.badmintonboard.Models.Team;
import de.hawhamburg.inf.badmintonboard.Repository.SpielRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daria Zhdanova
 */
@Service
public class SpielService {
    private final SpielRepository spielRepository;
    private final TeamService teamService;
    private final SpielerService spielerService;
    private final SpielfeldService spielfeldService;
    private final SpielsatzService spielsatzService;

    @Autowired
    public SpielService(SpielRepository spielRepository, TeamService teamService, SpielerService spielerService, 
            SpielfeldService spielfeldService, SpielsatzService spielsatzService) {
        this.spielRepository = spielRepository;
        this.teamService = teamService;
        this.spielerService = spielerService;
        this.spielfeldService = spielfeldService;
        this.spielsatzService = spielsatzService;
    }
    
    public List<Spiel> getSpiele() {
        return this.spielRepository.findAll();
    }
    
    public Spiel save(Spiel spiel) {        
        spielRepository.save(spiel);
        List<Spiel> s = getSpiele();
        if (!s.isEmpty()) {
            return s.get(s.size() - 1);
        }
        return null;
    }
    
    public void saveSpielsatz(Spiel spiel) {
        spielsatzService.save(spiel.getSpielsatz1());
        spielsatzService.save(spiel.getSpielsatz2());
        spielsatzService.save(spiel.getSpielsatz3());
    }
    
    public Team getTeamByTeamName(String teamName) {
        for (Team team : teamService.getTeams()) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }
    
    public Team getTeamByTeamId(int id) {       
        return teamService.getTeamById(Long.valueOf(id));
    }
    
    public Spieler getSpielerById(int id) {
        return spielerService.getSpielerById(Long.valueOf(id)).get();
    }
    
    public Spiel getSpiel(int id){
        Spiel spiel = this.spielRepository.findById(Long.valueOf(id)).get();
        
        if (spiel.getTeam1Id() != null) {
            spiel.setTeam1(teamService.getTeamById(spiel.getTeam1Id()));
            spiel.setTeam2(teamService.getTeamById(spiel.getTeam2Id()));        
            spiel.setErstaufschlagende(teamService.getTeamById(spiel.getErstaufschlagendeId()));
        }
        
        if (spiel.getSpielfeldId() != null) {
            spiel.setSpielfeld(spielfeldService.getBySpielfeldId(spiel.getSpielfeldId()));
        }
        
        if (spiel.getSpielsatz1Id() != null) {
            spiel.setSpielsatz1(spielsatzService.getSpielsatzById(spiel.getSpielsatz1Id()));
            spiel.setSpielsatz2(spielsatzService.getSpielsatzById(spiel.getSpielsatz2Id()));
            spiel.setSpielsatz3(spielsatzService.getSpielsatzById(spiel.getSpielsatz3Id()));
        }
        return spiel;        
    }
    
    public Spielfeld saveSpielfeld(Spielfeld spielfeld) {
        spielfeldService.save(spielfeld);
        return spielfeldService.getBySpielId(spielfeld.getSpielId());
    }
    
    public Spiel createAndSaveSpielsatz(Spiel spiel) {
        if (spielsatzService.getSpielsatzBySpielId(spiel.getId()).isEmpty()) {
            for (int i = 0; i < 3; i++) {
                Spielsatz spielsatz = new Spielsatz(0, 0, 0L, spiel.getId());
                spielsatzService.save(spielsatz);            
            }
        }
        
        List<Spielsatz> spielsaetze = spielsatzService.getSpielsatzBySpielId(spiel.getId());
        
        spiel.setSpielsatz1(spielsaetze.get(0));
        spiel.setSpielsatz2(spielsaetze.get(1));
        spiel.setSpielsatz3(spielsaetze.get(2));
        
        spiel.setSpielsatz1Id(spielsaetze.get(0).getId());
        spiel.setSpielsatz2Id(spielsaetze.get(1).getId());
        spiel.setSpielsatz3Id(spielsaetze.get(2).getId());
        
        return spiel;        
    }
}
