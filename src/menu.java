package src;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class menu {
    // RESET
    public static final String RESET = "\033[0m"; // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m"; // BLACK
    public static final String RED = "\033[0;31m"; // RED
    public static final String GREEN = "\033[0;32m"; // GREEN
    public static final String YELLOW = "\033[0;33m"; // YELLOW
    public static final String BLUE = "\033[0;34m"; // BLUE
    public static final String PURPLE = "\033[0;35m"; // PURPLE
    public static final String CYAN = "\033[0;36m"; // CYAN
    public static final String WHITE = "\033[0;37m"; // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m"; // BLACK
    public static final String RED_BOLD = "\033[1;31m"; // RED
    public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m"; // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m"; // CYAN
    public static final String WHITE_BOLD = "\033[1;37m"; // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m"; // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
    public static final String RED_BACKGROUND = "\033[41m"; // RED
    public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
    public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE

    public static boolean confirmar(String titulo, String descricao) throws InterruptedException {
        String valor;
        Boolean selecionado = false;
        Scanner entrada = new Scanner(System.in);

        while (!selecionado) {

            menu.limpar(0);

            menu.divisor();
            System.out.println(BLUE_BOLD + titulo);
            System.out.print(RESET);
            menu.divisor();
            System.out.println(descricao);
            menu.divisor();
            System.out.println(GREEN_BOLD + "(S)im (N)ão");
            System.out.print(RESET);
            menu.divisor();

            valor = entrada.nextLine();
            valor = valor.trim().toLowerCase();

            System.out.println();

            if (utilidades.textoIgual("s", valor)) {
                return true;
            } else if (utilidades.textoIgual("n", valor)) {
                return false;
            }

        }

        System.out.println("sai");

        return true;

    }

    public static void divisor() {
        System.out.print(RESET);
        System.out.println("========================================");
        System.out.print(RESET);
    }

    public static void titulo(String texto) {
        System.out.print(RESET);
        System.out.println(BLUE_BOLD + ">>> " + texto);
        System.out.print(RESET);
    }

    public static void resposta(String texto) {
        System.out.print(RESET);
        System.out.println(GREEN_BOLD + texto);
        System.out.print(RESET);
    }

    public static void pergunta(String texto) {
        System.out.print(RESET);
        System.out.println(PURPLE_BOLD + texto);
        System.out.print(RESET);
    }

    public static void erro(String texto) {
        System.out.print(RESET);
        System.out.println(RED_BOLD + texto);
        System.out.print(RESET);
    }

    public static void aguardar(int delaySegundos) throws InterruptedException {
        TimeUnit.SECONDS.sleep(delaySegundos);
    }

    public static void texto(String texto) {
        System.out.print(RESET);
        System.out.println(texto);
        System.out.print(RESET);
    }

    public static void limpar(int delaySegundos) throws InterruptedException {
        menu.aguardar(delaySegundos);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void cabecalho(String texto) {
        menu.divisor();
        System.out.print(RESET);
        System.out.println(CYAN_BOLD + texto);
        System.out.print(RESET);
        menu.divisor();
    }

    public static void opcoes() throws Exception {
        int posicao;
        String opcao;
        int codOpcao = -1;
        Scanner entrada = new Scanner(System.in);
        String opcoes[] = { "Consulta", "Reserva", "Cancelar reserva", "Nova linha [beta]" };

        menu.limpar(0);
        menu.cabecalho("Menu principal");
        menu.titulo("Escolha uma opção");
        menu.divisor();

        for (posicao = 0; posicao < opcoes.length; posicao++) {
            opcao = opcoes[posicao];
            System.out.println(" | " + posicao + " | " + opcao);
        }

        while (codOpcao == -1) {
            codOpcao = entrada.nextInt();
            switch (codOpcao) {
                case 0:
                    linhas.consultaLinhas();
                    break;
                case 1:
                    onibus.escolherAssento(true);
                    break;
                case 2:
                    onibus.escolherAssento(false);
                    break;
                case 3:
                    linhas.criarLinha();
                    break;
                default:
                    menu.erro("Opção nao suportada.");
            }
        }

    }

}
