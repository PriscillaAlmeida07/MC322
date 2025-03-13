import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Robo robo1;
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite o nome do robô: ");
        String nome = entrada.nextLine();
        robo1 = new Robo(nome);

        Scanner posicaoX = new Scanner(System.in);
        System.out.print("Digite a nova posição X: ");
        String posicao = entrada.nextLine();
        mover()

    }
}