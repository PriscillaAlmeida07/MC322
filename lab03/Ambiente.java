import java.util.ArrayList;

public class Ambiente { 

    private int largura;
    private int comprimento;
    private ArrayList<Robo> robosAtivos;
    private ArrayList<Obstaculo> obstaculosAtivos;

    // Construtor.
    public Ambiente() { 
        largura = 100;
        comprimento = 100;
        robosAtivos = new ArrayList<>();
        obstaculosAtivos = new ArrayList<>();
    }

    // Registra um novo robô.
    public void adicionarRobo(Robo robo){
        robosAtivos.add(robo);
    }

    // Registra um novo obstáculo.
    public void adicionarObstaculo(Obstaculo obstaculo){
        obstaculosAtivos.add(obstaculo);
    }
    
    // Confere se um robô está dentro dos limites do ambiente
    public int dentroDosLimites(int x, int y){
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

/* 
    // Confere se um robô irá bater em um obstáculo terrestre.
    public int existeObstaculoTerrestres(int x, int y){
        for (int i = 0; i < numObstaculosTerrestres; i++){
            if ((obstaculosTerrestres[i][0] == x) && (obstaculosTerrestres[i][1] == y)){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }

    // Confere se um robô irá bater em um obstáculo aéreo.
    public int existeObstaculoAereos(int x, int y, int z){
        for (int i = 0; i < numObstaculosAereos; i++){
            if ((obstaculosAereos[i][0] == x) && (obstaculosAereos[i][1] == y) && (obstaculosAereos[i][2] == z)){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }
        */
}
