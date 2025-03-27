public class Ambiente{ // Atributos.

    private int largura;
    private int comprimento;
    private int altura;
    private static int numRobos;
    private Robo[] robosAtivos;
    private int[2][] obstaculos;

    public Ambiente() { // Construtor.
        largura = 100;
        comprimento = 100;
        altura = 100;
        numRobos = 0;
    }

    public void adicionarRobo(Robo robo){
        robosAtivos[numRobos] = robo;
        numRobos++;
    }
    
    public int dentroDosLimites(int x, int y) {
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

//verificar a altura depois de cavar/voar 
}
