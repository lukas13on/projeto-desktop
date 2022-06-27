package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class arquivos {

    static String pastaBase = "arquivos/";

    /**
     * Le um arquivo
     * 
     * @param nomeArquivo
     */
    public static String lerArquivo(String nomeArquivo) throws Exception {
        try {

            File arquivo = new File(nomeArquivo);
            Scanner entrada = new Scanner(arquivo);
            String dados = "";

            while (entrada.hasNextLine()) {
                dados = dados.concat(entrada.nextLine());
            }

            entrada.close();
            return dados;
        } catch (FileNotFoundException e) {
            return null;
        }

    }

    /**
     * Apaga um arquivo
     * 
     * @param nomeArquivo
     * @return boolean
     */
    public static boolean apagarArquivo(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (arquivo.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Escreve em um arquivo
     * 
     * @param nomeArquivo
     * @param dadosArquivo
     * @throws Exception
     * @return boolean
     */
    public static boolean escreverArquivo(String nomeArquivo, String dadosArquivo) throws Exception {
        try {
            FileWriter escritora = new FileWriter(nomeArquivo);
            escritora.write(dadosArquivo, 0, dadosArquivo.length());
            escritora.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Cria um arquivo
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

            if (dadosArquivo != "" || dadosArquivo != null) {
                preenchido = escreverArquivo(nomeArquivo, dadosArquivo);
                return criado && preenchido;
            }

            return criado;
        } else {
            return true;
        }
    }

    /**
     * Determina se um arquivo existe
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

    /**
     * Retorna um array com o resultado da pesquisa
     * 
     * @param nomePasta
     * @param termo
     * @return
     * @throws IOException
     */
    public static List<String> procurarArquivos(String nomePasta, String termo) throws IOException {
        Path pasta = Paths.get(nomePasta);
        List<String> list = new ArrayList<String>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(pasta, termo)) {

            for (Path path : paths) {
                list.add(path.toString());
            }

            return list;
        } catch (IOException e) {
            return null;
        }
    }

    public static String toCSV(String[] array) {
        String result = "";
        if (array.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (String s : array) {
                sb.append(s).append(",");
            }
            result = sb.deleteCharAt(sb.length() - 1).toString();
        }
        return result;
    }


}