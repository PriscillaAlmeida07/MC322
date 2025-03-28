
public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente(); 

        // Instânciamento de todos os robôs.
        RoboCavador roboCavador1 = new RoboCavador("Cavador1");
        RoboObstaculoTerrestre roboObstaculoTerrestre1 = new RoboObstaculoTerrestre("ObstaculoTerrestre1");
        RoboObstaculoAereo roboObstaculoAereo1 = new RoboObstaculoAereo("ObstaculoAereo1");

        // Adicionando robôs ao ambiente
        ambiente1.adicionarRobo(roboCavador1);
        ambiente1.adicionarRobo(roboObstaculoTerrestre1);
        ambiente1.adicionarRobo(roboObstaculoAereo1);

        // Essas variaveis serão usadas em todos os casos de teste
        String nomeRobo;
        int[] vetorPosicao;
        
        
        // Testes do roboCavador1:
        nomeRobo = roboCavador1.getNome();
        vetorPosicao = roboCavador1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");


        // Teste 1: Robo está dentro dos limites e a profundidade perfurada é válida.
        int deltaX = 50;
        int deltaY = 50;
        int deltaZ = 50;

        System.out.print("Teste de movimento: (50,50,50)\n");
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração válida.
        vetorPosicao = roboCavador1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculo(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 2: Robo não está dentro dos limites e a profundidade perfurada não é válida.
        deltaX = 1;
        deltaY = 1;
        deltaZ = 51;

        System.out.print("Teste de movimento: (1,1,51)\n");
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.
        vetorPosicao = roboCavador1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculo(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        // Testes do roboObstaculoTerrestre1:
        int[][] obstaculoPosicao;
        nomeRobo = roboObstaculoTerrestre1.getNome();
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");

        // Teste 1: Deposito de um bloco em uma posição válida
        roboObstaculoTerrestre1.soltarBlocos();
        ambiente1.adicionarBloco(vetorPosicao);
        obstaculoPosicao = ambiente1.getBlocos();
        System.out.println("Um bloco foi depositado na posição (" + obstaculoPosicao[0][0] + ", " + obstaculoPosicao[0][1] + ")");

        // Teste 2: Robo está dentro dos limites e se move para uma posição válida sem obstáculos
        deltaX = 20;
        deltaY = 20;

        System.out.print("Teste de movimento: (20,20)\n");
        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculo(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele permanece na posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 3: Robo está dentro dos limites e se move para uma posição inválida com obstáculos
        deltaX = -20;
        deltaY = -20;

        System.out.print("Teste de movimento: (-20,-20)\n");
        roboObstaculoTerrestre1.mover(deltaX, deltaY);
        vetorPosicao = roboObstaculoTerrestre1.getPosicao();

        if ((ambiente1.dentroDosLimites(vetorPosicao[0], vetorPosicao[1]) == 1) && (ambiente1.existeObstaculo(vetorPosicao[0], vetorPosicao[1]) == 1)){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente ou atingiu um obstáculo, logo ele permanece na posição: ");
            roboObstaculoTerrestre1.mover(-deltaX, -deltaY);
            vetorPosicao = roboObstaculoTerrestre1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }


        // Testes do roboObstaculoAereo1
        



        // Testes do roboFlutuador1


        // Testes do robo


    
    }
}