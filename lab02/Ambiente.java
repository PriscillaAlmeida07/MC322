public class Ambiente{ // Atributos.

    private int largura;
    private int comprimento;
    private static int numRobos;
    private static int numObstaculosTerrestres;
    private static int numObstaculosAereos;
    private Robo[] robosAtivos;
    private int[][] obstaculosTerrestres;
    private int[][] obstaculosAereos;

    public Ambiente() { // Construtor.
        largura = 100;
        comprimento = 100;

        numObstaculosTerrestres = 0;
        numRobos = 0;
        this.robosAtivos = new Robo[10];
        this.obstaculosTerrestres = new int[100][2];
    }

    public void adicionarRobo(Robo robo){
        robosAtivos[numRobos] = robo;
        numRobos++;
    }

    public void adicionarBloco(int[] vetorPosicao){
        obstaculosTerrestres[numObstaculosTerrestres] = vetorPosicao;
        numObstaculosTerrestres++;
    }

    public int[][] getBlocos(){
        return obstaculosTerrestres;
    }    
    
    public int dentroDosLimites(int x, int y){
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

    public int existeObstaculo(int x, int y){
        for (int i = 0; i < numObstaculosTerrestres; i++){
            if ((obstaculosTerrestres[i][0] == x) && (obstaculosTerrestres[i][1] == y)){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }

//verificar a altura depois de cavar/voar 
}
