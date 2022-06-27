package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class linhas {

    public static boolean criarLinha() throws Exception {
        Scanner entrada = new Scanner(System.in);
        Boolean linhaExiste = false;

        while (!linhaExiste) {

            String nomeLinhaPartida = "";
            String nomeLinhaDestino = "";
            String nomeLinhaArquivo = "";
            String nomeLimpo = "";

            System.out.println("------------CADASTRO-DE-LINHAS----------");

            System.out.println("Informe um nome para a linha de destino:");
            nomeLinhaPartida = entrada.next();

            menu.divisor();

            System.out.println("Informe um nome para a linha de destino:");
            nomeLinhaDestino = entrada.next();

            menu.divisor();

            nomeLimpo = nomeLinhaPartida + "-" + nomeLinhaDestino;
            nomeLimpo = nomeLimpo.trim().toLowerCase();

            nomeLinhaArquivo += arquivos.pastaBase;
            nomeLinhaArquivo += nomeLimpo + ".csv";

            linhaExiste = arquivos.existeArquivo(nomeLinhaArquivo);

            if (!linhaExiste) {
                linhaExiste = arquivos.criarArquivo(nomeLinhaArquivo, "");
                menu.limpar(0);
            } else {
                linhaExiste = false;
                System.out.println("Essa linha já existe.");
                menu.limpar(3);
            }

        }

        return true;
    }

    /**
     * Retorna o codigo da linha escolhida
     * 
     * @return String
     * @throws IOException
     * @throws InterruptedException
     */
    public static List<String> escolherLinha() throws IOException, InterruptedException {
        try {

            Scanner entrada = new Scanner(System.in);
            String nomePasta = arquivos.pastaBase;
            String termo = "*.csv";
            List<String> arquivos = src.arquivos.procurarArquivos(nomePasta, termo);
            List<String> partidas = new ArrayList<String>();
            List<String> destinos = new ArrayList<String>();
            int codigoEscolhido = -1;
            Boolean linhaEscolhida = false;
            String partida = null;
            String destino = null;
            List<String> despachaDados = new ArrayList<String>();
            int posicao;

            menu.limpar(0);

            menu.divisor();
            menu.titulo("Escolha uma linha");
            menu.divisor();

            for (posicao = 0; posicao < arquivos.size(); posicao++) {
                String nomeArquivo = arquivos.get(posicao).split(Pattern.quote("\\"))[1];
                nomeArquivo = nomeArquivo.replace(".csv", "");

                String[] campos = nomeArquivo.split("-");
                partida = campos[0];
                destino = campos[1];
                partida = partida.substring(0, 1).toUpperCase() + partida.substring(1);
                destino = destino.substring(0, 1).toUpperCase() + destino.substring(1);
                partidas.add(partida);
                destinos.add(destino);

                String lin = Integer.toString(posicao);
                String linhaCodigo = posicao >= 10 ? lin : "0" + lin;

                System.out.println(" | " + linhaCodigo + " | " + partida + " - " + destino);

                menu.divisor();
            }

            while (!linhaEscolhida) {
                // System.out.println("Informe o código da linha:");
                codigoEscolhido = entrada.nextInt();

                if (codigoEscolhido >= 0 && codigoEscolhido < arquivos.size()) {
                    despachaDados.add(String.valueOf(codigoEscolhido));
                    despachaDados.add(arquivos.get(codigoEscolhido));
                    linhaEscolhida = true;
                }
            }

            partida = partidas.get(codigoEscolhido);
            destino = destinos.get(codigoEscolhido);

            menu.divisor();
            menu.resposta("[" + partida + " -> " + destino + "]");
            menu.limpar(0);

            return despachaDados;

        } catch (IOException e) {
            return null;
        }

    }

}
