package com.baguete;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe responsável por ler o nome do perfil do Google Chrome
 */
public class GoogleProfileReader {
    private static final String NOME_PADRAO = "Jogador";

    /**
     * Busca silenciosamente o primeiro nome da conta Google configurada no Chrome
     *
     * @return O primeiro nome encontrado ou um padrão
     */
    public static String obterNomeJogador() {
        // Tenta Windows
        String caminhoWindows = expandirCaminho("%LOCALAPPDATA%\\Google\\Chrome\\User Data\\Default\\Preferences");
        String nome = lerNomeDoArquivo(caminhoWindows);
        if (nome != null) {
            return nome;
        }

        // Tenta Linux
        String caminhoLinux = expandirCaminho("~/.config/google-chrome/Default/Preferences");
        nome = lerNomeDoArquivo(caminhoLinux);
        if (nome != null) {
            return nome;
        }

        // Tenta macOS
        String caminhoMac = expandirCaminho("~/Library/Application Support/Google/Chrome/Default/Preferences");
        nome = lerNomeDoArquivo(caminhoMac);
        if (nome != null) {
            return nome;
        }

        // Retorna variáveis de ambiente como fallback
        String nomeUsuario = System.getenv("USERNAME");
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            return nomeUsuario.split(" ")[0];
        }

        nomeUsuario = System.getenv("USER");
        if (nomeUsuario != null && !nomeUsuario.isEmpty()) {
            return nomeUsuario.split(" ")[0];
        }

        return NOME_PADRAO;
    }

    /**
     * Lê o nome do arquivo de preferências do Chrome
     */
    private static String lerNomeDoArquivo(String caminho) {
        try {
            Path arquivo = Paths.get(caminho);
            if (!Files.exists(arquivo)) {
                return null;
            }

            String conteudo = new String(Files.readAllBytes(arquivo));
            JsonObject json = JsonParser.parseString(conteudo).getAsJsonObject();

            if (json.has("profile") && json.getAsJsonObject("profile").has("name")) {
                String nome = json.getAsJsonObject("profile").get("name").getAsString();
                if (nome != null && !nome.isEmpty()) {
                    return nome.split(" ")[0];
                }
            }
        } catch (IOException | com.google.gson.JsonSyntaxException | IllegalStateException e) {
            // Silenciosamente falha e tenta o próximo caminho
        }
        return null;
    }

    /**
     * Expande variáveis de ambiente e tilde no caminho
     */
    private static String expandirCaminho(String caminho) {
        if (caminho.contains("%")) {
            // Windows: substitui %LOCALAPPDATA%
            caminho = caminho.replace("%LOCALAPPDATA%", System.getenv("LOCALAPPDATA") != null ? System.getenv("LOCALAPPDATA") : "");
        }
        if (caminho.startsWith("~")) {
            // Unix: substitui ~ pelo home directory
            caminho = caminho.replaceFirst("~", System.getProperty("user.home"));
        }
        return caminho;
    }
}
