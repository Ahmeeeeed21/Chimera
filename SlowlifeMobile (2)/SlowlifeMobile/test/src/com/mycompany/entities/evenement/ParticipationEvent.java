/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.evenement;

/**
 *
 * @author Ahmed Ezzine
 */
public class ParticipationEvent {
     private int id;
    private long idEvent;
    private int idUser;
    
        public ParticipationEvent() {
        super();
    }

    public ParticipationEvent(int id, int idEvent, int idUser) {
        this.id = id;
        this.idEvent = idEvent;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
