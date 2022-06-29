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
            String textoConfirmacao = "";
            String tituloConfirmacao = "";

            menu.limpar(0);
            menu.divisor();
            menu.titulo("Cadastro de nova linha");
            menu.divisor();
            menu.pergunta("Informe o local de partida:");
            nomeLinhaPartida = entrada.next();
            menu.pergunta("Informe o local de destino:");
            nomeLinhaDestino = entrada.next();

            nomeLimpo = nomeLinhaPartida + "-" + nomeLinhaDestino;
            nomeLimpo = nomeLimpo.trim().toLowerCase().replace(" ", "_");

            nomeLinhaArquivo += arquivos.pastaBase;
            nomeLinhaArquivo += nomeLimpo + ".csv";

            linhaExiste = arquivos.existeArquivo(nomeLinhaArquivo);

            textoConfirmacao = "Percurso: " + nomeLinhaPartida + " para " + nomeLinhaDestino + System.lineSeparator();

            tituloConfirmacao = "Confirmar cadastro de linha";
            Boolean confirmouRegistro = menu.confirmar(tituloConfirmacao, textoConfirmacao);

            if (confirmouRegistro) {
                if (!linhaExiste) {
                    linhaExiste = arquivos.criarArquivo(nomeLinhaArquivo, "");
                    menu.limpar(0);
                } else {
                    linhaExiste = false;
                    menu.limpar(0);
                    menu.erro("Essa linha já foi registrada.");
                    menu.limpar(3);
                }
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
            System.out.println(" | Código | Partida | Destino");
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

                System.out.println(" | " + lin + " | " + partida + " | " + destino);
            }

            menu.divisor();

            while (!linhaEscolhida) {
                menu.pergunta("Informe o código da linha:");
                codigoEscolhido = entrada.nextInt();

                if (codigoEscolhido >= 0 && codigoEscolhido < arquivos.size()) {
                    linhaEscolhida = true;
                }
            }

            despachaDados.add(String.valueOf(codigoEscolhido));
            despachaDados.add(arquivos.get(codigoEscolhido));
            despachaDados.add(partidas.get(codigoEscolhido));
            despachaDados.add(destinos.get(codigoEscolhido));

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
