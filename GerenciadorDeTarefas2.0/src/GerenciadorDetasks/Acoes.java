package GerenciadorDetasks;

public interface Acoes {
    void addTarefa(Tarefa t) throws Exception;
    void listarTarefas() ;
    void removerTarefas(long id);
    void marcarConcluido( long id);
}
