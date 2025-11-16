package com.minhabiblioteca;

import java.io.*;
import java.util.*;

/**
 * Biblioteca para leitura de arquivos CSV
 * Funciona como uma "biblioteca pessoal" .jar
 */
public class CsvReader {
    private String arquivo;
    private List<String[]> dados;
    private String[] cabecalhos;
    
    public CsvReader(String nomeArquivo) {
        this.arquivo = nomeArquivo;
        this.dados = new ArrayList<>();
    }
    
    /**
     * Carrega e parseia o arquivo CSV
     * @return true se carregou com sucesso, false caso contrário
     */
    public boolean carregarArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean primeiraLinha = true;
            
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    cabecalhos = parseLinha(linha);
                    primeiraLinha = false;
                } else {
                    String[] linhaDados = parseLinha(linha);
                    if (linhaDados.length > 0) {
                        dados.add(linhaDados);
                    }
                }
            }
            return true;
            
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Parseia uma linha do CSV, tratando aspas e vírgulas
     */
    private String[] parseLinha(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder campoAtual = new StringBuilder();
        boolean dentroDeAspas = false;
        
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            
            if (c == '"') {
                dentroDeAspas = !dentroDeAspas;
            } else if (c == ',' && !dentroDeAspas) {
                campos.add(campoAtual.toString().trim());
                campoAtual = new StringBuilder();
            } else {
                campoAtual.append(c);
            }
        }
        
        // Adiciona o último campo
        campos.add(campoAtual.toString().trim());
        return campos.toArray(new String[0]);
    }
    
    // ========== MÉTODOS PÚBLICOS PARA QUEM CHAMAR ==========
    
    /**
     * Retorna os cabeçalhos do CSV
     */
    public String[] getCabecalhos() {
        return cabecalhos != null ? cabecalhos.clone() : new String[0];
    }
    
    /**
     * Retorna todos os dados como lista de arrays
     */
    public List<String[]> getTodosDados() {
        return new ArrayList<>(dados);
    }
    
    /**
     * Retorna uma linha específica
     */
    public String[] getLinha(int indice) {
        if (indice >= 0 && indice < dados.size()) {
            return dados.get(indice).clone();
        }
        return new String[0];
    }
    
    /**
     * Retorna o número de linhas de dados (excluindo cabeçalho)
     */
    public int getNumeroLinhas() {
        return dados.size();
    }
    
    /**
     * Busca dados por coluna e valor
     */
    public List<String[]> buscarPorValor(String coluna, String valor) {
        List<String[]> resultados = new ArrayList<>();
        int indiceColuna = -1;
        
        // Encontra o índice da coluna
        for (int i = 0; i < cabecalhos.length; i++) {
            if (cabecalhos[i].equalsIgnoreCase(coluna)) {
                indiceColuna = i;
                break;
            }
        }
        
        if (indiceColuna == -1) return resultados;
        
        // Busca os valores
        for (String[] linha : dados) {
            if (indiceColuna < linha.length && 
                linha[indiceColuna].equalsIgnoreCase(valor)) {
                resultados.add(linha.clone());
            }
        }
        
        return resultados;
    }
    
    /**
     * Retorna dados como uma lista de mapas (chave-valor)
     */
    public List<Map<String, String>> getDadosComoMapa() {
        List<Map<String, String>> resultado = new ArrayList<>();
        
        for (String[] linha : dados) {
            Map<String, String> mapa = new HashMap<>();
            for (int i = 0; i < Math.min(cabecalhos.length, linha.length); i++) {
                mapa.put(cabecalhos[i], linha[i]);
            }
            resultado.add(mapa);
        }
        
        return resultado;
    }
}
