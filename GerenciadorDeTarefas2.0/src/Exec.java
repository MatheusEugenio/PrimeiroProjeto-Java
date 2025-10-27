import GerenciadorDetasks.AplicacaoDasAcoes;
import GerenciadorDetasks.Tarefa;

import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {
        Tarefa lavarcarro = new Tarefa("lavar o carro hoje", 1234567, "média");

        try (Scanner sc = new Scanner(System.in)){
            System.out.print("Digite o local/diretório do arquivo do gerenciador: ");
            String caminhoDefinitivo = sc.nextLine();
            AplicacaoDasAcoes gerenciador = new AplicacaoDasAcoes(caminhoDefinitivo);

            gerenciador.listarTarefas();
            gerenciador.addTarefa(sc); //Mudar esse parâmetro de scanner, tem que ser uma Tarefa
            gerenciador.addTarefa(sc); //Mudar esse parâmetro de scanner, tem que ser uma Tarefa
            gerenciador.listarTarefas();
            gerenciador.marcarConcluido(lavarcarro, 1234);
            gerenciador.removerTarefas(12345);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
