package missao;
import java.util.InputMismatchException;
import java.util.Scanner;

import ambiente.Ambiente;
import arquivos.Arquivo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import robo.AgenteInteligente;

public class MissaoBuscarPonto implements Missao {
    
    // Missão capaz de encontrar um caminho para levar os Agentes a um ponto específico do mapa

    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada) {
        int Xfinal = 0;
        int Yfinal = 0;

        // Loop para garantir entrada correta
        while (true) {
            try {
                System.out.print("Informe as coordenadas (x,y) do ponto que você deseja que o Agente Segurança busque:\n" + "Coordenada x: ");
                Xfinal = entrada.nextInt();

                System.out.print("Coordenada y: ");
                Yfinal = entrada.nextInt();

                break; // Sai do loop se leitura for correta
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite números inteiros.");
                entrada.nextLine(); // Limpar o buffer da entrada inválida
            }
        }

        // Agora tenta movimentar
        try {
            int deltaX = Xfinal - robo.getX();
            int deltaY = Yfinal - robo.getY();

            robo.getControleMovimento().movimentarAgente(robo, ambiente, deltaX, deltaY, 0);
            String mensagem = robo.getNome() + " se deslocou para o ponto (" + robo.getX() + "," + robo.getY() + "," + robo.getZUsuario() +")\n";
            robo.arquivarEPrintar(mensagem, arquivo);
        } catch (ForaDosLimitesException | ColisaoException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

}
