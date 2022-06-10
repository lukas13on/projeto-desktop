import src.onibus;
import src.linhas;
import src.arquivos;

public class main {
    public static void main(String[] args) throws Exception {

        // int linha = linhas.escolhaLinha();
        // int assento = onibus.escolhaAssento();
        // System.out.println("Assento escolhido" + assento);

        // TESTE EXISTE ARQUIVO - OK
        // ----------------------------------------------------------
        /**
         * String nomeArquivo = "arquivos/teste.csv";
         * Boolean existe = arquivos.existeArquivo(nomeArquivo);
         * if (existe) {
         * System.out.println("Arquivo existe");
         * } else {
         * System.out.println("Arquivo não existe");
         * }
         */

        // TESTE EXISTE ARQUIVO - OK
        // ----------------------------------------------------------
        String nomeArquivo = "arquivos/testedois.csv";
        String dadosArquivo = "teste";
        Boolean criado = arquivos.criarArquivo(nomeArquivo, "");
        if (criado) {
            System.out.println("Arquivo criado");
        } else {
            System.out.println("Arquivo não criado");
        }

    }
}