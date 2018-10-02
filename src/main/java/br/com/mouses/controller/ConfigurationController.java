/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mouses.controller;

import br.com.mouses.model.Configuration;

/**
 *
 * @author elton
 */
public class ConfigurationController {
    
    private final Configuration config;
    
    public ConfigurationController(int qtd_genes, int origem, int destino, int tam_pop, int tax_cruz, int tax_mut, String selecao, int geracoes) {
        config = new Configuration(qtd_genes, origem, destino, tam_pop, tax_cruz, tax_mut, selecao, geracoes);
    }
    
    public Configuration getConfig() {
        return this.config;
    }
}
