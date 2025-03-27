public class Ambiente{ // Atributos.

    private int largura;
    private int comprimento;
    private int altura;
    private static int numRobos;
    private static int numObstaculos;
    private Robo[] robosAtivos;
    private int[][] obstaculos;

    public Ambiente() { // Construtor.
        largura = 100;
        comprimento = 100;
        altura = 100;
        numObstaculos = 0;
        numRobos = 0;
        this.obstaculos = new int[100][2];
    }

    public void adicionarRobo(Robo robo){
        robosAtivos[numRobos] = robo;
        numRobos++;
    }

    public void adicionarBloco(int[] vetorPosicao){
        obstaculos[numObstaculos] = vetorPosicao;
        numObstaculos++;
    }

    public void getBlocos(){
        System.out.println(obstaculos[0][0] + "," + obstaculos[0][1]);
    }
    
    public int dentroDosLimites(int x, int y) {
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

//verificar a altura depois de cavar/voar 
}
