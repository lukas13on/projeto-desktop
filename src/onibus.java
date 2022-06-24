package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class onibus {

    public static boolean criarOnibus() {
        return true;
    }

    public static boolean assentoVago(String assento) {
        return utilidades.textoIgual(assento, "0");
    }

    public static boolean assentoOcupado(String assento) {
        return onibus.assentoVago(assento) ? false : true;
    }

    public static String escolherAssento() throws Exception {
        try {
            String arquivoLinha = linhas.escolherLinha();
            File arquivo = new File(arquivoLinha);
            Scanner entrada = new Scanner(System.in);
            Scanner dados = new Scanner(arquivo);
            int posicaoLinha = 0;
            int posicao;
            String horarioLinha = null;
            String assentosLinha = null;
            String horario = null;
            int assentoEscolhido = -1;
            int horarioEscolhido = -1;
            List<String> linhas = new ArrayList<String>();
            String assentos[];

            dados.useDelimiter("\n");

            if (!dados.hasNext()) {
                return null;
            }

            while (dados.hasNext()) {
                String linha = dados.next();
                linhas.add(linha);

            }

            utilidades.divisorConsole();
            utilidades.tituloConsole("Escolha um horario");
            utilidades.divisorConsole();

            for (posicao = 0; posicao < linhas.size(); posicao++) {
                String linha = linhas.get(posicao);
                String campos[] = linha.split(",");
                horarioLinha = campos[0];
                assentosLinha = campos[1];
                System.out.println(" | " + posicao + " | " + horarioLinha);
            }

            utilidades.divisorConsole();

            while (horarioEscolhido == -1) {
                System.out.println("Informe o código horario:");
                horarioEscolhido = entrada.nextInt();
                if (horarioEscolhido < 0 || horarioEscolhido > linhas.size()) {
                    horarioEscolhido = -1;
                }
                if (horarioEscolhido != -1) {
                    utilidades.divisorConsole();
                    String campos[] = linhas.get(horarioEscolhido).split(",");
                    horario = horarioLinha = campos[0];
                }
            }

            System.out.println("Horario selecionado [" + horario + "]");

            utilidades.divisorConsole();
            utilidades.tituloConsole("Escolha um assento");
            utilidades.divisorConsole();

            assentos = assentosLinha.split("\\.");
            // System.out.println(assentos);
            for (posicao = 0; posicao < assentos.length; posicao++) {
                // ocupa os assentos pra
                if (posicao == 4 || posicao == 7) {
                    assentos[posicao] = "1";
                }
            }

            for (posicao = 0; posicao < assentos.length; posicao++) {
                String assento = assentos[posicao];
                String pos = Integer.toString(posicao);
                String assentoCodigo = posicao >= 10 ? pos : "0" + pos;
                String estado = onibus.assentoVago(assento) ? "Vago  " : "Ocupado";
                System.out.println("| " + assentoCodigo + " | " + estado);
            }

            utilidades.divisorConsole();

            // System.out.println(dadosLinha);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}
