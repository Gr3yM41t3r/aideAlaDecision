package com.example.projetaidedecision.Models;

import java.util.ArrayList;

public class Etablissement {
    private String id;
    private int capacity;
    private int numberOfPreferences;
    private boolean randomCapacity=false;
    private ArrayList<Etudiant> studentPriority;
    private ArrayList<Etudiant> studentsEnroled;

    public void setNumberOfPreferences(int numberOfPreferences) {
        this.numberOfPreferences = numberOfPreferences;
    }

    public void setRandomCapacity(boolean randomCapacity) {
        this.randomCapacity = randomCapacity;
    }

    private int currentChoice;

    public int getCurrentChoice() {
        return currentChoice;
    }

    public void setCurrentChoice(int currentChoice) {
        this.currentChoice = currentChoice;
    }

    public Etablissement(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.numberOfPreferences=capacity;
        this.studentPriority = new ArrayList<>();
        this.studentsEnroled = new ArrayList<>();
        this.currentChoice=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Etudiant> getStudentPriority() {
        return studentPriority;
    }

    public void setStudentPriority(ArrayList<Etudiant> studentPriority) {
        this.studentPriority = studentPriority;
    }

    public ArrayList<Etudiant> getStudentsEnroled() {
        return studentsEnroled;
    }

    public void setStudentsEnroled(ArrayList<Etudiant> studentsEnroled) {
        this.studentsEnroled = studentsEnroled;
    }

    public boolean hasFreeSpace(){
        return this.studentsEnroled.size()<this.capacity;

    }

    public void addOneStudent(Etudiant eleve){

            this.studentPriority.add(eleve);

    }



    public void addOneAssignemen(Etudiant etudiant) {
        this.studentsEnroled.add(etudiant);

    }

    public void removeAssignement(Etudiant etudiant){
        this.studentsEnroled.remove(etudiant);


    }

    public Etudiant getLowerPrefStudent(Etudiant student) {
        int studPlace = studentPriority.indexOf(student);
        int stud2Ind;
        int max = studPlace;
        Etudiant ret = null;
        if (studPlace == -1 || studPlace == 0) return null;
        for (Etudiant stud :
                studentPriority) {
            stud2Ind = studentPriority.indexOf(stud);
            if (stud2Ind > max) {
                max = stud2Ind;
                ret = stud;
            }
        }
        return ret;
    }
    public void incrementRank() {
        currentChoice++;
    }

    public Etudiant getPref(int rank){
        return  this.studentPriority.get(rank);
    }


    public String getPreferencesId(){
        StringBuilder str= new StringBuilder();
        String prefix = "";
        for (Etudiant eleve:this.studentPriority) {
            str.append(prefix);
            prefix = ",";
            str.append(eleve.getId());
        }
        return str.toString();
    }

    public String getEnrollerStudents(){
        StringBuilder str= new StringBuilder();
        String prefix = "";
        for (Etudiant eleve:this.studentsEnroled) {
            str.append(prefix);
            prefix = ",";
            str.append(eleve.getId());
        }
        return str.toString();
    }

    public String getAllPreferences(){
        StringBuilder str= new StringBuilder("(" + this.getCapacity());
        for (int i = 0; i < this.studentPriority.size(); i++) {
            str.append(",").append(this.studentPriority.get(i).getId());
        }

        str.append(")");
        return str.toString();
    }

    public String printInProblemFormat(){
        return this.id+":"+this.getAllPreferences();
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", studentPriority=" + this.getPreferencesId() +
                ", studentsEnroled=" + getEnrollerStudents() +
                '}';
    }
}
