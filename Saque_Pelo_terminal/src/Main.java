import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int[] notas = {200, 100, 50, 20, 10, 5, 2};
    static int[] estoqueNotas = {2, 2, 3, 0, 1, 10, 0};

    static int[] moedas = {100, 50, 25, 10, 5, 1};
    static int[] estoqueMoedas = {10, 10, 10, 10, 10, 10};

    public static int sobra;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual valor deseja sacar?");
        Float valorSaque = sc.nextFloat();

        System.out.println("\nQuantidade a ser sacada\n");

        int valorNotas = valorSaque.intValue();
        List<Integer> resultadoNotas = quantidadeTroco(valorNotas, notas, estoqueNotas);
        int valorCentavos = (int) ((valorSaque - valorNotas + sobra) * 100);
        List<Integer> resultadoMoedas = quantidadeTroco(valorCentavos, moedas, estoqueMoedas);

        devolveQuantidade(resultadoNotas, " nota(s) de ", notas);
        devolveQuantidade(resultadoMoedas, " moeda(s) de ", moedas);
    }

    //Verifica a lista com a quantidade de notas/moedas necessárias e faz o print no formato solicitado
    public static void devolveQuantidade(List<Integer> listaverificacao, String texto, int[] lista) {
        for (int posilista = 0; posilista < listaverificacao.size(); posilista++) {
            if (lista == moedas && listaverificacao.get(posilista) > 0) {
                System.out.printf(listaverificacao.get(posilista) + texto + lista[posilista] / 100.0 + "\n");
            } else if (listaverificacao.get(posilista) > 0) {
                System.out.printf(listaverificacao.get(posilista) + texto + lista[posilista] + "\n");
            }
        }
    }


    //Vai dividindo o valor total moedas/notas pelas existentes no caixa, ao final,
    //retorna a quantidade necessária de cada moedas/notas
    public static List<Integer> quantidadeTroco(int valor, int[] valores, int[] estoque) {
        List<Integer> resultado = new ArrayList<>();

        for (int i = 0; i < valores.length; i++) {
            int qtdUsada = Math.min(valor / valores[i], estoque[i]);
            resultado.add(qtdUsada);
            valor -= qtdUsada * valores[i];
        }
        if(!(verificaMoeda(valores)) && valor > 0) {
            sobra = valor;
        } if (valor > 1) {
            System.out.println("\nEstoque de notas/moedas não suporta o valor solicitado\n");
            System.exit(0);
        }
        return resultado;
    }

    //verificar se a lista passada é a lista de moedas
    public static Boolean verificaMoeda(int[] lista) {
        if (lista == moedas) {
            return true;
        } else {
            return false;
        }
    }
}
