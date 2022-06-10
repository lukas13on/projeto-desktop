import src.onibus;
import src.linhas;

public class main {
    public static void main(String[] args) {

        // int linha = linhas.escolhaLinha();
        int assento = onibus.escolhaAssento();
        System.out.println("Assento escolhido" + assento);
    }
}