
public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente(); 

        // Instânciamento de todos os robôs.
        RoboCavador roboCavador1 = new RoboCavador("Cavador1");
        RoboCriaObstaculo roboCriaObstaculo1 = new RoboCriaObstaculo("CriaObstaculo1");

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

        
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração válida.

        if (ambiente1.dentroDosLimites(deltaX, deltaY) == 1){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");
        }

        // Teste 2: Robo não está dentro dos limites e a profundidade perfurada não é válida.
        deltaX = 110;
        deltaY = 110;
        deltaZ = 110;

        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração inválida.

        if (ambiente1.dentroDosLimites(deltaX, deltaY) == 1){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O " + nomeRobo + " tentou sair do ambiente, logo ele permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetorPosicao = roboCavador1.getPosicao();
            System.out.print("(" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n\n");
        }

        // Testes do roboCriaObstaculo1:
        int[][] obstaculoPosicao;
        nomeRobo = roboCriaObstaculo1.getNome();
        vetorPosicao = roboCriaObstaculo1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetorPosicao[0] + "," + vetorPosicao[1] + ")\n");

        // Teste 1: Deposito de um bloco em uma posição válida
        roboCriaObstaculo1.soltarBlocos();
        ambiente1.adicionarBloco(vetorPosicao);
        obstaculoPosicao = ambiente1.getBlocos();
        System.out.println("Um bloco foi depositado na posição (" + obstaculoPosicao[0][0] + ", " + obstaculoPosicao[0][1] + ")");



        // Testes do roboFlutuador1


        // Testes do robo


    
    }
}