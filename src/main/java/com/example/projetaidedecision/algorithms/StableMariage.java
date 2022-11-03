package com.example.projetaidedecision.algorithms;

import com.example.projetaidedecision.Models.Etudiant;
import com.example.projetaidedecision.Models.Etablissement;

import java.util.ArrayList;
import java.util.List;

public class StableMariage {

    List<Etudiant> listeEtudiants;
    List<Etablissement> listeEtablissement;


    public StableMariage(List<Etudiant> listeEtudiants, List<Etablissement> listeEtablissement) {
        this.listeEtudiants = listeEtudiants;
        this.listeEtablissement = listeEtablissement;
    }

    public void solve() {
        List<Etudiant> input = new ArrayList<>(this.listeEtudiants);

        while (!input.isEmpty()) {
            List<Etudiant> removeToInput = new ArrayList<>();
            List<Etudiant> addToInput = new ArrayList<>();

            for (int i = 0, inputSize = input.size(); i < inputSize; i++) {
                Etudiant etudiant = input.get(i);
                removeToInput.add(etudiant);
                Etablissement wish = etudiant.getEtblissment(etudiant.getCurrentChoice());
                if (wish.hasFreeSpace()) {
                    etudiant.addOneAssignement(wish);
                    wish.addOneAssignemen(etudiant);

                } else {
                    Etudiant deleted = wish.getLowerPrefStudent(etudiant);
                    if (deleted != null) {
                        deleted.incrementChoice();
                        addToInput.add(deleted);
                        wish.removeAssignement(deleted);
                        deleted.setAssignement(null);
                        etudiant.addOneAssignement(wish);
                        wish.addOneAssignemen(etudiant);

                    } else {
                        etudiant.incrementChoice();
                        addToInput.add(etudiant);
                    }
                    if (etudiant.getCurrentChoice() > etudiant.getPreference().size() - 1) {
                        removeToInput.add(etudiant);
                        addToInput.remove(etudiant);
                    }


                }


            }
            input.removeAll(removeToInput);
            input.addAll(addToInput);
        }
        for (Etudiant etudiant: this.listeEtudiants) {
            System.err.println(etudiant.toString());

        }
        for (Etablissement eta:this.listeEtablissement) {
            System.err.println(eta.toString());
        }
    }
}

