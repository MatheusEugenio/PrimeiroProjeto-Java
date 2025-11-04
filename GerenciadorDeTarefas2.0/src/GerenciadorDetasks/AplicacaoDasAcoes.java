package GerenciadorDetasks;

import java.io.*;
import java.util.*;

public class AplicacaoDasAcoes implements Acoes{

    private List<Tarefa> tarefas = new ArrayList<>();
    private String caminhoArquivo;

    public AplicacaoDasAcoes(String caminho){
        this.caminhoArquivo = caminho;
        carregarTarefas();
    }

    @Override
    public void addTarefa(Tarefa t) throws Exception{
        try{
            this.tarefas.add(t);
            salvarTarefas();

            System.out.println("Tarefa foi adicionada com sucesso!");
        }catch (Exception e) {
            throw  new Exception("Erro genérico! "+ e.getMessage());
        }
    }

    @Override
    public void listarTarefas() {
        System.out.println("=== Lista de Tarefas ===");

        List<Tarefa> tarefasTemp = new ArrayList<>();

        try (BufferedReader leitorar = new BufferedReader(new FileReader(this.caminhoArquivo))){
            String linha;
            boolean veri = false;

            while ((linha = leitorar.readLine()) != null){
                Tarefa t = parseTarefa(linha);
                tarefasTemp.add(t);
                veri = true;
            }

            if (!veri){
                System.out.println("Lista vazia!");
                return;
            }

            // Define ordem lógica de prioridade
            Map<String, Integer> ordemPrioridade = Map.of(
                    "Alta", 1,
                    "Média", 2,
                    "Baixa", 3
            );

            // Define a ordem lógica de prioridade e ordena a lista em Alta, Média e Baixa
            tarefasTemp.sort(Comparator.comparing(t -> ordemPrioridade.getOrDefault(t.getPrioridade() ,  99)));

            for (Tarefa tare : tarefasTemp){
                System.out.println(tare);
            }

        }catch (Exception e){
            System.out.println("Erro ao listar tarefas! "+e.getMessage());
        }
        System.out.println("==========================");
    }

    @Override
    public void removerTarefas(long id) {
        this.tarefas.removeIf(tarefa -> tarefa.getId() == id);
        salvarTarefas();
    }

    @Override
    public void marcarConcluido(long id) {
        /*
        for (Tarefa tar : tarefas){
            if (tar.getId() == id) {
                tar.setStatus(true);
                break;
            }
        }
         */
            tarefas.stream()
                .filter(tarefa -> tarefa.getId() == id)
                .forEach(t -> t.setStatus(true));

            salvarTarefas();
    }

    private void carregarTarefas(){
        try (BufferedReader leitorArq = new BufferedReader(new FileReader(this.caminhoArquivo))){
            String linha;

            while ((linha = leitorArq.readLine()) != null){
                if (!linha.trim().isEmpty()){
                    Tarefa tarefa = parseTarefa(linha);

                    this.tarefas.add(tarefa);
                }
            }

            System.out.println("Arquivo carregado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo!"+ e.getMessage());
        } catch (Exception e) {
            System.out.println("ERRO! "+ e.getMessage());
        }
    }

    private Tarefa parseTarefa(String linha) throws Exception {
        // 1. Remove o ';' final, se existir, para facilitar o split
        String conteudo = linha.endsWith(";") ? linha.substring(0, linha.length() - 1) : linha;

        // 2. Divide a string pelos separadores de campo (vírgula e espaço: ", ")
        // Se o seu separador for apenas a vírgula, use: conteudo.split(",");
        String[] campos = conteudo.split(", ");

        // Variáveis para armazenar os dados extraídos, já nos tipos corretos
        String nomeTarefa = null;
        long id = 0;
        String prioridade = null;

        for (String campo : campos) {
            // Exemplo: campo será "nomeTarefa:Minha Tarefa"

            // 3. Divide em Chave e Valor pelo primeiro dois-pontos (:)
            int indiceDoisPontos = campo.indexOf(':');
            if (indiceDoisPontos > 0) {
                String chave = campo.substring(0, indiceDoisPontos).trim(); // ex: "nomeTarefa"
                String valorBruto = campo.substring(indiceDoisPontos + 1).trim(); // ex: "Minha Tarefa"

                // 4. Atribui o valor e faz a conversão de tipo (parse)
                switch (chave) {
                    case "nomeTarefa":
                        nomeTarefa = valorBruto;
                        break;
                    case "id":
                        id = Long.parseLong(valorBruto);
                        break;
                    case "prioridade":
                        prioridade = valorBruto;
                        break;
                    default:
                        // Ignora campos não reconhecidos
                        break;
                }
            }
        }

        // 5. Verifica a integridade mínima antes de instanciar o objeto
        if (nomeTarefa == null) {
            throw new Exception("Nome da tarefa ausente ou formato inválido na linha.");
        }

        // **IMPORTANTE**: Garanta que a assinatura do seu construtor 'Tarefa' é esta:
        return new Tarefa(nomeTarefa,id, prioridade);
    }

    private void salvarTarefas(){
        try(BufferedWriter arqListaTarefas = new BufferedWriter(new FileWriter(this.caminhoArquivo))){

            for (Tarefa t: this.tarefas){
                String tarefa = t.toString();

                arqListaTarefas.write(tarefa);
                arqListaTarefas.newLine();
                arqListaTarefas.flush();
            }

        }catch (IOException e){
            System.out.println("Erro ao salvar o arquivo! "+e.getMessage());
        }
    }

}
