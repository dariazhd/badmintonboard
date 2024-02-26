/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.badmintonboard.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 *
 * @author diiia
 */
@Entity
@Table
public class Spiel {
    @Id
    @SequenceGenerator(
              name="spiel_sequence",
              sequenceName="spiel_sequence",
              allocationSize=1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "spiel_sequence"
    )
    private Long id;
    
    private Long team1Id;
    
    private Long team2Id;
    
    private Long erstaufschlagendeId;
    
    private Long spielsatz1Id;
    
    private Long spielsatz2Id;
    
    private Long spielsatz3Id;
    
    private Long spielfeldId;
    
    @Transient
    private Team team1;
    
    @Transient
    private Team team2;
    
    @Transient
    private Team erstaufschlagende;
    
    @Transient
    private Spielsatz spielsatz1;
    
    @Transient
    private Spielsatz spielsatz2;
    
    @Transient
    private Spielsatz spielsatz3;
    
    @Transient
    private Spielfeld spielfeld;
    
    public Spiel() {
        //spielsatz1 = new Spielsatz();
        //spiel.setSpielsatz1Id(spielsatz1.getId());
        //spielsatz2 = new Spielsatz();
        //spiel.setSpielsatz2Id(spielsatz2.getId());
        //spielsatz3 = new Spielsatz();
        //spiel.setSpielsatz3Id(spielsatz3.getId());
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getErstaufschlagende() {
        return erstaufschlagende;
    }

    public void setErstaufschlagende(Team erstaufschlagende) {
        this.erstaufschlagende = erstaufschlagende;
    }

    public Spielsatz getSpielsatz1() {
        return spielsatz1;
    }

    public void setSpielsatz1(Spielsatz spielsatz1) {        
        this.spielsatz1 = spielsatz1;
    }

    public Spielsatz getSpielsatz2() {
        return spielsatz2;
    }

    public void setSpielsatz2(Spielsatz spielsatz2) {
        this.spielsatz2 = spielsatz2;
    }

    public Spielsatz getSpielsatz3() {
        return spielsatz3;
    }

    public void setSpielsatz3(Spielsatz spielsatz3) {
        this.spielsatz3 = spielsatz3;
    }

    public Spielfeld getSpielfeld() {
        return spielfeld;
    }

    public void setSpielfeld(Spielfeld spielfeld) {
        this.spielfeld = spielfeld;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(Long team1Id) {
        this.team1Id = team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(Long team2Id) {
        this.team2Id = team2Id;
    }

    public Long getErstaufschlagendeId() {
        return erstaufschlagendeId;
    }

    public void setErstaufschlagendeId(Long erstaufschlagendeId) {
        this.erstaufschlagendeId = erstaufschlagendeId;
    }

    public Long getSpielsatz1Id() {
        return spielsatz1Id;
    }

    public void setSpielsatz1Id(Long spielsatz1Id) {
        this.spielsatz1Id = spielsatz1Id;
    }

    public Long getSpielsatz2Id() {
        return spielsatz2Id;
    }

    public void setSpielsatz2Id(Long spielsatz2Id) {
        this.spielsatz2Id = spielsatz2Id;
    }

    public Long getSpielsatz3Id() {
        return spielsatz3Id;
    }

    public void setSpielsatz3Id(Long spielsatz3Id) {
        this.spielsatz3Id = spielsatz3Id;
    }

    public Long getSpielfeldId() {
        return spielfeldId;
    }

    public void setSpielfeldId(Long spielfeldId) {
        this.spielfeldId = spielfeldId;
    }
    
}
