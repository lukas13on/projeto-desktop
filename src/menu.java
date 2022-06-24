package src;

import java.util.Scanner;

public class menu {

    public static boolean confirmarAcao(String titulo, String descricao) throws InterruptedException {
        String valor;
        Boolean selecionado = false;
        Scanner entrada = new Scanner(System.in);

        while (!selecionado) {

            utilidades.limparConsole(0);

            utilidades.divisorConsole();
            System.out.println("Deseja confirmar sua ação?");
            utilidades.divisorConsole();
            System.out.println("--------------[S]im-|-[N]ão-------------");
            utilidades.divisorConsole();

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

}
