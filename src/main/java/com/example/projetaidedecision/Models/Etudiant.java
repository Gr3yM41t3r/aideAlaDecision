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

    //TODO fix bug de merde
    public Etablissement getLowerPrefUniv(Etablissement other) throws Exception {
        int otherIndex = this.preference.indexOf(other);
        int assignRank = this.preference.indexOf(assignement);
        //if (otherIndex == -1) throw new Exception("Student.get LowerPrefStudent() : Prefs not in prefs list");
        if (otherIndex < assignRank) {
            return assignement;
        }
        return null;
    }




    public void addOneChoice(Etablissement etablissement){
        this.preference.add(etablissement);
    }


    public String getAllPreferences(){
        StringBuilder str= new StringBuilder("(");
        for (int i = 0; i < this.preference.size(); i++) {
            str.append(this.preference.get(i).getId()).append(",");
        }
        str.deleteCharAt(str.length()-1);
        str.append(")");
        return str.toString();
    }

    public String printInProblemFormat(){
        return this.id+":"+this.getAllPreferences();
    }

    @Override
    public String toString() {
        return "Eleve{" +
                "id='" + id + '\'' +
                ", preference=" + getPreferencesId() +
                ", assignement=" + getIdd()+
                '}';
    }

    public String getIdd(){
        if (assignement == null){
            return "null";
        }
        return assignement.getId();
    }

    public double calculateSatisfaction(){
        if(this.assignement==null){
            return 0;
        }else{
            double position = this.preference.indexOf(this.assignement);
            return (1-((position)/this.preference.size()))*100;
        }
    }
    public void resetAssignement(){
        this.assignement = null;
    }
}
