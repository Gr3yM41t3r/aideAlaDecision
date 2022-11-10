package com.example.projetaidedecision.Utils;

import com.example.projetaidedecision.Models.Etablissement;
import com.example.projetaidedecision.Models.Etudiant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    private ArrayList<Etablissement> etablissements ;
    private ArrayList<Etudiant> etudiants ;
    private Map<String,Etudiant> etudiantMap;
    private Map<String,Etablissement> etablissementMap;



    public FileParser() {
        this.etablissements = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.etudiantMap =new HashMap<>();
        this.etablissementMap =new HashMap<>();
    }



    public void parse(String filename){
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                parseLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                addPreferences(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Etudiant etudiant:etudiants) {
            System.out.println(etudiant.toString());

        }
        for (Etablissement etablissement:etablissements) {
            System.out.println(etablissement.toString());

        }
    }

    public void addPreferences(String line){
        String id;
        String preference;
        String capacite;
        Etudiant etudiant ;
        Etablissement etablissement;
        if(line.charAt(0)=='i'){
            id = line.split(":")[0];
            preference = line.split(":")[1];
            preference=preference.substring(1,preference.length()-1);
            String[] preferences = preference.split(",");
            for (String str:preferences) {
                this.etudiants.get(this.etudiants.indexOf(this.etudiantMap.get(id))).addOneChoice(this.etablissementMap.get(str));
            }


        }else if (line.charAt(0)=='s') {
            id = line.split(":")[0];
            preference = line.split(":")[1];
            preference=preference.substring(1,preference.length()-1);
            capacite= String.valueOf(preference.charAt(0));
            String[] preferences = preference.split(",");
            for (int i=1;i<preferences.length;i++) {
                this.etablissements.get(this.etablissements.indexOf(this.etablissementMap.get(id))).addOneStudent(this.etudiantMap.get(preferences[i]));
                System.out.println(preferences[i]);
            }
        }else {
            System.out.println("error parsing");
        }
    }

    public void parseLine(String line){
        String id;
        String preference;
        String capacite;
        Etudiant etudiant ;
        Etablissement etablissement;
        if(line.charAt(0)=='i'){
            id = line.split(":")[0];
            preference = line.split(":")[1];
            preference=preference.substring(1,preference.length()-1);
            System.out.print(id+" ");
            System.out.println(preference+" ");
            etudiant = new Etudiant(id);
            String[] listPreference = preference.split(",");
            this.etudiants.add(etudiant);
            this.etudiantMap.put(etudiant.getId(),etudiant);

        } else if (line.charAt(0)=='s') {
            id = line.split(":")[0];
            preference = line.split(":")[1];
            preference=preference.substring(1,preference.length()-1);
            capacite= String.valueOf(preference.charAt(0));
            etablissement = new Etablissement(id,Integer.parseInt(capacite));
            this.etablissements.add(etablissement);
            this.etablissementMap.put(etablissement.getId(),etablissement);
        }else {
            System.out.println("error parsing");
        }

    }

    public ArrayList<Etablissement> getEtablissements() {
        return etablissements;
    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }
}
