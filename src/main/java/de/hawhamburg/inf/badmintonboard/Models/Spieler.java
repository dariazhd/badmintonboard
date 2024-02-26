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

/**
 *
 * @author diiia
 */
@Entity
@Table

public class Spieler {
    @Id
    @SequenceGenerator(
              name="spieler_sequence",
              sequenceName="spieler_sequence",
              allocationSize=1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "spieler_sequence"
    )
    private Long id;
    
    private String name;
    
    private String vorname;
    
    public Spieler() {
    }
    
    public Spieler(String name,String vorname) {
        this.name = name;
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    } 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}