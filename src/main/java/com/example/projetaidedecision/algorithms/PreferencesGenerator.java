package com.example.projetaidedecision.algorithms;

import com.example.projetaidedecision.Models.Eleve;
import com.example.projetaidedecision.Models.Etablissement;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.ArrayList;
import java.util.Random;

public class PreferencesGenerator {

    private final ArrayList<Eleve> studentList = new ArrayList<>();
    private final ArrayList<Etablissement> universityList = new ArrayList<>();
    private final Random random = new Random();

    public PreferencesGenerator() {

    }

    public void createStudentList(int numberOfStudentToGenerate) {
        for (int i = 0; i < numberOfStudentToGenerate; i++) {
            Eleve eleve = new Eleve("i_" + i);
            studentList.add(eleve);
            System.out.println(eleve.toString());
        }
    }

    public void createUniversityList(int numberOfUniversitiesToGenerate, int maximumCapacity) {
        for (int i = 0; i < numberOfUniversitiesToGenerate; i++) {
            Etablissement etablissement = new Etablissement("s_" + i, random.nextInt(maximumCapacity - 1) + 1);
            universityList.add(etablissement);
            System.out.println(etablissement.toString());
        }
    }

    public void generateStudentsChoices(){
        for (Eleve eleve:studentList) {
            ArrayList<Etablissement> tempList = (ArrayList<Etablissement>) universityList.clone();
            for (int i = 0; i < universityList.size(); i++) {
                int rdmchoice = random.nextInt(tempList.size());
                eleve.addOneChoice(tempList.get(rdmchoice));
                tempList.remove(rdmchoice);
            }
            System.out.println(eleve.toString());

        }
    }

    public void generateUniversitiesChoices(){
        for (Etablissement etablissement:universityList) {
            ArrayList<Eleve> tempList = (ArrayList<Eleve>) studentList.clone();
            for (int i = 0; i < studentList.size(); i++) {
                int rdmchoice = random.nextInt(tempList.size());
                etablissement.addOneStudent(tempList.get(rdmchoice));
                tempList.remove(rdmchoice);
            }
            System.out.println(etablissement.toString());

        }
    }


}
