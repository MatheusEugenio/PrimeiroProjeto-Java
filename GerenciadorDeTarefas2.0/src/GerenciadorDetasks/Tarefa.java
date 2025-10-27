package GerenciadorDetasks;

import java.util.Objects;

public class Tarefa {

    private final String nomeTarefa;
    private final long id;
    private boolean status;
    private final String prioridade;

    public Tarefa(String nomeTarefa, long id,String prioridade) {
        this.nomeTarefa = nomeTarefa;
        this.id = id;
        this.status = false;
        this.prioridade = prioridade;
    }

    public String getNomeTarefa() {
        return nomeTarefa;
    }

    public long getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "nomeTarefa:" + nomeTarefa +", id:" + id + ", status:" + status +", prioridade:" + prioridade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id == tarefa.id && Objects.equals(prioridade, tarefa.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prioridade);
    }
}
