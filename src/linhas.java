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

            utilidades.divisorConsole();

            System.out.println("Informe um nome para a linha de destino:");
            nomeLinhaDestino = entrada.next();

            utilidades.divisorConsole();

            nomeLimpo = nomeLinhaPartida + "-" + nomeLinhaDestino;
            nomeLimpo = nomeLimpo.trim().toLowerCase();

            nomeLinhaArquivo += arquivos.pastaBase;
            nomeLinhaArquivo += nomeLimpo + ".csv";

            linhaExiste = arquivos.existeArquivo(nomeLinhaArquivo);

            if (!linhaExiste) {
                linhaExiste = arquivos.criarArquivo(nomeLinhaArquivo, "");
                utilidades.limparConsole(0);
            } else {
                linhaExiste = false;
                System.out.println("Essa linha já existe.");
                utilidades.limparConsole(3);
            }

        }

        return true;
    }

    public static int escolhaLinha() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite a linha desejada: ");
        int linha = entrada.nextInt();
        switch (linha) {
            case 0:
                System.out.println("Linha escolhida = 1");
                break;
            case 1:
                System.out.println("Linha escolhida = 2");
                break;
            default:
                System.out.println("LINHA DESCONHECIDA");
                break;
        }
        entrada.close();
        return linha;
    }

    public static String escolherLinha() throws IOException, InterruptedException {
        try {

            Scanner entrada = new Scanner(System.in);
            String nomePasta = arquivos.pastaBase;
            String termo = "*.csv";
            List<String> arquivos = src.arquivos.procurarArquivos(nomePasta, termo);
            List<String> partidas = new ArrayList<String>();
            List<String> destinos = new ArrayList<String>();
            int codigoEscolhido = -1;
            String linhaEscolhida = null;
            String partida = null;
            String destino = null;
            int posicao;

            utilidades.limparConsole(0);

            utilidades.divisorConsole();
            System.out.println("Bem vindo ao sistema");
            utilidades.divisorConsole();
            utilidades.tituloConsole("Escolha uma linha");
            utilidades.divisorConsole();

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

                utilidades.divisorConsole();
            }

            while (linhaEscolhida == null) {
                System.out.println("Informe o código da linha:");
                codigoEscolhido = entrada.nextInt();

                if (codigoEscolhido >= 0 && codigoEscolhido < arquivos.size()) {
                    linhaEscolhida = arquivos.get(codigoEscolhido);
                }
            }

            partida = partidas.get(codigoEscolhido);
            destino = destinos.get(codigoEscolhido);
            utilidades.divisorConsole();
            System.out.println("Linha selecionada [" + partida + " -> " + destino + "]");

            return linhaEscolhida;

        } catch (IOException e) {
            return null;
        }

    }

}
