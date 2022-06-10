package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class arquivos {

    public static void lerArquivo() {

    }

    public static void apagarArquivo() {

    }

    public static void escreverArquivo() {

    }

    /**
     * Cria um arquivo com o nome e dados.
     * 
     * @param nomeArquivo
     * @param dadosArquivo
     * @throws Exception
     * @return boolean
     */
    public static boolean criarArquivo(String nomeArquivo, String dadosArquivo) throws Exception {

        boolean criado = false;
        boolean preenchido = false;

        if (!arquivos.existeArquivo(nomeArquivo)) {

            File arquivo = new File(nomeArquivo);
            if (arquivo.createNewFile()) {
                criado = true;
            }

            if (dadosArquivo != "") {

            }

            return criado;

        } else {
            return true;
        }
    }

    /**
     * Determina se um arquivo existe.
     * 
     * @param nomeArquivo
     * @throws Exception
     * @return boolean
     */
    public static boolean existeArquivo(String nomeArquivo) throws Exception {
        File arquivo = new File(nomeArquivo);
        if (arquivo.exists() && !arquivo.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

}