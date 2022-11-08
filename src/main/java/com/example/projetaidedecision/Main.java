package com.example.projetaidedecision;

import com.example.projetaidedecision.algorithms.PreferencesGenerator;
import com.example.projetaidedecision.algorithms.StableMariage;

public class Main {

    public static void main(String[] args) throws Exception {
        PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList(36);
        preferencesGenerator.createUniversityList(6,6);
        preferencesGenerator.generateStudentsChoices();
        preferencesGenerator.generateUniversitiesChoices();

        StableMariage stableMariage = new StableMariage(preferencesGenerator.getStudentList(),preferencesGenerator.getUniversityList());
        stableMariage.solve2();

    }

}
