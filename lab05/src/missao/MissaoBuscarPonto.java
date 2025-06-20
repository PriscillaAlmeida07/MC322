package missao;

import ambiente.Ambiente;
import arquivos.Arquivo;
import exceptions.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import robo.AgenteInteligente;

public class MissaoBuscarPonto implements Missao {
    
    // Missão capaz de encontrar um caminho para levar os Agentes a um ponto específico do mapa.
    @Override
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo, Scanner entrada) {
        int Xfinal; int Yfinal;

        // Loop para garantir entrada correta
        while (true){
            try {
                System.out.print("Informe as coordenadas (x,y) do ponto que você deseja que o Agente Segurança busque:\nCoordenada x: ");
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
            robo.getControleMovimento().movimentarAgente(robo, ambiente, Xfinal, Yfinal);
            String mensagem = robo.getNome() + " se deslocou para o ponto (" + robo.getX() + "," + robo.getY() + "," + robo.getZUsuario() +")\n";
            arquivo.escreverNoArquivo(mensagem);
            System.out.print("\n");

        } catch (ForaDosLimitesException | ColisaoException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
