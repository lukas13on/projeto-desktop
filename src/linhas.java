package src;

import java.util.Scanner;

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

}
