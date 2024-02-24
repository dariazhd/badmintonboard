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
 * @author Daria Zhdanova
 */
@Entity
@Table
public class Spielfeld {
    @Id
    @SequenceGenerator(
              name="spielfeld_sequence",
              sequenceName="spielfeld_sequence",
              allocationSize=1
    )
    @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "spielfeld_sequence"
    )
    private Long id;
    
    private Long spielId;
    
    private Long obenRechterSpielerId;
    
    private Long obenLinkerSpielerId;
    
    private Long untenRechterSpielerId;
    
    private Long untenLinkerSpielerId;
    
    private Long aufschlagenderId;
    
    @Transient
    private Spieler obenRechterSpieler;
    
    @Transient
    private Spieler obenLinkerSpieler;
    
    @Transient
    private Spieler untenRechterSpieler;
    
    @Transient
    private Spieler untenLinkerSpieler;
    
    @Transient
    private Spieler aufschlagender;

    public Spielfeld() {
    }
    
    

    public Spielfeld(Long spielId, Spieler obenRechterSpieler, Spieler obenLinkerSpieler, Spieler untenRechterSpieler, Spieler untenLinkerSpieler, Spieler aufschlagender) {
        this.obenRechterSpieler = obenRechterSpieler;
        this.obenLinkerSpieler = obenLinkerSpieler;
        this.untenRechterSpieler = untenRechterSpieler;
        this.untenLinkerSpieler = untenLinkerSpieler;
        this.aufschlagender = aufschlagender;
        
        this.obenRechterSpielerId = obenRechterSpieler.getId();
        this.obenLinkerSpielerId = obenLinkerSpieler.getId();
        this.untenRechterSpielerId = untenRechterSpieler.getId();
        this.untenLinkerSpielerId = untenLinkerSpieler.getId();
        this.aufschlagenderId = aufschlagender.getId();
        this.spielId = spielId;
    }

    public Spieler getObenRechterSpieler() {
        return obenRechterSpieler;
    }

    public void setObenRechterSpieler(Spieler obenRechterSpieler) {
        this.obenRechterSpieler = obenRechterSpieler;
    }

    public Spieler getObenLinkerSpieler() {
        return obenLinkerSpieler;
    }

    public void setObenLinkerSpieler(Spieler obenLinkerSpieler) {
        this.obenLinkerSpieler = obenLinkerSpieler;
    }

    public Spieler getUntenRechterSpieler() {
        return untenRechterSpieler;
    }

    public void setUntenRechterSpieler(Spieler untenRechterSpieler) {
        this.untenRechterSpieler = untenRechterSpieler;
    }

    public Spieler getUntenLinkerSpieler() {
        return untenLinkerSpieler;
    }

    public void setUntenLinkerSpieler(Spieler untenLinkerSpieler) {
        this.untenLinkerSpieler = untenLinkerSpieler;
    }

    public Spieler getAufschlagender() {
        return aufschlagender;
    }

    public void setAufschlagender(Spieler aufschlagender) {
        this.aufschlagender = aufschlagender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObenRechterSpielerId() {
        return obenRechterSpielerId;
    }

    public void setObenRechterSpielerId(Long obenRechterSpielerId) {
        this.obenRechterSpielerId = obenRechterSpielerId;
    }

    public Long getObenLinkerSpielerId() {
        return obenLinkerSpielerId;
    }

    public void setObenLinkerSpielerId(Long obenLinkerSpielerId) {
        this.obenLinkerSpielerId = obenLinkerSpielerId;
    }

    public Long getUntenRechterSpielerId() {
        return untenRechterSpielerId;
    }

    public void setUntenRechterSpielerId(Long untenRechterSpielerId) {
        this.untenRechterSpielerId = untenRechterSpielerId;
    }

    public Long getUntenLinkerSpielerId() {
        return untenLinkerSpielerId;
    }

    public void setUntenLinkerSpielerId(Long untenLinkerSpielerId) {
        this.untenLinkerSpielerId = untenLinkerSpielerId;
    }

    public Long getAufschlagenderId() {
        return aufschlagenderId;
    }

    public void setAufschlagenderId(Long aufschlagenderId) {
        this.aufschlagenderId = aufschlagenderId;
    }

    public Long getSpielId() {
        return spielId;
    }

    public void setSpielId(Long spielId) {
        this.spielId = spielId;
    }
}
