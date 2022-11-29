package com.example.projetaidedecision.Controllers;

import com.example.projetaidedecision.Models.Etablissement;
import com.example.projetaidedecision.Models.Etudiant;
import com.example.projetaidedecision.Models.Score;
import com.example.projetaidedecision.Utils.FileParser;
import com.example.projetaidedecision.algorithms.PreferencesGenerator;
import com.example.projetaidedecision.algorithms.StableMariage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class InterfaceController implements Initializable {

    StableMariage mariage;
    ArrayList<Etablissement> listeEtablissement;
    ArrayList<Etudiant> listEtudiant;
    @FXML
    private Text avgEtab;

    @FXML
    private Text avgStudent;

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
    private Button shuffleButton;

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

    @FXML
    private TableColumn<Score, String> satisfaction;
    @FXML
    private TableColumn<Score, String> version;

    @FXML
    private TableView<Score> comparaisonTable;

    private ObservableList<Score> getScoreListe(){
        ObservableList<Score> scores = FXCollections.observableArrayList();
        return scores;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        version.setCellValueFactory(new PropertyValueFactory<>("name"));
        satisfaction.setCellValueFactory(new PropertyValueFactory<>("score"));
        comparaisonTable.setItems(getScoreListe());
        satisfaction.setSortType(TableColumn.SortType.DESCENDING);

        comparaisonTable.getSortOrder().add(satisfaction);

    }

    @FXML
    private void generateProblem() throws Exception {
        reset();
        PreferencesGenerator preferencesGenerator = new PreferencesGenerator();
        preferencesGenerator.createStudentList((int) nbEtudiant.getValue());
        preferencesGenerator.createUniversityList((int) nbEtablissement.getValue(), (int) capEtablissement.getValue(), capFixe.isSelected());
        preferencesGenerator.generateStudentsChoices((int) nbVoeux.getValue());
        preferencesGenerator.generateUniversitiesChoices((int) nbPreferences.getValue());
        listEtudiant = preferencesGenerator.getStudentList();
        listeEtablissement = preferencesGenerator.getUniversityList();
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        for (Etudiant etudiant : listEtudiant) {
            etudiantProblemeText.appendText(etudiant.printInProblemFormat() + "\n");
        }
        for (Etablissement etablissement : listeEtablissement) {
            etablissementProblemeTexte.appendText(etablissement.printInProblemFormat() + "\n");
        }
        tabPrincipale.getSelectionModel().select(tabProbleme);
    }

    @FXML
    public void solvePriEta() throws Exception {
        comparaisonTable.getItems().clear();

        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant, listeEtablissement);
        mariage.solve2();
        double avgStuden=0.0;
        double avgEtabl=0.0;
        for (Etablissement et : listeEtablissement) {
            avgEtabl+=et.calculateSatisfaction();

        }
        for (Etudiant et : listEtudiant) {
            avgStuden+=et.calculateSatisfaction();
        }

        comparaisonTable.getItems().add( new Score("prioEtablissement-Etudiant",new DecimalFormat("##.##").format((avgStuden/listEtudiant.size()))+" %"));
        comparaisonTable.getItems().add( new Score("prioEtablissement-Etablissemnt",new DecimalFormat("##.##").format((avgEtabl/listeEtablissement.size()))+" %"));
        showSolutions();
        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant, listeEtablissement);
        mariage.solve();
        avgStuden=0.0;
        avgEtabl=0.0;
        for (Etablissement et : listeEtablissement) {
            avgEtabl+=et.calculateSatisfaction();

        }
        for (Etudiant et : listEtudiant) {
            avgStuden+=et.calculateSatisfaction();
        }

        comparaisonTable.getItems().add( new Score("prioEtudiant-Etudiant",new DecimalFormat("##.##").format((avgStuden/listEtudiant.size()))+" %"));
        comparaisonTable.getItems().add( new Score("prioEtudiant-Etablissemnt",new DecimalFormat("##.##").format((avgEtabl/listeEtablissement.size()))+" %"));
        comparaisonTable.sort();


    }

    @FXML
    public void solvePriEtu() throws Exception {
        comparaisonTable.getItems().clear();
        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant, listeEtablissement);
        mariage.solve();
        showSolutions();
        double avgStuden=0.0;
        double avgEtabl=0.0;
        for (Etablissement et : listeEtablissement) {
            avgEtabl+=et.calculateSatisfaction();

        }
        for (Etudiant et : listEtudiant) {
            avgStuden+=et.calculateSatisfaction();
        }


        comparaisonTable.getItems().add( new Score("prioEtudiant-Etudiant",new DecimalFormat("##.##").format((avgStuden/listEtudiant.size()))+" %"));
        comparaisonTable.getItems().add( new Score("prioEtudiant-Etablissemnt",new DecimalFormat("##.##").format((avgEtabl/listeEtablissement.size()))+" %"));
        listEtudiant.forEach(Etudiant::resetAssignement);
        listeEtablissement.forEach(Etablissement::resetAssignement);
        mariage = new StableMariage(listEtudiant, listeEtablissement);
        mariage.solve2();
        avgStuden=0.0;
        avgEtabl=0.0;
        for (Etablissement et : listeEtablissement) {
            avgEtabl+=et.calculateSatisfaction();

        }
        for (Etudiant et : listEtudiant) {
            avgStuden+=et.calculateSatisfaction();
        }

        comparaisonTable.getItems().add( new Score("prioEtablissement-Etudiant",new DecimalFormat("##.##").format((avgStuden/listEtudiant.size()))+" %"));
        comparaisonTable.getItems().add( new Score("prioEtablissement-Etablissemnt",new DecimalFormat("##.##").format((avgEtabl/listeEtablissement.size()))+" %"));
        comparaisonTable.sort();
    }

    @FXML
    public void openAndParseFile() {
        reset();
        FileChooser fx = new FileChooser();
        fx.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", "*.txt"));
        File f = fx.showOpenDialog(null);
        FileParser fileParser = new FileParser();
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        if (f != null) {
            fileParser.parse(f.getPath());
            System.err.println(f.getPath());

            for (Etudiant etudiant : fileParser.getEtudiants()) {
                etudiantProblemeText.appendText(etudiant.printInProblemFormat() + "\n");
            }
            for (Etablissement etablissement : fileParser.getEtablissements()) {
                etablissementProblemeTexte.appendText(etablissement.printInProblemFormat() + "\n");
            }
        }
        listeEtablissement = fileParser.getEtablissements();
        listEtudiant = fileParser.getEtudiants();

    }

    @FXML
    public void createAndSaveFile() throws FileNotFoundException {
        FileChooser fx = new FileChooser();
        fx.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt", ".csv"));
        File f = fx.showSaveDialog(null);
        File file;
        if (!f.getName().contains(".")) {
            file = new File(f.getAbsolutePath() + ".txt");
            PrintWriter outFile = new PrintWriter(file);
            for (Etudiant et : listEtudiant) {
                outFile.println(et.printInProblemFormat());

            }
            for (Etablissement et : listeEtablissement) {
                outFile.println(et.printInProblemFormat());

            }
            outFile.close();
        }


    }

    @FXML
    public void showSolutions() {
        solutionEtablissement.clear();
        solutionEtudiant.clear();
        double avgStuden=0.0;
        double avgEtabl=0.0;
        for (Etablissement et : listeEtablissement) {
            solutionEtablissement.appendText(et.toString() + "\n");
            avgEtabl+=et.calculateSatisfaction();

        }
        for (Etudiant et : listEtudiant) {
            solutionEtudiant.appendText(et.toString() + "\n");
            avgStuden+=et.calculateSatisfaction();

        }
        avgStudent.setText(new DecimalFormat("##.##").format((avgStuden/listEtudiant.size()))+" %");
        avgEtab.setText(new DecimalFormat("##.##").format((avgEtabl/listeEtablissement.size()))+" %");


        tabPrincipale.getSelectionModel().select(tabSolution);


    }

    public void reset() {
        this.listEtudiant = null;
        this.listeEtablissement = null;
    }

    public void shuffle() {
        Collections.shuffle(listeEtablissement);
        Collections.shuffle(listEtudiant);
        etablissementProblemeTexte.clear();
        etudiantProblemeText.clear();
        for (Etudiant etudiant : listEtudiant) {
            etudiantProblemeText.appendText(etudiant.printInProblemFormat() + "\n");
        }
        for (Etablissement etablissement : listeEtablissement) {
            etablissementProblemeTexte.appendText(etablissement.printInProblemFormat() + "\n");
        }

    }



}
