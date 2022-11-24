package com.example.projetaidedecision.Controllers;

import com.example.projetaidedecision.Models.Etablissement;
import com.example.projetaidedecision.Models.Etudiant;
import com.example.projetaidedecision.Utils.FileParser;
import com.example.projetaidedecision.algorithms.PreferencesGenerator;
import com.example.projetaidedecision.algorithms.StableMariage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.security.cert.Extension;
import java.util.ArrayList;

public class InterfaceController {

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
    private Button openProblem;

    @FXML
    private Button saveProblem;

    @FXML
    private TextArea solutionEtablissement;

    @FXML
    private TextArea solutionEtudiant;

    @FXML
    private Button solvePriEta;

    @FXML
    private Button solvePriEtu;

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
    StableMariage mariage;

    ArrayList<Etablissement> listeEtablissement;
    ArrayList<Etudiant> listEtudiant;

    @FXML
    private void generateProblem() throws Exception {
        reset();
        PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList((int)nbEtudiant.getValue());
        preferencesGenerator.createUniversityList((int)nbEtablissement.getValue(),(int) capEtablissement.getValue(),capFixe.isSelected());
        preferencesGenerator.generateStudentsChoices((int) nbVoeux.getValue());
        preferencesGenerator.generateUniversitiesChoices((int)nbPreferences.getValue());
        listEtudiant = preferencesGenerator.getStudentList();
        listeEtablissement = preferencesGenerator.getUniversityList();
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        for (Etudiant etudiant:listEtudiant) {
            etudiantProblemeText.appendText(etudiant.printInProblemFormat()+"\n");
        }
        for (Etablissement etablissement:listeEtablissement) {
            etablissementProblemeTexte.appendText(etablissement.printInProblemFormat()+"\n");
        }
        tabPrincipale.getSelectionModel().select(tabProbleme);
    }

    @FXML
    public void solvePriEtu() throws Exception {
        System.out.println("solve2");
        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant,listeEtablissement);
        mariage.solve2();
        showSolutions();

    }

    @FXML
    public void solvePriEta(){
        System.out.println("solve");

        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant,listeEtablissement);
        mariage.solve();
        showSolutions();
    }

    @FXML
    public void openAndParseFile(){
        reset();
        FileChooser fx = new FileChooser();
        fx.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt","*.txt"));
        File f = fx.showOpenDialog(null);
        FileParser fileParser = new FileParser();
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        if (f!=null){
            fileParser.parse(f.getPath());
            System.err.println(f.getPath());
            for (Etudiant etudiant:fileParser.getEtudiants()) {
                etudiantProblemeText.appendText(etudiant.printInProblemFormat()+"\n");
            }
            for (Etablissement etablissement:fileParser.getEtablissements()) {
                etablissementProblemeTexte.appendText(etablissement.printInProblemFormat()+"\n");
            }
        }
        listeEtablissement = fileParser.getEtablissements();
        listEtudiant = fileParser.getEtudiants();

    }

    @FXML
    public void showSolutions(){
        solutionEtablissement.clear();
        solutionEtudiant.clear();
        for (Etablissement et:listeEtablissement) {
            solutionEtablissement.appendText(et.toString()+"\n");
        }
        for (Etudiant et:listEtudiant) {
            solutionEtudiant.appendText(et.toString()+"\n");

        }
        tabPrincipale.getSelectionModel().select(tabSolution);


    }

    public void reset(){
        this.listEtudiant=null;
        this.listeEtablissement=null;
    }





}
