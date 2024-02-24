/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.hawhamburg.inf.projectb.Models;

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
public class Team {
    @Id
    @SequenceGenerator(
              name="team_sequence",
              sequenceName="team_sequence",
              allocationSize=1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "team_sequence"
    )
    private Long id;
 
    private String teamName;
    
    private Long spieler1Id;
    
    private Long spieler2Id;
    
    @Transient
    private Spieler spieler1;
    
    @Transient
    private Spieler spieler2;
    /**
     * Konstruktor für Objekte der Klasse Team
     */
    
    public Team() {
    }
    
    public Team(String teamName, Spieler spieler1,Spieler spieler2) {
        this.teamName = teamName;
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
        this.spieler1Id = spieler1.getId();
        this.spieler2Id = spieler2.getId();
    }
    
    public Team(String teamName, Long spieler1, Long spieler2) {
        this.teamName = teamName;
        this.spieler1Id = spieler1;
        this.spieler2Id = spieler2;
    }
    
    public String showDetails() {
        return "Team: " + teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Transient
    public Spieler getSpieler1() {
        return spieler1;
    }

    @Transient
    public void setSpieler1(Spieler spieler1) {
        this.spieler1 = spieler1;
    }

    @Transient
    public Spieler getSpieler2() {
        return spieler2;
    }

    @Transient
    public void setSpieler2(Spieler spieler2) {
        this.spieler2 = spieler2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpieler1Id() {
        return spieler1Id;
    }

    public void setSpieler1Id(Long spieler1Id) {
        this.spieler1Id = spieler1Id;
    }

    public Long getSpieler2Id() {
        return spieler2Id;
    }

    public void setSpieler2Id(Long spieler2Id) {
        this.spieler2Id = spieler2Id;
    }
    
    
    
}
