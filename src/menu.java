package src;

import java.util.Scanner;

public class menu {

    public static boolean confirmarAcao(String titulo, String descricao) throws InterruptedException {
        String valor;
        Boolean confirmado = false;
        Boolean selecionado = false;
        Scanner entrada = new Scanner(System.in);

        while (!selecionado) {

            utilidades.limparConsole(0);

            utilidades.divisorConsole();
            System.out.println("-------Deseja confirmar sua ação?-------");
            utilidades.divisorConsole();
            System.out.println("--------------(S)im-|-(N)ão-------------");
            utilidades.divisorConsole();

            valor = entrada.next();
            valor = valor.trim().toLowerCase();

            System.out.println(valor);

            if (valor == "s" || valor == "sim") {
                selecionado = true;
                System.out.println("sim");
                // return true;
            } else if (valor == "n" || valor == "nao" || valor == "não") {
                // return false;
                selecionado = true;
                System.out.println("nao");
            } else {
                System.out.println("else");
                /** .... */
            }
        }

        return true;

    }

}
