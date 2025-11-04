import GerenciadorDetasks.AplicacaoDasAcoes;
import GerenciadorDetasks.Tarefa;

import java.lang.System;
import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {
        Tarefa lavarcarro = new Tarefa("lavar o carro hoje", 1234567, "média");

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Digite o local/diretório do arquivo do gerenciador: ");
            String caminhoDefinitivo = sc.nextLine();
            AplicacaoDasAcoes gerenciador = new AplicacaoDasAcoes(caminhoDefinitivo);

            int op;
            do {
                System.out.println("=== Menu ===");
                System.out.println("1- Listar tarefas");
                System.out.println("2- Adicionar tarefa");
                System.out.println("3- remover tarefa");
                System.out.println("4- marcar como concluída");
                System.out.println("0- sair");
                op = sc.nextInt();

                gerenciador.listarTarefas();
                switch (op) {
                    case 1:
                        gerenciador.listarTarefas();
                        break;
                    case 2:
                        System.out.println("Quantas tarefas deseja cadastrar?");
                        int quant = sc.nextInt();

                        if (quant == 0) {
                            System.out.println("Nenhuma tarefa foi cadastrada!");
                            return;
                        }

                        for (int i = 0; i < quant; i++) {
                            System.out.println("=== Adicionando Tarefa ===");
                            System.out.print("Digite o nome/descrição da tarefa: ");
                            String nomeT = sc.nextLine();

                            System.out.print("Digite um número identificador(ID) para esta tarefa: ");
                            int id = sc.nextInt();

                            System.out.print("Digite a prioridade dessa tarefa: (Alta | Média | Baixa)");
                            String prioridade = sc.nextLine();

                            System.out.println("==================================\n");

                            Tarefa t = new Tarefa(nomeT, id, prioridade);
                            gerenciador.addTarefa(t);
                        }
                        break;
                    case 3:
                        System.out.println("=== Remoção de tarefa ===");
                        gerenciador.listarTarefas();
                        System.out.print("Digite o id da tarefa que deseja remover: ");
                        int id = sc.nextInt();
                        gerenciador.removerTarefas(id);
                        System.out.println("==================================\n");
                        break;
                    case 4:
                        System.out.println("=== Marcar como concluído ===");
                        System.out.print("Digite o id da tarefa que deseja concluir: ");
                        int idRef = sc.nextInt();
                        gerenciador.marcarConcluido(idRef);
                        System.out.println("==================================\n");

                        break;
                }
            } while (op != 0);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
