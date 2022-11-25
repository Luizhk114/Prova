import java.io.IOException;
import java.util.Scanner;
import classes.Pessoa;
import classes.Piloto;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui
                Piloto piloto = new Piloto();
                
                System.out.println("Nome:");
                String nome = in.nextLine();
                piloto.setNome(nome);

                System.out.println("CPF:");
                String cpf = in.nextLine();
                piloto.setCpf(cpf);

                System.out.println("Brevê:");
                String breve = in.nextLine();
                piloto.setBreve(breve);

                System.out.println("Matrícula:");
                int Matricula = in.nextInt();
                piloto.setMatricula(Matricula);

                pilotos[qtdCadastrados] = piloto;
                qtdCadastrados ++;


                System.out.println("\nPiloto cadastrado com sucesso.");
                voltarMenu(in);
            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há motoristas cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }

                // Exiba os pilotos aqui

                for (int index = 0; index < qtdCadastrados; index++) {
                    Piloto atual = (Piloto) pilotos[index];
                    String texto = "Nome: " + atual.getNome() + " Cpf: " + atual.getCpf() + " Brevê: " + atual.getBreve() + " Matrícula: " + atual.getMatricula();
                    System.out.println(texto);
                }

                voltarMenu(in);
            } else if (opcao == 3) {
                System.out.println("Digite o CPF a ser localizado:");
                String cpf = in.nextLine();
                boolean encontrada = false;
                for (int index = 0; index < qtdCadastrados; index++) {
                    Piloto atual = (Piloto) pilotos[index];
                    if (atual.getCpf().matches(cpf)){
                        String texto = "Nome: " + atual.getNome() + " Cpf: " + atual.getCpf() + " Brevê: " + atual.getBreve() + " Matrícula: " + atual.getMatricula();
                        System.out.println(texto);
                        encontrada = true;
                    }
                
                }
                if (encontrada == false){
                    System.out.println("Nenhum piloto cadastrado com esse CPF"); 
                }

            } else if (opcao == 4) {
                System.out.println("Digite o novo limite: ");
                int novo_Limite = in.nextInt();
                if (novo_Limite < qtdCadastrados){
                    System.out.println("Não é possível diminuir o limite atual");
                    continue;
                }
                
                Pessoa[] novo_Vetor = new Pessoa[novo_Limite];
                
                 for (int index = 0; index < qtdCadastrados; index++) {
                    novo_Vetor[index] = pilotos[index];

                 }
                
                pilotos = novo_Vetor;
                MAX_ELEMENTOS = novo_Limite;



                
            }
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}