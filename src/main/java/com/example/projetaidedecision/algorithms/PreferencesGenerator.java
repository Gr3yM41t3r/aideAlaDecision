package com.example.projetaidedecision.algorithms;

import com.example.projetaidedecision.Models.Etudiant;
import com.example.projetaidedecision.Models.Etablissement;

import java.util.ArrayList;
import java.util.Random;

public class PreferencesGenerator {

    private final ArrayList<Etudiant> studentList = new ArrayList<>();
    private final ArrayList<Etablissement> universityList = new ArrayList<>();
    private final Random random = new Random();

    public PreferencesGenerator() {

    }

    public ArrayList<Etudiant> getStudentList() {
        return studentList;
    }

    public ArrayList<Etablissement> getUniversityList() {
        return universityList;
    }

    public void createStudentList(int numberOfStudentToGenerate) {
        for (int i = 0; i < numberOfStudentToGenerate; i++) {
            Etudiant eleve = new Etudiant("i_" + i);
            studentList.add(eleve);
            //System.out.println(eleve.toString());
        }
    }

    public void createUniversityList(int numberOfUniversitiesToGenerate, int maximumCapacity) {
        for (int i = 0; i < numberOfUniversitiesToGenerate; i++) {
            Etablissement etablissement = new Etablissement("s_" + i, maximumCapacity);
            universityList.add(etablissement);
            System.out.println(etablissement.toString());
        }
    }

    public void generateStudentsChoices(){
        for (Etudiant eleve:studentList) {
            ArrayList<Etablissement> tempList = (ArrayList<Etablissement>) universityList.clone();
            for (int i = 0; i < universityList.size(); i++) {
                int rdmchoice = random.nextInt(tempList.size());
                eleve.addOneChoice(tempList.get(rdmchoice));
                tempList.remove(rdmchoice);
            }
           // System.out.println(eleve.toString());

        }
    }

    public void generateUniversitiesChoices(){
        for (Etablissement etablissement:universityList) {
            ArrayList<Etudiant> tempList = (ArrayList<Etudiant>) studentList.clone();
            for (int i = 0; i < studentList.size(); i++) {
                int rdmchoice = random.nextInt(tempList.size());
                etablissement.addOneStudent(tempList.get(rdmchoice));
                tempList.remove(rdmchoice);
            }
            System.out.println(etablissement.toString());

        }
    }


}
