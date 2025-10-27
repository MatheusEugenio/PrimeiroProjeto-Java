package GerenciadorDetasks;

import java.util.Scanner;

public interface Acoes {
    abstract void addTarefa(Scanner sc);
    abstract void listarTarefas();
    abstract void removerTarefas(long id);
    abstract void marcarConcluido(Tarefa t, long id);
}
