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
public class Spielsatz {
    @Id
    @SequenceGenerator(
              name="spielsatz_sequence",
              sequenceName="spielsatz_sequence",
              allocationSize=1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "spielsatz_sequence"
    )
    private Long id;
    private int scoreTeam1;
    private int scoreTeam2;
    private Long gewinnerId;
    private Long spielId;
    
    
    @Transient
    private transient Team gewinner;

    public Spielsatz() {
    }

    public Spielsatz(int scoreTeam1, int scoreTeam2, Long gewinnerId, Long spielId) {
        this.scoreTeam1 = scoreTeam1;
        this.scoreTeam2 = scoreTeam2;
        this.gewinnerId = gewinnerId;
        this.spielId = spielId;
    }
    
    

    public int getScoreTeam1() {
        return scoreTeam1;
    }

    public void setScoreTeam1(int scoreTeam1) {
        this.scoreTeam1 = scoreTeam1;
    }

    public int getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeam2(int scoreTeam2) {
        this.scoreTeam2 = scoreTeam2;
    }

    public Team getGewinner() {
        return gewinner;
    }

    public void setGewinner(Team gewinner) {
        this.gewinner = gewinner;
    }   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGewinnerId() {
        return gewinnerId;
    }

    public void setGewinnerId(Long gewinnerId) {
        this.gewinnerId = gewinnerId;
    }

    public Long getSpielId() {
        return spielId;
    }

    public void setSpielId(Long spielId) {
        this.spielId = spielId;
    }    
}
