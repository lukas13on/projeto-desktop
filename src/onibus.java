package src;

import java.util.Scanner;

public class onibus {

    public static int escolhaAssento() {

        Scanner entrada = new Scanner(System.in);

        int escolhido = 0;
        boolean verificaAssento = false;
        int assentos[] = { 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1 };

        while (verificaAssento == false) {
            System.out.println("Escolha um assento: ");
            escolhido = entrada.nextInt();

            if (assentos[escolhido] != 1) {
                verificaAssento = true;
            } else {
                System.out.println("Esse assento não está disponível");
            }
        }

        entrada.close();
        return escolhido;
    }

}
