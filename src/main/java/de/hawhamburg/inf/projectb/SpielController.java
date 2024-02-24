/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.projectb;

import de.hawhamburg.inf.projectb.Models.Spiel;
import de.hawhamburg.inf.projectb.Models.Spieler;
import de.hawhamburg.inf.projectb.Models.Spielfeld;
import de.hawhamburg.inf.projectb.Models.Spielsatz;
import de.hawhamburg.inf.projectb.Models.Team;
import de.hawhamburg.inf.projectb.Service.SpielService;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Daria Zhdanova
 */
@Controller
@RequestMapping("/spiel")
public class SpielController {    
    public static final HashMap<Long, LinkedList<String>> nachrichten = new HashMap<>();    
    private final SpielService spielService;

    @Autowired
    public SpielController(SpielService spielService) {
        this.spielService = spielService;
    }
    
    @GetMapping("")
    public String toSpiel(){
        return "/spiel";
    }
    
    @GetMapping("/spielfeld")
    public String toAddSpielfeld(@RequestParam(value="spiel", required=false) int spiel, Model model) {
        Spiel sp = spielService.getSpiel(spiel);
        List<Spieler> spieler1team = new LinkedList<>();
        List<Spieler> spieler2team = new LinkedList<>();
        spieler1team.add(sp.getTeam1().getSpieler1());
        spieler1team.add(sp.getTeam1().getSpieler2());
        spieler2team.add(sp.getTeam2().getSpieler1());
        spieler2team.add(sp.getTeam2().getSpieler2());
        
        List<Spieler> erstaufSpielern = new LinkedList<>();
        erstaufSpielern.add(sp.getErstaufschlagende().getSpieler1());
        erstaufSpielern.add(sp.getErstaufschlagende().getSpieler2());
        
        model.addAttribute("spieler1team",spieler1team);
        model.addAttribute("spieler2team",spieler2team);
        model.addAttribute("erstauf",erstaufSpielern);
        model.addAttribute("spiel",spiel);
        return "/spielfeld";
    }
    
    @GetMapping("/addSpiel")
    public String addSpiel(@RequestParam(value="t1", required=false) int t1, @RequestParam(value="t2", required=false) int t2, @RequestParam(value="erstauf", required=false) int erstauf, Model model) {        
        
        Spiel sp = new Spiel();
        sp.setTeam1Id(Long.valueOf(t1));
        sp.setTeam2Id(Long.valueOf(t2));
        sp.setErstaufschlagendeId(Long.valueOf(erstauf));
        sp = spielService.save(sp);
        return "redirect:/spiel/spielfeld?spiel=" + sp.getId();
    }
    
    @GetMapping("/list")
    public String getList(@RequestParam("spielId") int spielId, Model model) {
        Spiel spiel = spielService.getSpiel(spielId);
        String result = "";
        model.addAttribute("team1",spiel.getTeam1().getTeamName());
        model.addAttribute("team2",spiel.getTeam2().getTeamName());
        model.addAttribute("erstauf",spiel.getErstaufschlagende().getTeamName());
        
        result += spiel.getSpielfeld().getObenLinkerSpieler().getId() + " " + spiel.getSpielfeld().getObenLinkerSpieler().getVorname() + " " + spiel.getSpielfeld().getObenLinkerSpieler().getName() + getAufschlagender(spiel.getSpielfeld().getObenLinkerSpieler(), spiel)  + " || ";
        result += spiel.getSpielfeld().getObenRechterSpieler().getId() + " " + spiel.getSpielfeld().getObenRechterSpieler().getVorname() + " " + spiel.getSpielfeld().getObenRechterSpieler().getName() + getAufschlagender(spiel.getSpielfeld().getObenRechterSpieler(), spiel)  + "<br/>";
        
        result += " ---------------------------------------------- <br/>";
        result += spiel.getSpielfeld().getUntenLinkerSpieler().getId() + " " + spiel.getSpielfeld().getUntenLinkerSpieler().getVorname() + " " + spiel.getSpielfeld().getUntenLinkerSpieler().getName() + getAufschlagender(spiel.getSpielfeld().getUntenLinkerSpieler(), spiel)  + " || ";
        result += spiel.getSpielfeld().getUntenRechterSpieler().getId() + " " + spiel.getSpielfeld().getUntenRechterSpieler().getVorname() + " " + spiel.getSpielfeld().getUntenRechterSpieler().getName() + getAufschlagender(spiel.getSpielfeld().getUntenRechterSpieler(), spiel)  + "<br/>";
      
        result += "<br/><br/>";
        result += " ----------------------- <br/>";
        result += spiel.getTeam1().getTeamName() + " | " + spiel.getSpielsatz1().getScoreTeam1() + " | " + spiel.getSpielsatz2().getScoreTeam1() + " | " + spiel.getSpielsatz3().getScoreTeam1() + "|<br/>"; 
        result += " ----------------------- <br/>";
        result += spiel.getTeam2().getTeamName() + " | " + spiel.getSpielsatz1().getScoreTeam2() + " | " + spiel.getSpielsatz2().getScoreTeam2() + " | " + spiel.getSpielsatz3().getScoreTeam2() + "|<br/>"; 
        
        String nachrichtenTxt = "";
        if (nachrichten.containsKey(spiel.getId())) {
            for (String nr : nachrichten.get(spiel.getId())) {
                model.addAttribute("nachrichten",nr);
                //nachrichtenTxt = nr + "<br/>" + nachrichtenTxt;
            }
        }        
        return "/list";
    }
    
    
    /*@GetMapping("/startsz")
    public String startSz(@RequestParam("aufschlagender") int aufschlagender) {        
        for (Spieler spieler : ProjectbApplication.spielerList) {
            if (spieler.getId() == aufschlagender) {
                if (spiel.getSpielsatz1().getGewinner() == null) {
                    spiel.getSpielsatz1().setAufschlagender(spieler);
                } else if (spiel.getSpielsatz2().getGewinner() == null) {
                    spiel.getSpielsatz2().setAufschlagender(spieler);
                } else {
                    spiel.getSpielsatz3().setAufschlagender(spieler);
                }
            }
        }
        return "Spielsatz gestartet";
    }*/
    
