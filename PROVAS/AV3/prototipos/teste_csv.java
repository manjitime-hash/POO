package com.minhabiblioteca.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvManager {

    /**
     * Verifica se o arquivo CSV existe. Se não existir, cria o arquivo com os cabeçalhos fornecidos.
     * @param nomeArquivo O nome do arquivo CSV.
     * @param cabecalhos Um array de strings com os cabeçalhos das colunas.
     * @return true se o arquivo foi criado, false se o arquivo já existia.
     */
    public static boolean verificarOuCriarCsv(String nomeArquivo, String[] cabecalhos) {
        File arquivo = new File(nomeArquivo);
        
        if (arquivo.exists()) {
            System.out.println("Arquivo " + nomeArquivo + " já existe.");
            return false;
        } else {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("Arquivo " + nomeArquivo + " criado com sucesso.");
                    // Escreve os cabeçalhos
                    escreverLinhaCsv(nomeArquivo, cabecalhos);
                    return true;
                } else {
                    System.out.println("Falha ao criar o arquivo " + nomeArquivo);
                    return false;
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                return false;
            }
        }
    }

    /**
     * Escreve uma linha no arquivo CSV.
     * @param nomeArquivo O nome do arquivo CSV.
     * @param dados Um array de strings com os dados da linha.
     */
    public static void escreverLinhaCsv(String nomeArquivo, String[] dados) {
        try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
            for (int i = 0; i < dados.length; i++) {
                writer.append(dados[i]);
                if (i < dados.length - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
            System.out.println("Linha escrita no arquivo " + nomeArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    // Método main para teste
    public static void main(String[] args) {
        // Exemplo de uso
        String[] cabecalhos = {"ID", "Nome", "Idade"};
        verificarOuCriarCsv("exemplo.csv", cabecalhos);
        
        // Escrevendo algumas linhas
        String[] linha1 = {"1", "João", "30"};
        String[] linha2 = {"2", "Maria", "25"};
        escreverLinhaCsv("exemplo.csv", linha1);
        escreverLinhaCsv("exemplo.csv", linha2);
    }
}
