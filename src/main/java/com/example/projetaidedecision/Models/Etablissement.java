package com.example.projetaidedecision.Models;

import java.util.ArrayList;
import java.util.Map;

public class Etablissement {
    private String id;
    private int capacity;
    private ArrayList<Eleve> studentPriority;
    private ArrayList<Eleve> studentsEnroled;

    public Etablissement(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.studentPriority = new ArrayList<>();
        this.studentsEnroled = new ArrayList<>();
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

    public ArrayList<Eleve> getStudentPriority() {
        return studentPriority;
    }

    public void setStudentPriority(ArrayList<Eleve> studentPriority) {
        this.studentPriority = studentPriority;
    }

    public ArrayList<Eleve> getStudentsEnroled() {
        return studentsEnroled;
    }

    public void setStudentsEnroled(ArrayList<Eleve> studentsEnroled) {
        this.studentsEnroled = studentsEnroled;
    }

    public void addOneStudent(Eleve eleve){
        if(this.studentPriority.size()<this.capacity){
            this.studentPriority.add(eleve);
        }
    }

    public String getPreferencesId(){
        StringBuilder str= new StringBuilder();
        String prefix = "";
        for (Eleve eleve:this.studentPriority) {
            str.append(prefix);
            prefix = ",";
            str.append(eleve.getId());
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "Etablissement{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", studentPriority=" + this.getPreferencesId() +
                ", studentsEnroled=" + studentsEnroled +
                '}';
    }
}
