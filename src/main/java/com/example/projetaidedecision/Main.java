package com.example.projetaidedecision;

import com.example.projetaidedecision.Utils.FileParser;
import com.example.projetaidedecision.algorithms.PreferencesGenerator;
import com.example.projetaidedecision.algorithms.StableMariage;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
       /* PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList(3);
        preferencesGenerator.createUniversityList(3,1);
        preferencesGenerator.generateStudentsChoices();
        preferencesGenerator.generateUniversitiesChoices();

        StableMariage stableMariage = new StableMariage(preferencesGenerator.getStudentList(),preferencesGenerator.getUniversityList());
        stableMariage.solve();*/
        FileParser fileParser = new FileParser();
        fileParser.parse("src/main/java/com/example/projetaidedecision/problems/problem1.txt");
        StableMariage stableMariage = new StableMariage(fileParser.getEtudiants(),fileParser.getEtablissements());
        stableMariage.solve2();

    }

}
