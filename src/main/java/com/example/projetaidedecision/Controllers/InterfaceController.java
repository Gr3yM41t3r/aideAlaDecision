package com.example.projetaidedecision.Controllers;

import com.example.projetaidedecision.Models.Etablissement;
import com.example.projetaidedecision.Models.Etudiant;
import com.example.projetaidedecision.algorithms.PreferencesGenerator;
import com.example.projetaidedecision.algorithms.StableMariage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class InterfaceController {


    StableMariage mariage;
    @FXML
    public Button solvePriEtu;
    @FXML
    public Button solvePriEta;
    @FXML
    private Slider capEtablissement;

    @FXML
    private CheckBox capFixe;

    @FXML
    private TextArea etablissementProblemeTexte;

    @FXML
    private TextArea etudiantProblemeText;

    @FXML
    private Button generer;

    @FXML
    private Slider nbEtablissement;

    @FXML
    private Slider nbEtudiant;

    @FXML
    private Slider nbPreferences;

    @FXML
    private Slider nbVoeux;

    @FXML
    private CheckBox nbVoeuxFix;

    @FXML
    private Tab tabGeneration;

    @FXML
    private TabPane tabPrincipale;

    @FXML
    private Tab tabProbleme;

    @FXML
    private Tab tabSolution;

    @FXML
    private Text valeurCapEtabli;

    @FXML
    private Text valeurNbEtabli;

    @FXML
    private Text valeurNbEtudiant;

    @FXML
    private Text valeurNbPreference;

    @FXML
    private Text valeurNbVoeux;
    @FXML
    private void generateProblem() throws Exception {
        PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList((int)nbEtudiant.getValue());
        preferencesGenerator.createUniversityList((int)nbEtablissement.getValue(),(int) capEtablissement.getValue(),capFixe.isSelected());
        preferencesGenerator.generateStudentsChoices((int) nbVoeux.getValue());
        preferencesGenerator.generateUniversitiesChoices((int)nbPreferences.getValue());
        ArrayList<Etudiant> listEtudiant = preferencesGenerator.getStudentList();
        ArrayList<Etablissement> listeEtablissement = preferencesGenerator.getUniversityList();
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        for (Etudiant etudiant:listEtudiant) {
            etudiantProblemeText.appendText(etudiant.printInProblemFormat()+"\n");
        }

        for (Etablissement etablissement:listeEtablissement) {
            etablissementProblemeTexte.appendText(etablissement.printInProblemFormat()+"\n");

        }
        tabPrincipale.getSelectionModel().select(tabProbleme);
         mariage = new StableMariage(listEtudiant,listeEtablissement);
    }

    @FXML
    public void solvePriEtu() throws Exception {
        mariage.solve2();

    }

    @FXML
    public void solvePriEta(){
        mariage.solve();

    }


    @FXML
    public void onSliderChange(){
        valeurNbEtabli.setText(String.valueOf((int)nbEtablissement.getValue()));
        valeurCapEtabli.setText(String.valueOf((int)capEtablissement.getValue()));
        valeurNbEtudiant.setText(String.valueOf((int)nbEtudiant.getValue()));
        valeurNbVoeux.setText(String.valueOf((int)nbVoeux.getValue()));
    }

}
