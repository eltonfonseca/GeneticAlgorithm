package br.com.mouses.view;

import br.com.mouses.controller.ConfigurationController;
import br.com.mouses.controller.GeneticAlgorithm;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        ConfigurationController config = new ConfigurationController(5, 2, 4, 100, 1, 1, "teste", 4);
        GeneticAlgorithm ga = new GeneticAlgorithm(config.getConfig());
        ga.populacaoInicial();
        ga.showPopulation();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
