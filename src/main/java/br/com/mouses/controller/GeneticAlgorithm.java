/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mouses.controller;

import br.com.mouses.model.Configuration;
import br.com.mouses.model.Cromossomo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author elton
 */
public class GeneticAlgorithm {
    
    private final List<Cromossomo> populacao;
    private final Configuration config;
    private int[] genes;
    private Random rand;
    
    public GeneticAlgorithm(Configuration config) {
        this.config = config;
        this.populacao = new ArrayList<>();
    }
    
    private int[] geraGenesRandomicos() {
        this.genes = new int[this.config.getQtd_genes()];
        rand = new Random();
        
        this.genes[0] = config.getOrigem();
        this.genes[this.genes.length - 1] = config.getDestino();       
        for(int i = 1; i < this.genes.length - 1; i++) {
            this.genes[i] = (int) rand.nextInt(this.config.getQtd_genes()) + 1;
        }      
        return this.genes;
    }
    
    private int calcularFitness(int[] genes) {
        int fitness = 0;
        
        for(int i = 0; i < genes.length - 1; i++) {
            fitness += Math.abs(genes[i] - genes[i + 1]) + 1;
        }
        return fitness;
    }
    
    public void populacaoInicial() {
        for(int i = 0; i < config.getTam_pop(); i++) {
            Cromossomo cromossomo = new Cromossomo(geraGenesRandomicos());
            cromossomo.setFitness(calcularFitness(cromossomo.getGenes()));
            populacao.add(cromossomo);
        }
        ordenaPopulacaoPorFitness();
    }
    
    public void showPopulation() {
        for(int i = 0; i < this.populacao.size(); i++) {
            System.out.print((i + 1) + " Fitness: " + populacao.get(i).getFitness() + " Gene: ");
            populacao.get(i).show_genes();
        }
    }
    
    private void ordenaPopulacaoPorFitness() {
        this.populacao.sort((Cromossomo a, Cromossomo b) -> a.getFitness() - b.getFitness());
    }
}
