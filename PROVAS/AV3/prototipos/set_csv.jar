package com.minhabiblioteca.csv;

import java.io.*;
import java.util.*;

/**
 * Biblioteca para verificar e criar arquivos CSV
 * Equivalente a uma biblioteca .o em C
 */
public class CsvValidator {
    
    /**
     * Verifica se o arquivo CSV existe. Se não existir, cria com cabeçalhos.
     * @param nomeArquivo Nome do arquivo CSV
     * @param cabecalhos Array com os nomes das colunas
     * @return true se arquivo já existia, false se foi criado
     */
    public static boolean verificarOuCriarCsv(String nomeArquivo, String[] cabecalhos) {
        File arquivo = new File(nomeArquivo);
        
        if (arquivo.exists()) {
            System.out.println("✅ Arquivo '" + nomeArquivo + "' já existe.");
            return true;
        } else {
            try {
                if (arquivo.createNewFile()) {
                    System.out.println("📄 Criando novo arquivo: " + nomeArquivo);
                    escreverCabecalhos(nomeArquivo, cabecalhos);
                    return false;
                }
            } catch (IOException e) {
                System.err.println("❌ Erro ao criar arquivo: " + e.getMessage());
            }
        }
        return false;
    }
    
    /**
     * Verifica se o arquivo existe (sobrecarga sem criar)
     */
    public static boolean arquivoExiste(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        boolean existe = arquivo.exists();
        System.out.println("📁 Arquivo '" + nomeArquivo + "' existe? " + existe);
        return existe;
    }
    
    /**
     * Cria arquivo CSV com cabeçalhos específicos
     */
    public static boolean criarCsvComCabecalhos(String nomeArquivo, String[] cabecalhos) {
        try {
            File arquivo = new File(nomeArquivo);
            if (arquivo.createNewFile()) {
                escreverCabecalhos(nomeArquivo, cabecalhos);
                System.out.println("✅ CSV criado: " + nomeArquivo);
                return true;
            } else {
                System.out.println("⚠️ Arquivo já existe ou não pôde ser criado: " + nomeArquivo);
                return false;
            }
        } catch (IOException e) {
            System.err.println("❌ Erro: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Escreve cabeçalhos no arquivo CSV
     */
    private static void escreverCabecalhos(String nomeArquivo, String[] cabecalhos) {
        try (FileWriter writer = new FileWriter(nomeArquivo)) {
            for (int i = 0; i < cabecalhos.length; i++) {
                writer.append(cabecalhos[i]);
                if (i < cabecalhos.length - 1) {
                    writer.append(";");
                }
            }
            writer.append("\n");
            System.out.println("📝 Cabeçalhos escritos: " + Arrays.toString(cabecalhos));
        } catch (IOException e) {
            System.err.println("❌ Erro ao escrever cabeçalhos: " + e.getMessage());
        }
    }
    
    /**
     * Adiciona uma linha de dados ao CSV
     */
    public static void adicionarLinha(String nomeArquivo, String[] dados) {
        try (FileWriter writer = new FileWriter(nomeArquivo, true)) {
            for (int i = 0; i < dados.length; i++) {
                writer.append(dados[i]);
                if (i < dados.length - 1) {
                    writer.append(";");
                }
            }
            writer.append("\n");
            System.out.println("➕ Linha adicionada: " + Arrays.toString(dados));
        } catch (IOException e) {
            System.err.println("❌ Erro ao adicionar linha: " + e.getMessage());
        }
    }
    
    /**
     * Verifica múltiplos arquivos de uma vez
     */
    public static void verificarMultiplosArquivos(Map<String, String[]> arquivosECabecalhos) {
        System.out.println("\n🔍 Verificando múltiplos arquivos...");
        for (Map.Entry<String, String[]> entry : arquivosECabecalhos.entrySet()) {
            String arquivo = entry.getKey();
            String[] cabecalhos = entry.getValue();
            verificarOuCriarCsv(arquivo, cabecalhos);
        }
    }
}
