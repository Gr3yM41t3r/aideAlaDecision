package com.example.projetaidedecision.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

public class InterfaceController {

    @FXML
    private Slider capEtablissement;

    @FXML
    private CheckBox capFixe;

    @FXML
    private Button generer;

    @FXML
    private Slider nbEtablissement;

    @FXML
    private Slider nbEtudiant;

    @FXML
    private Slider nbVoeux;

    @FXML
    private CheckBox nbVoeuxFix;

    @FXML
    private Button prioEtabli;

    @FXML
    private Button prioEtu;

    @FXML
    private Text valeurCapEtabli;

    @FXML
    private Text valeurNbEtabli;

    @FXML
    private Text valeurNbEtudiant;

    @FXML
    private Text valeurNbVoeux;


    @FXML
    private void generateProblem(){
        int nombreEtablissement = (int) nbEtablissement.getValue();
        int capaciteEtablissement = (int) capEtablissement.getValue();
        int nombreEtudiant = (int) nbEtudiant.getValue();
        int nombreVoeux = (int) nbVoeux.getValue();
        System.out.println(nombreEtablissement);
        System.out.println(capaciteEtablissement);
        System.out.println(nombreEtudiant);
        System.out.println(nombreVoeux);

    }

}
