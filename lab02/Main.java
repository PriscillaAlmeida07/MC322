
public class Main {
    public static void main(String[] args){

        // Instânciamento do ambiente.
        Ambiente ambiente1 = new Ambiente(); 

        // Instânciamento de todos os robôs
        RoboCavador roboCavador1 = new RoboCavador("Cavador1");
        RoboCriaObstaculo roboCriaObstaculo1 = new RoboCriaObstaculo("CriaObstaculo1");


        // Testes do roboCavador1
        int[] vetor_posicao;
        String nomeRobo = roboCavador1.getNome();
        vetor_posicao = roboCavador1.getPosicao();
        System.out.print("O " + nomeRobo + " esta atualmente na posição: (" + vetor_posicao[0] + "," + vetor_posicao[1] + ")\n");


        // Teste 1: Robo está dentro dos limites e a profundidade perfurada é válida
        int deltaX = 50;
        int deltaY = 50;
        int deltaZ = 50;

        
        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração.

        if (ambiente1.dentroDosLimites(deltaX, deltaY) == 1){
            System.out.print("O " + nomeRobo + " esta na nova posição: ");
            vetor_posicao = roboCavador1.getPosicao();
            System.out.print("(" + vetor_posicao[0] + "," + vetor_posicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O/A " + nomeRobo + " tentou sair do ambiente, logo ele/a permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetor_posicao = roboCavador1.getPosicao();
            System.out.print("(" + vetor_posicao[0] + "," + vetor_posicao[1] + ")\n");
        }

        // Teste 2: Robo não está dentro dos limites e a profundidade perfurada não é válida
        deltaX = 110;
        deltaY = 110;
        deltaZ = 110;

        roboCavador1.mover(deltaX, deltaY);
        roboCavador1.cavar(deltaZ); // Perfuração.

        if (ambiente1.dentroDosLimites(deltaX, deltaY) == 1){
            System.out.print("O/A " + nomeRobo + " esta na nova posição: ");
            vetor_posicao = roboCavador1.getPosicao();
            System.out.print("(" + vetor_posicao[0] + "," + vetor_posicao[1] + ")\n");
        }

        else { // Se sair do ambiente, volta para a posição inicial.
            System.out.print("O/A " + nomeRobo + " tentou sair do ambiente, logo ele/a permanece na posição: ");
            roboCavador1.mover(-deltaX, -deltaY);
            vetor_posicao = roboCavador1.getPosicao();
            System.out.print("(" + vetor_posicao[0] + "," + vetor_posicao[1] + ")\n");
        }

        // Testes do roboCriaObstaculo1
        int[] posicao = roboCriaObstaculo1.getPosicao() ;
        roboCriaObstaculo1.soltarBlocos();
        ambiente1.adicionarBloco(posicao);
        ambiente1.getBlocos();
        

        // Testes do roboFlutuador1


        // Testes do robo


    
    }
}