import GerenciadorDetasks.AplicacaoDasAcoes;

import java.util.Scanner;

public class Exec {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)){
            System.out.print("Digite o local/diretório do arquivo do gerenciador: ");
            String caminhoDefinitivo = sc.nextLine();
            AplicacaoDasAcoes gerenciador = new AplicacaoDasAcoes(caminhoDefinitivo);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