    @GetMapping("/punkten")
    public String punkten(@RequestParam(value="teamId", required=false) int teamIdP, @RequestParam(value="spielId", required=false) int spielId, Model model) {    
        Spiel spiel = spielService.getSpiel(spielId);
        Long teamId = Long.valueOf(teamIdP);
        Team team;
            if (spiel.getTeam1().getId().equals(teamId)) {
                team = spiel.getTeam1();
            } else {
                team = spiel.getTeam2();
            }  
      
        if (spielBeendet(spiel)){
            addNachricht(spiel, "Neues Spiel starten?");            
            return "redirect:/spiel/scoreTab?spielId=" + spielId;
        } else {
            if (spiel.getSpielsatz1().getGewinner() == null) {
               if (spiel.getTeam1().getId().equals(teamId)) {
                   spiel.getSpielsatz1().setScoreTeam1(spiel.getSpielsatz1().getScoreTeam1() + 1);
               } else if (spiel.getTeam2().getId().equals(teamId)) {
                   spiel.getSpielsatz1().setScoreTeam2(spiel.getSpielsatz1().getScoreTeam2() + 1);
               }                          
            } else if (spiel.getSpielsatz2().getGewinner() == null) {
              if (spiel.getTeam1().getId().equals(teamId)) {
                   spiel.getSpielsatz2().setScoreTeam1(spiel.getSpielsatz2().getScoreTeam1() + 1);
               } else if (spiel.getTeam2().getId().equals(teamId)) {
                   spiel.getSpielsatz2().setScoreTeam2(spiel.getSpielsatz2().getScoreTeam2() + 1);
               }
            } else {
               if (spiel.getTeam1().getId().equals(teamId)) {
                   spiel.getSpielsatz3().setScoreTeam1(spiel.getSpielsatz3().getScoreTeam1() + 1);
               } else if (spiel.getTeam2().getId().equals(teamId)) {
                   spiel.getSpielsatz3().setScoreTeam2(spiel.getSpielsatz3().getScoreTeam2() + 1);
               }
            } 
            
            spielService.saveSpielsatz(spiel);
            addNachricht(spiel, "1 Punkt zu " + team.getTeamName());
            spielLogik(spiel);
            prüfePositionierung(team, spiel.getSpielfeld().getAufschlagender(), spiel);
            
            model.addAttribute("teamId",teamId);
            model.addAttribute("spielId",spielId);
            return "redirect:/spiel/scoreTab?spielId=" + spielId;
            }
    }
    
