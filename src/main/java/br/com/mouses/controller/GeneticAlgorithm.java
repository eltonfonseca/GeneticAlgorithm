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
    private final List<Cromossomo> nova_populacao;
    private final Configuration config;
    private int[] genes;
    private Random rand;
    
    public GeneticAlgorithm(Configuration config) {
        this.config = config;
        this.populacao = new ArrayList<>();
        this.nova_populacao = new ArrayList<>(); 
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
        ordenaPopulacaoPorFitness(false);
    }
    
    public void seleciona() {
        
        int tamanho_nova_populacao = this.config.getTax_cruz() * this.populacao.size() / 100;
                
        if(this.config.getSelecao().equals("Rank")) {
            for(int i = 0; i < tamanho_nova_populacao; i++) {
                this.nova_populacao.add(this.populacao.get(i));
            }
        }
        else {
            int elitismo = tamanho_nova_populacao * 10 / 100;
            
            for(int i = 0; i < elitismo; i++) {
                this.nova_populacao.add(this.populacao.get(i));
                System.out.println("Adicionando por Rank");
            }
            
            System.out.println("Agora adicionando de forma randomica");
            
            while(this.nova_populacao.size() != tamanho_nova_populacao) {
                rand = new Random();
                int randA = (int) rand.nextInt(this.populacao.size());
                int randB = (int) rand.nextInt(this.populacao.size());
                Cromossomo a = this.populacao.get(randA);
                Cromossomo b = this.populacao.get(randB);
                
                if(a.getFitness() <= b.getFitness())
                    this.nova_populacao.add(a);
                else
                    this.nova_populacao.add(b);
            }
            
            ordenaPopulacaoPorFitness(true);
        }
    }
    
    public void showPopulation(List<Cromossomo> populacao) {
        System.out.println();
        for(int i = 0; i < populacao.size(); i++) {
            System.out.print((i + 1) + " Fitness: " + populacao.get(i).getFitness() + " Gene: ");
            populacao.get(i).show_genes();
        }
    }
    
    private void ordenaPopulacaoPorFitness(boolean ordenaNovaPopulacao) {
        if(ordenaNovaPopulacao)
            this.nova_populacao.sort((Cromossomo a, Cromossomo b) -> a.getFitness() - b.getFitness());
        else
            this.populacao.sort((Cromossomo a, Cromossomo b) -> a.getFitness() - b.getFitness());
    }
    
    public List<Cromossomo> getPopulacao() {
        return this.populacao;
    }
    
    public List<Cromossomo> getNovaPopulacao() {
        return this.nova_populacao;
    }
}
