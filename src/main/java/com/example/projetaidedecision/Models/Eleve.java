package com.example.projetaidedecision.Models;

import java.util.ArrayList;

public class Eleve {
    private String id;
    private ArrayList<Etablissement> preference;
    private Etablissement assignement;

    public Eleve(String id) {
        this.id = id;
        this.preference = new ArrayList<>();
        this.assignement = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Etablissement> getPreference() {
        return preference;
    }

    public void setPreference(ArrayList<Etablissement> preference) {
        this.preference = preference;
    }

    public Etablissement getAssignement() {
        return assignement;
    }

    public void setAssignement(Etablissement assignement) {
        this.assignement = assignement;
    }

    @Override
    public String toString() {
        return "Eleve{" +
                "id='" + id + '\'' +
                ", preference=" + getPreferencesId() +
                ", assignement=" + assignement +
                '}';
    }

    public String getPreferencesId(){
        StringBuilder str= new StringBuilder();
        String prefix = "";
        for (Etablissement pref:this.preference) {
            str.append(prefix);
            prefix = ",";
            str.append(pref.getId());
        }
        return str.toString();
    }

    public void addOneChoice(Etablissement etablissement){
        this.preference.add(etablissement);
    }
}