    private void wechselPositionen(Team team, Spiel spiel) {
        System.out.println("wechselPositionen");
        if (team.getTeamName().equals(getTeamBySpieler(spiel.getSpielfeld().getObenLinkerSpieler(), spiel).getTeamName())) {            
            Spieler neuOL = spiel.getSpielfeld().getUntenLinkerSpieler();
            Spieler neuUL = spiel.getSpielfeld().getObenLinkerSpieler();
            spiel.getSpielfeld().setObenLinkerSpieler(neuOL);
            spiel.getSpielfeld().setUntenLinkerSpieler(neuUL);
            spiel.getSpielfeld().setObenLinkerSpielerId(neuOL.getId());
            spiel.getSpielfeld().setUntenLinkerSpielerId(neuUL.getId());
        } else {
            Spieler neuOR = spiel.getSpielfeld().getUntenRechterSpieler();
            Spieler neuUR = spiel.getSpielfeld().getObenRechterSpieler();
            spiel.getSpielfeld().setObenRechterSpieler(neuOR);
            spiel.getSpielfeld().setUntenRechterSpieler(neuUR);
            spiel.getSpielfeld().setObenRechterSpielerId(neuOR.getId());
            spiel.getSpielfeld().setUntenRechterSpielerId(neuUR.getId());
        }
        spielService.saveSpielfeld(spiel.getSpielfeld());
    }
    
     private void nimmAufschlag(Team team, int score, Spiel spiel) {
        if (team.getTeamName().equals(getTeamBySpieler(spiel.getSpielfeld().getObenLinkerSpieler(), spiel).getTeamName())) {
            Spieler ol = spiel.getSpielfeld().getObenLinkerSpieler();
            Spieler or = spiel.getSpielfeld().getObenRechterSpieler();
            if (score % 2 == 0) {
                spiel.getSpielfeld().setAufschlagender(ol);
                spiel.getSpielfeld().setAufschlagenderId(ol.getId());
            } else {
               spiel.getSpielfeld().setAufschlagender(or); 
               spiel.getSpielfeld().setAufschlagenderId(or.getId());
            }             
        } else {
            Spieler ul = spiel.getSpielfeld().getUntenLinkerSpieler();
            Spieler ur = spiel.getSpielfeld().getUntenRechterSpieler();
            if (score % 2 == 0) {
                spiel.getSpielfeld().setAufschlagender(ur);
                spiel.getSpielfeld().setAufschlagenderId(ur.getId());
            } else {
               spiel.getSpielfeld().setAufschlagender(ul); 
               spiel.getSpielfeld().setAufschlagenderId(ul.getId()); 
            }
        }
        
        spielService.saveSpielfeld(spiel.getSpielfeld());
    }
    
    private int getAktullerPuntktstand(Team team, Spiel spiel) {
        if (spiel.getSpielsatz1().getGewinner() == null) {
           if (spiel.getTeam1().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz1().getScoreTeam1();
           } else if (spiel.getTeam2().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz1().getScoreTeam2();
           }           
        } else if (spiel.getSpielsatz2().getGewinner() == null) {
          if (spiel.getTeam1().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz2().getScoreTeam1();
           } else if (spiel.getTeam2().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz2().getScoreTeam2();
           }  
        } else {
           if (spiel.getTeam1().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz3().getScoreTeam1();
           } else if (spiel.getTeam2().getTeamName().equals(team.getTeamName())) {
               return spiel.getSpielsatz3().getScoreTeam2();
           } 
        }
        return 0;
    }
    
    private void prüfePositionierung(Team scoredTeam, Spieler aufschlag, Spiel spiel) {
        System.out.println("prüfePositionierung");
        if (scoredTeam.getTeamName().equals(getTeamBySpieler(aufschlag, spiel).getTeamName())) {
            System.out.println("aufschlag");
            wechselPositionen(scoredTeam, spiel);
            addNachricht(spiel, scoredTeam.getTeamName() + " wechselt die Positionen");
        } else {
            if(!spielBeendet(spiel)){
            nimmAufschlag(scoredTeam, getAktullerPuntktstand(scoredTeam, spiel), spiel);
            addNachricht(spiel, spiel.getSpielfeld().getAufschlagender().getVorname() + " " 
                    + spiel.getSpielfeld().getAufschlagender().getName() + " aus der Team " + scoredTeam.getTeamName() 
                    + " nimmt der Aufschlag");
            
            } else {
                System.out.println("in else");
            }
        }
    }
    
