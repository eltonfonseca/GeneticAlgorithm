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
public class Configuration {
    
    private final int qtd_genes;
    private final int origem;
    private final int destino;
    private final int tam_pop;
    private final int tax_cruz;
    private final int tax_mut;
    private final String selecao;
    private final int geracoes;

    public Configuration(int qtd_genes, int origem, int destino, int tam_pop, int tax_cruz, int tax_mut, String selecao, int geracoes) {
        this.qtd_genes = qtd_genes;
        this.origem = origem;
        this.destino = destino;
        this.tam_pop = tam_pop;
        this.tax_cruz = tax_cruz;
        this.tax_mut = tax_mut;
        this.selecao = selecao;
        this.geracoes = geracoes;
    }
    
    public int getQtd_genes() {
        return this.qtd_genes;
    }
    
    public int getOrigem() {
        return this.origem;
    }

    public int getDestino() {
        return this.destino;
    }

    public int getTam_pop() {
        return this.tam_pop;
    }

    public int getTax_cruz() {
        return this.tax_cruz;
    }

    public int getTax_mut() {
        return this.tax_mut;
    }

    public String getSelecao() {
        return this.selecao;
    }
    
    public int getGeracoes() {
        return this.geracoes;
    }
}
