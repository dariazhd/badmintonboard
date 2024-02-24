/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.projectb;

import de.hawhamburg.inf.projectb.Models.Spieler;
import de.hawhamburg.inf.projectb.Service.SpielerService;
import de.hawhamburg.inf.projectb.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author diiia
 */
@Controller
@RequestMapping("/start")
public class SpielerController {
    
    private final SpielerService spielerService;
    private final TeamService teamService;

    @Autowired
    public SpielerController(SpielerService spielerService, TeamService teamService) {
        this.spielerService = spielerService;
        this.teamService = teamService;
    }
    
    @GetMapping("")
    public String toSpieler(Model model) {
        model.addAttribute("teams",teamService.getTeams());
        return "/start";
    }
    
    
    @GetMapping("/new_spieler")
    public String toAddSpieler(){
        return "/new_spieler";
    }
    @GetMapping("new_spieler/addSpieler")
    public String addSpieler(@RequestParam(value="name", required=false) String name, @RequestParam(value="vorname",required=false) String vorname, Model model) {
        Spieler spieler = new Spieler(name, vorname);
        ProjectbApplication.spielerList.add(spieler);
        spielerService.save(spieler);
        model.addAttribute("name",name);
        model.addAttribute("vorname",vorname);
        //"Spieler " + spieler.getId() + " " + spieler.getVorname() + " " + spieler.getName() + " hingefügt"
        return "redirect:/start/spielerList";
    }
    
    @GetMapping("/spielerList")
    public String getSpielerList(Model model) {
            model.addAttribute("spielern",spielerService.getSpieler());
            //result += spieler.getId() + " " + spieler.getVorname() + " " + spieler.getName() + "<br/>";
        return "/spielerList";
    }    
}
