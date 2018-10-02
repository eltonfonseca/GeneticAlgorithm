/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mouses.model;

/**
 *
 * @author elton
 */
public class Cromossomo {
    
    private final int[] genes;
    private int fitness;
    
    public Cromossomo(int[] genes) {
        this.genes = genes;
    }
    
    public int[] getGenes() {
        return this.genes;
    }
    
    public void setFitness(int value) {
        this.fitness = value;
    }
    
    public int getFitness() {
        return this.fitness;
    }
    
    public void show_genes() {
        for(int gene: this.genes) {
            System.out.print(gene + " ");
        }
        System.out.println();
    }
}
