package com.example.projetaidedecision;

import com.example.projetaidedecision.algorithms.PreferencesGenerator;

public class Main {

    public static void main(String[] args) {
        PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList(6);
        preferencesGenerator.createUniversityList(6,5);
        preferencesGenerator.generateStudentsChoices();
        preferencesGenerator.generateUniversitiesChoices();

    }

}
