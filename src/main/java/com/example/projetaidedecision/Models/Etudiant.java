package com.example.projetaidedecision.Models;

import java.util.ArrayList;

public class Etudiant {
    private String id;
    private ArrayList<Etablissement> preference;
    private Etablissement assignement;
    private int currentChoice;

    public Etudiant(String id) {
        this.id = id;
        this.preference = new ArrayList<>();
        this.assignement = null;
        this.currentChoice=0;
    }

    public int getCurrentChoice() {
        return currentChoice;
    }

    public void setCurrentChoice(int currentChoice) {
        this.currentChoice = currentChoice;
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



    public void addOneAssignement(Etablissement etablissement){
        this.assignement =etablissement;

    }

    public Etablissement getEtblissment(int i){
        return  this.preference.get(i);
    }

    public void incrementChoice(){
        this.currentChoice++;
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

    @Override
    public String toString() {
        return "Eleve{" +
                "id='" + id + '\'' +
                ", preference=" + getPreferencesId() +
                ", assignement=" + assignement.getId()+
                '}';
    }
}
