package com.tp.todo;

public class TODO {


    int id;
    String nom;
    String urgency;


    public TODO() {
    }

    public TODO(int id, String nom, String urgency) {
        this.id = id;
        this.nom = nom;
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }


}
