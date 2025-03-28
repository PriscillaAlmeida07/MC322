public class Ambiente{ 

    private int largura;
    private int comprimento;
    private static int numRobos;
    private static int numObstaculosTerrestres;
    private static int numObstaculosAereos;
    private Robo[] robosAtivos;
    private int[][] obstaculosTerrestres;
    private int[][] obstaculosAereos;

    // Construtor.
    public Ambiente() { 
        largura = 100;
        comprimento = 100;

        numObstaculosTerrestres = 0;
        numObstaculosAereos = 0;
        numRobos = 0;
        robosAtivos = new Robo[10];
        obstaculosTerrestres = new int[100][2];
        obstaculosAereos = new int[100][3];
    }

    public void adicionarRobo(Robo robo){
        robosAtivos[numRobos] = robo;
        numRobos++;
    }

    public void adicionarBloco(int[] vetorPosicao){
        obstaculosTerrestres[numObstaculosTerrestres] = vetorPosicao;
        numObstaculosTerrestres++;
    }

    public void adicionarNuvem(int[] vetorPosicao){
        obstaculosAereos[numObstaculosAereos] = vetorPosicao;
        numObstaculosAereos++;
    }
    
    public int dentroDosLimites(int x, int y){
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

    public int existeObstaculoTerrestres(int x, int y){
        for (int i = 0; i < numObstaculosTerrestres; i++){
            if ((obstaculosTerrestres[i][0] == x) && (obstaculosTerrestres[i][1] == y)){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }

    public int existeObstaculoAereos(int x, int y, int z){
        for (int i = 0; i < numObstaculosAereos; i++){
            if ((obstaculosAereos[i][0] == x) && (obstaculosAereos[i][1] == y) && (obstaculosAereos[i][2] == z)){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }
}
