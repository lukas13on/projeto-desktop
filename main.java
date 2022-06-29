import src.onibus;
import src.utilidades;
import src.linhas;
import src.menu;
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

        // TESTE CRIAR ARQUIVO - OK
        // ----------------------------------------------------------
        /**
         * String nomeArquivo = "arquivos/testedois.csv";
         * String dadosArquivo = "teste";
         * Boolean criado = arquivos.criarArquivo(nomeArquivo, dadosArquivo);
         * if (criado) {
         * System.out.println("Arquivo criado");
         * } else {
         * System.out.println("Arquivo não criado");
         * }
         */
        // TESTE ESCREVER ARQUIVO - OK
        // ----------------------------------------------------------
        /**
         * String nomeArquivo = "arquivos/testedois.csv";
         * String dadosArquivo = "teste";
         * Boolean escrito = arquivos.escreverArquivo(nomeArquivo, dadosArquivo);
         * if (escrito) {
         * System.out.println("Arquivo escrito");
         * } else {
         * System.out.println("Arquivo não escrito");
         * }
         */

        // TESTE APAGAR ARQUIVO - OK
        // ----------------------------------------------------------
        /**
         * String nomeArquivo = "arquivos/testedois.csv";
         * Boolean apagado = arquivos.apagarArquivo(nomeArquivo);
         * if (apagado) {
         * System.out.println("Arquivo apagado");
         * } else {
         * System.out.println("Arquivo não apagado");
         * }
         */

        // TESTE LER ARQUIVO - OK
        // ----------------------------------------------------------
        /**
         * String nomeArquivo = "arquivos/teste.csv";
         * String dados = arquivos.lerArquivo(nomeArquivo);
         * System.out.println(dados);
         */

        // TESTE MENU CONFIRMAR - OK
        // ----------------------------------------------------------
        // boolean test = menu.confirmar("titulo", "descricao");
        // System.out.println(test);

        // TESTE PROCURAR ARQUIVOS - OK
        // ----------------------------------------------------------
        // String nomePasta = "arquivos/";
        // String termo = "*.csv";
        // System.out.println(arquivos.procurarArquivos(nomePasta, termo));

        // TESTE PEGA LINHAS - OK
        // ----------------------------------------------------------
        // String linha = linhas.pegaLinhas();
        // System.out.println(linha);
        // onibus.escolherAssento();
        menu.opcoes();
    }
}