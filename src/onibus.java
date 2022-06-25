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
            utilidades.limparConsole(0);
            List<String> escolhaLinha = linhas.escolherLinha();
            int codLinha = Integer.parseInt(escolhaLinha.get(0));
            String arquivoLinha = escolhaLinha.get(1);
            System.out.println(codLinha);
            System.out.println(arquivoLinha);
            /**
             * if (true) {
             * return null;
             * }
             */
            File arquivo = new File(arquivoLinha);
            Scanner entrada = new Scanner(System.in);
            Scanner dados = new Scanner(arquivo);
            int posicao;
            String horarioLinha = null;
            String assentosLinha = null;
            String horario = null;
            int assentoEscolhido = -1;
            int horarioEscolhido = -1;
            String assento;
            List<String> linhas = new ArrayList<String>();
            String assentos[];
            String linhaAlterada = null;
            String assentoAlterado = null;

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
                assento = assentos[posicao];
                String pos = Integer.toString(posicao);
                String assentoCodigo = posicao >= 10 ? pos : "0" + pos;
                String estado = onibus.assentoVago(assento) ? "Vago  " : "Ocupado";
                System.out.println("| " + assentoCodigo + " | " + estado);
            }

            utilidades.divisorConsole();

            while (assentoEscolhido == -1) {
                System.out.println("Informe o código do assento:");
                assentoEscolhido = entrada.nextInt();
                if (assentoEscolhido >= 0 && assentoEscolhido < assentos.length) {
                    assento = assentos[assentoEscolhido];
                    if (onibus.assentoOcupado(assento)) {
                        assentoEscolhido = -1;
                        System.out.println("Assento ocupado: [" + assentoEscolhido + "]");
                    } else {
                        assentos[assentoEscolhido] = "1";
                    }
                } else {
                    assentoEscolhido = -1;
                }
            }

            System.out.println("Escolhido: " + assentoEscolhido);
            utilidades.divisorConsole();

            assentoAlterado = String.join(".", assentos);
            linhaAlterada = horario + "," + assentoAlterado;

            utilidades.divisorConsole();
            System.out.println(linhaAlterada);
            utilidades.divisorConsole();

            int test[] = { 0, 0, 0, 1, 0, 1, 1 };

            linhas.set(codLinha, linhaAlterada);

            for (String linha : linhas) {
                System.out.println(linha);
            }

            // System.out.println(dadosLinha);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}