    private boolean spielBeendet(Spiel spiel) {
        boolean flag = false;
        if (spiel.getSpielsatz1().getGewinner() != null && spiel.getSpielsatz2().getGewinner() != null && 
               spiel.getSpielsatz1().getGewinner().getTeamName().equals(spiel.getSpielsatz2().getGewinner().getTeamName())){
            flag = true;
            addNachricht(spiel, spiel.getSpielsatz1().getGewinner().getTeamName() + " hat das Spiel gewonnen!");            
            addNachricht(spiel, "Spiel ist beendet!");
            
        } else if (spiel.getSpielsatz1().getGewinner() != null && spiel.getSpielsatz2().getGewinner() != null 
                && spiel.getSpielsatz3().getGewinner() != null) {
            int t1 = 0;
            int t2 = 0;
            
            if (spiel.getSpielsatz1().getGewinner().getTeamName().equals(spiel.getTeam1().getTeamName())) {
               t1++; 
            } else {
                t2++;
            }
            
            if (spiel.getSpielsatz2().getGewinner().getTeamName().equals(spiel.getTeam1().getTeamName())) {
               t1++; 
            } else {
                t2++;
            }
            
            if (spiel.getSpielsatz3().getGewinner().getTeamName().equals(spiel.getTeam1().getTeamName())) {
               t1++; 
            } else {
                t2++;
            }
            
            if (t1 > t2) {
                addNachricht(spiel, spiel.getTeam1().getTeamName() + " hat das Spiel gewonnen!");
            } else {
                addNachricht(spiel, spiel.getTeam2().getTeamName() + " hat  das Spiel gewonnen!");
            }
            flag = true;
            addNachricht(spiel, "Spiel ist beendet!");
            
        }
        return flag;
    }
    
    private void wechselTeamspositionen(Spiel spiel) {
        Spieler neuUR = spiel.getSpielfeld().getUntenLinkerSpieler();
        Spieler neuUL = spiel.getSpielfeld().getUntenRechterSpieler();
        Spieler neuOR = spiel.getSpielfeld().getObenLinkerSpieler();
        Spieler neuOL = spiel.getSpielfeld().getObenRechterSpieler();
        
        spiel.getSpielfeld().setObenLinkerSpieler(neuOL);
        spiel.getSpielfeld().setObenRechterSpieler(neuOR);
        spiel.getSpielfeld().setUntenLinkerSpieler(neuUL);
        spiel.getSpielfeld().setUntenRechterSpieler(neuUR);      
        
        spiel.getSpielfeld().setObenLinkerSpielerId(neuOL.getId());
        spiel.getSpielfeld().setObenRechterSpielerId(neuOR.getId());
        spiel.getSpielfeld().setUntenLinkerSpielerId(neuUL.getId());
        spiel.getSpielfeld().setUntenRechterSpielerId(neuUR.getId());
       
        spielService.saveSpielfeld(spiel.getSpielfeld());
    }
            
    private void spielLogik(Spiel spiel) {
        Spielsatz aktuellSpielsatz = szBestimmen(spiel);
        if (aktuellerSpielsatz(spiel) == 3 && (aktuellSpielsatz.getScoreTeam1() == 11 || aktuellSpielsatz.getScoreTeam2() == 11)) {
            addNachricht(spiel, "Teams wechseln ihre Positionen");            
            wechselTeamspositionen(spiel);
        }
        
        if (aktuellSpielsatz.getScoreTeam1() == 11 || aktuellSpielsatz.getScoreTeam2() == 11) {
            addNachricht(spiel, "Teams machen eine Pause");
        }
    
        if (aktuellSpielsatz.getScoreTeam1() >= 30) {
            aktuellSpielsatz.setGewinner(spiel.getTeam1());
            aktuellSpielsatz.setGewinnerId(spiel.getTeam1().getId());
            addNachricht(spiel, spiel.getTeam1().getTeamName() + " hat der Spielsatz gewonnen!");
            wechselTeamspositionen(spiel);
        }
        else if (aktuellSpielsatz.getScoreTeam2() >= 30) {
           aktuellSpielsatz.setGewinner(spiel.getTeam2());
           aktuellSpielsatz.setGewinnerId(spiel.getTeam2().getId());
           addNachricht(spiel, spiel.getTeam2().getTeamName() + " hat der Spielsatz gewonnen!");
           wechselTeamspositionen(spiel);
        }
        else if (aktuellSpielsatz.getScoreTeam1() >= 21 && (aktuellSpielsatz.getScoreTeam1() - aktuellSpielsatz.getScoreTeam2()) >= 2) {
            aktuellSpielsatz.setGewinner(spiel.getTeam1());
            aktuellSpielsatz.setGewinnerId(spiel.getTeam1().getId());
            addNachricht(spiel, spiel.getTeam1().getTeamName() + " hat der Spielsatz gewonnen!");
            wechselTeamspositionen(spiel);
        }
        else if (aktuellSpielsatz.getScoreTeam2() >= 21 && (aktuellSpielsatz.getScoreTeam2() - aktuellSpielsatz.getScoreTeam1()) >= 2) {
            aktuellSpielsatz.setGewinner(spiel.getTeam2());
            aktuellSpielsatz.setGewinnerId(spiel.getTeam2().getId());
            addNachricht(spiel, spiel.getTeam2().getTeamName() + " hat der Spielsatz gewonnen!");            
            wechselTeamspositionen(spiel);
        }
        spielService.saveSpielsatz(spiel);
    }
    
