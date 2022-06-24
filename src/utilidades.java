package src;

import java.util.concurrent.TimeUnit;

public class utilidades {

    public static void limparConsole(int delaySegundos) throws InterruptedException {
        TimeUnit.SECONDS.sleep(delaySegundos);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void divisorConsole() {
        System.out.println("----------------------------------------");
    }

    public static void tituloConsole(String texto) {
        System.out.println(">>> " + texto);
    }

    public static boolean textoIgual(String textoA, String textoB) {
        return new String(textoA).equals(textoB);
    }
}
