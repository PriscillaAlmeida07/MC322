
public class Main {
    public static void main(String[] args){

        Ambiente ambiente1; 
        RoboCavador roboCavador1;
        RoboCriaObstaculo roboCriaObstaculo1;
       // RoboFlutuador roboFlutuador1;

        ambiente1 = new Ambiente(); // Instânciamento dos objetos e inicialização do ambiente.


        // Inicialização de todos os robôs
        roboCavador1 = new RoboCavador("Cavador-1");
        roboCriaObstaculo1 = new RoboCriaObstaculo("CriaObstaculo-1");

        // ROBO CAVADOR

        int[] posicao = roboCriaObstaculo1.getPosicao() ;
        roboCriaObstaculo1.soltarBlocos();
        ambiente1.adicionarBloco(posicao);
        ambiente1.getBlocos();
/* 
        String nome_robo = roboCavador1.exibirNome();
        System.out.print("O/A " + nome_robo + " esta na posição: ");
        roboCavador1.exibirPosicao();

        System.out.print("Quantos metros você moverá no eixo x (Para andar para o oeste use negativo, para o leste positivo): ");
        int deltaX = entrada.nextInt();
        System.out.print("Quantos metros você moverá no eixo y (Para andar para o sul use negativo, para o norte positivo): ");
        int deltaY = entrada.nextInt();
        System.out.print("Quantos metros você cavará: ");
        int deltaZ = entrada.nextInt();

        int[] vetor_posicao = roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Movimentação do robô.

        if (ambiente1.dentroDosLimites(vetor_posicao[0], vetor_posicao[1]) == 1){
            System.out.print("O/A " + nome_robo + " esta na nova posição: ");
            roboCavador1.exibirPosicao();
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O/A " + nome_robo + " tentou sair do ambiente, logo ele/a permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            roboCavador1.exibirPosicao();
        }


        */
    }
}