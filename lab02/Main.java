import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Ambiente ambiente1; 
        Robo robo1;
        ambiente1 = new Ambiente(); // Instânciamento dos objetos e inicialização do ambiente.

        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite o nome do Robô: ");
        String nome = entrada.nextLine();
        robo1 = new Robo(nome); // Inicialização do robô.

        String nome_robo= robo1.exibirNome();
        System.out.print("O/A " + nome_robo + " esta na posição: ");
        robo1.exibirPosicao();

        System.out.print("Quantos passos você dará horizontalmente (Para andar para a esquerda use negativo, para a direita positivo): ");
        int deltaX = entrada.nextInt();
        System.out.print("Quantos passos você dará verticalmente (Para andar para baixo use negativo, para cima positivo): ");
        int deltaY = entrada.nextInt();

        int[] vetor_posicao = robo1.mover(deltaX, deltaY); // Movimentação do robô.

        if (ambiente1.dentroDosLimites(vetor_posicao[0], vetor_posicao[1]) == 1){
            System.out.print("O/A " + nome_robo + " esta na nova posição: ");
            robo1.exibirPosicao();
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O/A " + nome_robo + " tentou sair do ambiente, logo ele/a permanece na posição: ");
            robo1.mover(-deltaX, -deltaY);
            robo1.exibirPosicao();
        }

        entrada.close();
    }
}