    private Spielsatz szBestimmen(Spiel spiel){
        Spielsatz aktuell = null;
        
        if (spiel.getSpielsatz1().getGewinner() == null) {
            aktuell = spiel.getSpielsatz1();
            if (!nachrichten.get(spiel.getId()).contains("Spielsatz 1 gestartet")) {
                addNachricht(spiel, "Spielsatz 1 gestartet");
            }
        } else if (spiel.getSpielsatz2().getGewinner() == null) {
           aktuell = spiel.getSpielsatz2();
           if (!nachrichten.get(spiel.getId()).contains("Spielsatz 2 gestartet")) {
               addNachricht(spiel, "Spielsatz 2 gestartet");
            }
        } else {
            aktuell = spiel.getSpielsatz3();
            if (!nachrichten.get(spiel.getId()).contains("Spielsatz 3 gestartet")) {
                addNachricht(spiel, "Spielsatz 3 gestartet");                
            }
        }
       return aktuell;
    }
    
     private int aktuellerSpielsatz(Spiel spiel) {        
        if (spiel.getSpielsatz1().getGewinner() == null) {
            return 1;
        } else if (spiel.getSpielsatz2().getGewinner() == null) {
           return 2;
        } else {
            return 3;
        }
    }
    
    public String getAufschlagender(Spieler spieler, Spiel spiel) {        
        if (spiel.getSpielfeld().getAufschlagender() != null && spiel.getSpielfeld().getAufschlagender().getId().equals(spieler.getId())) {
            return "+";
        }  
        return "";
    } 
    
   
    @GetMapping("spielfeld/addSpielfeld")
    public String addSpieler(@RequestParam(value="ol", required=false) int ol, @RequestParam(value="or", required=false) int or, @RequestParam(value="ul", required=false) int ul, @RequestParam(value="ur", required=false) int ur, @RequestParam(value="erstauf", required=false) int erstauf, @RequestParam(value="spielId", required=false) int spielId, Model model) {        
        
        Spiel spiel = spielService.getSpiel(spielId);
        
        Spielfeld spielfeld = new Spielfeld(Long.valueOf(spielId), spielService.getSpielerById(or), spielService.getSpielerById(ol), 
                                    spielService.getSpielerById(ur), spielService.getSpielerById(ul), 
                                    spielService.getSpielerById(erstauf));
        
        
        spielfeld = spielService.saveSpielfeld(spielfeld);
        spiel.setSpielfeld(spielfeld);
        spiel.setSpielfeldId(spielfeld.getId());               
        
        spielService.createAndSaveSpielsatz(spiel);
        spiel = spielService.save(spiel);
        
        return "redirect:/spiel/scoreTab?spielId=" + spielId;
    }
    
    @GetMapping("/scoreTab")
    public String scoreTabs(@RequestParam(value="spielId", required=false) int spielId, Model model) {  
        Spiel spiel = spielService.getSpiel(spielId);
        List nachrichtenTxt = new LinkedList<String>();
        if (nachrichten.containsKey(spiel.getId())) {
            nachrichtenTxt = nachrichten.get(spiel.getId());
        }   
        Collections.reverse(nachrichtenTxt);
        model.addAttribute("spiel", spiel);        
        model.addAttribute("nachrichtenTxt", nachrichtenTxt);
        return "/scoreTab";
    }
    
    private Team getTeamByName(String teamname, Spiel spiel) {
        if (spiel.getTeam1().getTeamName().equals(teamname)) {
            return spiel.getTeam1();
        } else {
            return spiel.getTeam2();
        }
    }
    
    private Team getTeamBySpieler(Spieler spieler, Spiel spiel) {
        if (spiel.getTeam1().getSpieler1().getId().equals(spieler.getId()) || spiel.getTeam1().getSpieler2().getId().equals(spieler.getId())) {
            return spiel.getTeam1();
        } else {
            return spiel.getTeam2();
        }
    }
    
    private void addNachricht(Spiel spiel, String nachricht) {
        if (nachrichten.containsKey(spiel.getId())) {
            nachrichten.get(spiel.getId()).add(nachricht);
        } else {
            LinkedList<String> n = new LinkedList<>();
            n.add(nachricht);
            nachrichten.put(spiel.getId(), n);
        }
    }
    
    public String getPunktUrl(int spielId, int teamId) {
        return "location.href='/spiel/punkten?teamId=" + spielId + "&teamId=" + teamId;
    }
}
