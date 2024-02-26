/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard;

import de.hawhamburg.inf.badmintonboard.Models.Team;
import de.hawhamburg.inf.badmintonboard.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author diiia
 */
@Controller
@RequestMapping("/team")
public class TeamController {
    
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
    
    @GetMapping("")
    public String toTeams(){
        return "/team";
    }
    
    @GetMapping("/new_team")
    public String toAddTeam(){
        return "/new_team";
    }
    
    @GetMapping("/teamList/addTeam")
    public String addTeam(@RequestParam(value="teamname", required=false) String teamname, @RequestParam(value="spieler1", required=false) int spieler1Id, @RequestParam(value="spieler2", required=false) int spieler2Id, Model model) {
        Team team = new Team(teamname, Long.valueOf(spieler1Id), Long.valueOf(spieler2Id));
        teamService.save(team);
        model.addAttribute("teamname",teamname);
        model.addAttribute("spieler1",spieler1Id);
        model.addAttribute("spieler2",spieler2Id);
        return "redirect:/team/teamList";
    }
    
    @GetMapping("/teamList")
    public String getTeamList(Model model) {
         model.addAttribute("teams",teamService.getTeams());
         model.addAttribute("spielern",teamService.getSpielerList());
        
            //result += team.getTeamName()+ ": " + team.getSpieler1().getId() + " " + team.getSpieler1().getVorname()+ " " + team.getSpieler1().getName()
                    //+ ", " + team.getSpieler2().getId() + " "  + team.getSpieler2().getVorname() + " " +team.getSpieler2().getName() + "<br/>";
        
        return "/teamList";
    }      
}
