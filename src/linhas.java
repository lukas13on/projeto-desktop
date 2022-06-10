package src;

import java.util.Scanner;

public class linhas {

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
