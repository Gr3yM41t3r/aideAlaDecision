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

            for (Etudiant etudiant : input) {
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

    public void solve2() throws Exception {
        List<Etablissement> input = new ArrayList<>(this.listeEtablissement);
        while (!input.isEmpty()){
            List<Etablissement> toRemove = new ArrayList<>();
            List<Etablissement> toAdd  = new ArrayList<>();
            for (Etablissement etablissement: input) {
                if (!etablissement.hasFreeSpace() ||etablissement.getCurrentChoice() >= etablissement.getStudentPriority().size()){
                    toRemove.add(etablissement);
                    continue;
                }

                Etudiant etudiant = etablissement.getPref(etablissement.getCurrentChoice());

                if (etudiant.getAssignement() == null){
                    etudiant.addOneAssignement(etablissement);
                    etablissement.addOneAssignemen(etudiant);
                }else {
                    Etablissement deleted = etudiant.getLowerPrefUniv(etablissement);
                    if (deleted!=null){
                        deleted.incrementRank();
                        toAdd.add(deleted);
                        etudiant.setAssignement(null);
                        etudiant.addOneAssignement(etablissement);
                        etablissement.addOneAssignemen(etudiant);

                    }else {
                        etablissement.incrementRank();
                        toRemove.add(etablissement);
                    }

                }
                if (etablissement.hasFreeSpace()) {
                    toRemove.remove((etablissement));
                    toAdd.add(etablissement);
                }

            }
            input.removeAll(toRemove);
            input.addAll(toAdd);
        }
        for (Etudiant etudiant: this.listeEtudiants) {
            System.err.print(etudiant.toString());
            System.err.println("   satisfaction"+etudiant.calculateSatisfaction());

        }
        for (Etablissement eta:this.listeEtablissement) {
            System.err.println(eta.toString());
        }

    }
}

