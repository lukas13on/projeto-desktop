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
            menu.limpar(0);
            List<String> linhaEscolhida = linhas.escolherLinha();

            int codLinha = Integer.parseInt(linhaEscolhida.get(0));
            String nomeArquivoLinha = linhaEscolhida.get(1);
            String partida = linhaEscolhida.get(2);
            String destino = linhaEscolhida.get(3);

            // System.out.println(codLinha);
            // System.out.println(nomeArquivoLinha);

            File arquivo = new File(nomeArquivoLinha);
            Scanner entrada = new Scanner(System.in);
            Scanner dados = new Scanner(arquivo);
            int posicao;
            String horarioLinha = null;
            String assentosLinha = null;
            String horario = null;
            int assentoEscolhido = -1;
            int horarioEscolhido = -1;
            String assento;
            String linhasArray[];
            String assentos[];
            String linhaAlterada = null;
            String assentoAlterado = null;
            String dadosLinhaAlterada = "";
            boolean linhaSalva = false;
            boolean linhaConfirmacao = false;
            String textoConfirmacao;
            String tituloConfirmacao;
            List<String> linhas = new ArrayList<String>();
            List<String> horariosLinhas = new ArrayList<String>();
            List<String> assentosLinhas = new ArrayList<String>();

            dados.useDelimiter("\n");

            if (!dados.hasNext()) {
                return null;
            }

            while (dados.hasNext()) {
                String linha = dados.next();
                linha = linha.trim();
                // ignora linhas vazias
                if (linha == "" || linha == null) {
                    continue;
                }
                // System.out.println("Linha: " + linha);
                linhas.add(linha);
            }

            menu.divisor();
            menu.titulo("Escolha um horario");
            menu.divisor();
            menu.resposta("Percurso: " + partida + " para " + destino);
            menu.divisor();

            for (posicao = 0; posicao < linhas.size(); posicao++) {

                String linha = linhas.get(posicao);
                String campos[] = linha.split(",");
                horarioLinha = campos[0];
                assentosLinha = campos[1];
                horariosLinhas.add(horarioLinha);
                assentosLinhas.add(assentosLinha);

                System.out.println(" | " + posicao + " | " + horarioLinha);

            }

            menu.divisor();

            while (horarioEscolhido == -1) {
                menu.pergunta("Informe o código horario:");
                horarioEscolhido = entrada.nextInt();
                if (horarioEscolhido < 0 || horarioEscolhido > linhas.size()) {
                    horarioEscolhido = -1;
                }
                if (horarioEscolhido != -1) {
                    menu.divisor();
                    horario = horariosLinhas.get(horarioEscolhido);
                    assentosLinha = assentosLinhas.get(horarioEscolhido);
                }
            }

            // menu.resposta("Horario selecionado [" + horario + "]");

            menu.limpar(0);

            menu.divisor();
            menu.titulo("Escolha um assento");
            menu.divisor();
            menu.resposta("Percurso: " + partida + " para " + destino);
            menu.resposta("Horario: " + horario);
            menu.divisor();

            assentos = assentosLinha.split("\\.");

            for (posicao = 0; posicao < assentos.length; posicao++) {
                assento = assentos[posicao];
                String pos = Integer.toString(posicao);
                String assentoCodigo = posicao >= 10 ? pos : "0" + pos;
                String estado = onibus.assentoVago(assento) ? "Vago  " : "Ocupado";
                System.out.println("| " + assentoCodigo + " | " + estado);
            }

            menu.divisor();

            while (assentoEscolhido == -1) {
                menu.pergunta("Informe o código do assento:");
                assentoEscolhido = entrada.nextInt();
                if (assentoEscolhido >= 0 && assentoEscolhido < assentos.length) {
                    assento = assentos[assentoEscolhido];
                    if (onibus.assentoOcupado(assento)) {
                        menu.erro("Assento ocupado [" + assentoEscolhido + "]");
                        assentoEscolhido = -1;
                    } else {
                        assentos[assentoEscolhido] = "1";
                    }
                } else {
                    assentoEscolhido = -1;
                }
            }

            menu.limpar(0);
            menu.divisor();

            assentoAlterado = String.join(".", assentos);
            linhaAlterada = horario + "," + assentoAlterado;

            menu.divisor();
            System.out.println(linhaAlterada);
            menu.divisor();

            linhas.set(codLinha, linhaAlterada);

            linhasArray = new String[linhas.size()];
            linhasArray = linhas.toArray(linhasArray);
            dadosLinhaAlterada = String.join(System.lineSeparator(), linhasArray);

            textoConfirmacao = "Percurso: " + partida + " para " + destino + System.lineSeparator();
            textoConfirmacao = textoConfirmacao + "Horario: " + horario;

            tituloConfirmacao = "Confirmar reserva de assento";
            linhaConfirmacao = menu.confirmar(tituloConfirmacao, textoConfirmacao);

            menu.divisor();
            menu.limpar(0);

            if (linhaConfirmacao) {
                linhaSalva = arquivos.escreverArquivo(nomeArquivoLinha, dadosLinhaAlterada);
                if (linhaSalva) {
                    menu.resposta("Passagem reservada");
                } else {
                    menu.erro("Falha sistema de arquivos.");
                }
            } else {
                menu.erro("Reserva cancelada");
            }

            menu.divisor();
            menu.texto("Voltando ao menu de reservas [...]");
            menu.aguardar(3);
            menu.limpar(0);

            onibus.escolherAssento();

            // System.out.println(dadosLinha);
            return null;
        } catch (IOException e) {
            return null;
        }
    }

}
