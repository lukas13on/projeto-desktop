package src;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class linhas {

    public static Integer maximoHorarios = 50;

    public static String gerarAssentos(Integer quantidade) {
        String resultado = "";
        for (Integer x = 0; x < quantidade; x++) {
            if (x == (quantidade - 1)) {
                resultado = resultado + "0";
            } else {
                resultado = resultado + "0.";
            }
        }
        return resultado;
    }

    public static String gerarDadosLinha(List<String> horarios, List<Integer> assentos) {
        List<String> linhasArquivo = new ArrayList<String>();
        String dados = "";
        for (Integer x = 0; x < horarios.size(); x++) {
            String linha = "";
            linha = linha + horarios.get(x) + "," + linhas.gerarAssentos(assentos.get(x));
            linhasArquivo.add(linha);
            // System.out.println(horarios.get(x));
        }
        for (String linha : linhasArquivo) {
            dados = dados + linha + System.lineSeparator();
        }
        // System.out.println(dados);
        return dados;
    }

    public static boolean criarLinha() throws Exception {
        Scanner entrada = new Scanner(System.in);
        Boolean linhaExiste = false;
        Integer quantidadeHorarios = -1;
        String nomeLinhaPartida = "";
        String nomeLinhaDestino = "";
        String nomeLinhaArquivo = "";
        String nomeLimpo = "";
        String textoConfirmacao = "";
        String tituloConfirmacao = "";
        String dataArquivo = "";
        List<String> horarios = new ArrayList<String>();
        List<Integer> assentos = new ArrayList<Integer>();

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

        textoConfirmacao = "Percurso: " + nomeLinhaPartida + " para " + nomeLinhaDestino;

        tituloConfirmacao = "Confirmar cadastro de linha";

        if (!linhaExiste) {

            menu.limpar(0);

            menu.divisor();
            menu.titulo("Cadastro de nova linha");
            menu.divisor();

            while (quantidadeHorarios == -1) {
                menu.pergunta("Informe a quantidade de horarios:");
                quantidadeHorarios = entrada.nextInt();
                if (quantidadeHorarios < 0 || quantidadeHorarios > linhas.maximoHorarios) {
                    quantidadeHorarios = -1;
                }
            }

            menu.divisor();

            for (Integer x = 0; x < quantidadeHorarios; x++) {
                String horarioAtual = null;
                Integer assentosAtual = -1;
                while (horarioAtual == null) {
                    menu.pergunta("Informe o horario (" + x + "/" + quantidadeHorarios + "):");
                    horarioAtual = entrada.next();
                    if (horarioAtual.length() <= 0) {
                        horarioAtual = null;
                    }
                }
                while (assentosAtual == -1) {
                    menu.pergunta("Informe a quantidade de assentos (" + x + "/" + quantidadeHorarios + "):");
                    assentosAtual = entrada.nextInt();
                    if (assentosAtual < 0) {
                        assentosAtual = -1;
                    }
                }

                horarios.add(horarioAtual);
                assentos.add(assentosAtual);
                System.out.println(assentosAtual);
                System.out.println(horarioAtual);

                menu.divisor();
                // list.add();
            }

            String dados = linhas.gerarDadosLinha(horarios, assentos);
            // System.out.println(quantidadeHorarios);

            textoConfirmacao = textoConfirmacao + System.lineSeparator();
            textoConfirmacao = textoConfirmacao + quantidadeHorarios.toString() + " horario(s)";

            Boolean confirmouRegistro = menu.confirmar(tituloConfirmacao, textoConfirmacao);

            if (confirmouRegistro) {
                arquivos.criarArquivo(nomeLinhaArquivo, dados);
                menu.limpar(0);
                menu.resposta("Linha registrada com sucesso.");
                menu.limpar(3);
                menu.opcoes();
            } else {
                menu.opcoes();
            }

        } else {
            linhaExiste = false;
            menu.limpar(0);
            menu.erro("Essa linha já foi registrada.");
            menu.limpar(3);
            menu.opcoes();
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
    public static List<String> escolherLinha(String tipo) throws IOException, InterruptedException {
        try {

            Scanner entrada = new Scanner(System.in);
            String nomePasta = arquivos.pastaBase;
            String termo = "*.csv";
            List<String> arquivos = src.arquivos.procurarArquivos(nomePasta, termo);
            List<String> partidas = new ArrayList<String>();
            List<String> destinos = new ArrayList<String>();
            Integer codigoEscolhido = -1;
            Boolean linhaEscolhida = false;
            String partida = null;
            String destino = null;
            List<String> despachaDados = new ArrayList<String>();

            int posicao;
            String cabecalho;

            if (utilidades.textoIgual(tipo, "reservar")) {
                cabecalho = "Reserva de assento";
            } else if (utilidades.textoIgual(tipo, "cancelar")) {
                cabecalho = "Cancelamento de reserva";
            } else if (utilidades.textoIgual(tipo, "consulta")) {
                cabecalho = "Consulta de linhas";
            } else {
                cabecalho = "Menu desconhecido";
            }

            menu.limpar(0);
            menu.cabecalho(cabecalho);
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

    public static void consultaLinhas() throws Exception {
        try {
            menu.limpar(0);
            List<String> linhaEscolhida = linhas.escolherLinha("consulta");

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
            int horarioEscolhido = -1;
            String assento;
            String assentos[];
            List<String> linhas = new ArrayList<String>();
            List<String> horariosLinhas = new ArrayList<String>();
            List<String> assentosLinhas = new ArrayList<String>();

            dados.useDelimiter("\n");

            if (!dados.hasNext()) {
                return;
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
            menu.titulo("Consulta de disponibilidade");
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
            menu.resposta("Pressione qualquer tecla para sair [...]");
            try {
                System.in.read();
                menu.opcoes();
            } catch (Exception e) {
                return;
            }

            return;
        } catch (IOException e) {
            return;
        }
    }

}
