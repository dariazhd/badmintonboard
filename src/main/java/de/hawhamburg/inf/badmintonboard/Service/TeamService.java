/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Service;

import de.hawhamburg.inf.badmintonboard.Models.Spieler;
import de.hawhamburg.inf.badmintonboard.Models.Team;
import de.hawhamburg.inf.badmintonboard.Repository.TeamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daria Zhdanova
 */
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final SpielerService spielerService;
    
    @Autowired
    public TeamService(TeamRepository teamRepository, SpielerService spielerService) {
        this.teamRepository = teamRepository;
        this.spielerService = spielerService;
    }
    
    public List<Team> getTeamNames() {
        return this.teamRepository.findAll();
    }
    
    public List<Team> getTeams() {
        List<Team> teams = this.teamRepository.findAll();
        for (Team team : teams) {
            Spieler spieler1 = spielerService.getSpielerById(team.getSpieler1Id()).get();
            Spieler spieler2 = spielerService.getSpielerById(team.getSpieler2Id()).get();
            team.setSpieler1(spieler1);
            team.setSpieler2(spieler2);
        }
        return teams;
    }
    
    public void save(Team team) {
        teamRepository.save(team);
    }
    
    public Team getTeamById(Long id) {
        Team team = teamRepository.findById(id).get();
        team.setSpieler1(spielerService.getSpielerById(team.getSpieler1Id()).get());
        team.setSpieler2(spielerService.getSpielerById(team.getSpieler2Id()).get());
        return team;
    }
    
    public List<Spieler> getSpielerList() {
        return spielerService.getSpieler();
    }
}
