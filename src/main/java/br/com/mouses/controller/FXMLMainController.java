/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mouses.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author elton
 */
public class FXMLMainController implements Initializable {

    @FXML
    private JFXButton botaoEvoluir;
    @FXML
    private JFXTextField tam_field;
    @FXML
    private JFXTextField origem_field;
    @FXML
    private JFXTextField destino_field;
    @FXML
    private JFXComboBox<Integer> pop_box;
    @FXML
    private JFXComboBox<Integer> cruz_box;
    @FXML
    private JFXComboBox<Integer> mut_box;
    @FXML
    private JFXComboBox<String> selecao_box;
    @FXML
    private JFXComboBox<Integer> geracoes_box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        pop_box.getItems().addAll(10, 100, 500, 1000, 5000);
        pop_box.setValue(10);
        
        cruz_box.getItems().addAll(80, 85, 90);
        cruz_box.setValue(80);
        
        mut_box.getItems().addAll(1, 3, 5);
        mut_box.setValue(1);
        
        selecao_box.getItems().addAll("Rank", "Rank + Torneio");
        selecao_box.setValue("Rank");
        
        geracoes_box.getItems().addAll(100, 250, 500, 1000, 5000);
        geracoes_box.setValue(100);
    }

    @FXML
    private void evoluir(ActionEvent event) {
                
        int tamanho = Integer.parseInt(tam_field.getText());
        int origem = Integer.parseInt(origem_field.getText());
        int destino = Integer.parseInt(destino_field.getText());
        int populacao = pop_box.getSelectionModel().getSelectedItem();
        int cruzamento = cruz_box.getSelectionModel().getSelectedItem();
        int mutacao = mut_box.getSelectionModel().getSelectedItem();
        String selecao = selecao_box.getSelectionModel().getSelectedItem();
        int geracoes = geracoes_box.getSelectionModel().getSelectedItem();
                
        ConfigurationController config = new ConfigurationController(tamanho, origem, destino, populacao, cruzamento, mutacao, selecao, geracoes);
        GeneticAlgorithm ga = new GeneticAlgorithm(config.getConfig());
        
        System.out.println("Gerando população inicial...");
        ga.populacaoInicial();
        ga.showPopulation();
        ga.seleciona();
    }
}
