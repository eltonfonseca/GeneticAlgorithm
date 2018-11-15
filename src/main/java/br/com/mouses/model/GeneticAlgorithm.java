/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mouses.model;

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
        }
    }
    
    public void showPopulation(List<Cromossomo> populacao) {
        System.out.println();
        for(int i = 0; i < populacao.size(); i++) {
            System.out.print((i + 1) + " Fitness: " + populacao.get(i).getFitness() + " Gene: ");
            populacao.get(i).show_genes();
        }
    }
    
    public void cruzamento() {
        Cromossomo pai, mae, filho1, filho2;
        int vezes_cruzar = (int) this.nova_populacao.size() / 2;
        
        for(int i = 0; i < vezes_cruzar; i++) {
            pai = this.nova_populacao.get(i);
            mae = this.nova_populacao.get(i + 1);
            filho1 = new Cromossomo(new int[this.config.getQtd_genes()]);
            filho2 = new Cromossomo(new int[this.config.getQtd_genes()]);
            
            filho1.getGenes()[0] = pai.getGenes()[0];
            filho1.getGenes()[this.config.getQtd_genes() - 1] = pai.getGenes()[this.config.getQtd_genes() - 1];
            filho2.getGenes()[0] = pai.getGenes()[0];
            filho2.getGenes()[this.config.getQtd_genes() - 1] = pai.getGenes()[this.config.getQtd_genes() - 1];
            
            for(int j = 1; j < this.config.getQtd_genes() - 1; j++) {
                if(j <= (this.config.getQtd_genes() / 2) - 1) {
                    filho1.getGenes()[j] = pai.getGenes()[j];
                    filho2.getGenes()[j] = mae.getGenes()[j];
                }
                else {
                    filho1.getGenes()[j] = mae.getGenes()[j];
                    filho2.getGenes()[j] = pai.getGenes()[j];
                }
            }
            
            this.nova_populacao.add(filho1);
            this.nova_populacao.add(filho2);
        }
    }
    
    public void mutacao() {
        int qtd_mutacao = (int) (this.nova_populacao.size() * (this.config.getQtd_genes() - 2) * this.config.getTax_mut());
        int randGene = 0;
        
        for(int i = 0; i < qtd_mutacao; i++) {
            rand = new Random();
            int randCromossomo = rand.nextInt(this.nova_populacao.size());
            while(true) {
                randGene = rand.nextInt(this.config.getQtd_genes());
                if(randGene != 0 && randGene != this.config.getQtd_genes() - 1)
                    break;
            }
            int randValor = rand.nextInt(this.config.getQtd_genes());
            this.nova_populacao.get(randCromossomo).getGenes()[randGene] = randValor;
        }
        
        for(int i = 0; i < this.nova_populacao.size(); i++) 
            this.nova_populacao.get(i).setFitness(this.calcularFitness(this.nova_populacao.get(i).getGenes()));
        this.ordenaPopulacaoPorFitness(true);
        this.populacao.clear();
        
        for(int i = 0; i < this.config.getTam_pop(); i++)
            this.populacao.add(this.nova_populacao.get(i));
        this.nova_populacao.clear();
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
