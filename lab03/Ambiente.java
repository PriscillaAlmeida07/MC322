import java.util.ArrayList;

public class Ambiente { 

    private final int largura;
    private final int comprimento;
    private ArrayList<Robo> robosAtivos;
    private ArrayList<Obstaculo> obstaculosAtivos;

    // Construtor.
    public Ambiente(){ 
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
    
    // Confere se um robô está dentro dos limites do ambiente.
    public int dentroDosLimites(int x, int y){
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

    // Lista todos os robôs que estão no ambiente e as suas informações.
    public void getTodosRobos(){
        String nome; int[] posicao; String direcao;

        for (int i = 0; i < robosAtivos.size(); i++){
            nome = robosAtivos.get(i).getNome();
            posicao = robosAtivos.get(i).getPosicao();
            direcao = robosAtivos.get(i).getDirecao();

            if (posicao.length == 2){
                System.out.println("O robô " + nome + " está na posição (" + posicao[0] + "," + posicao[1] + ") e está virado para o " + direcao);

            } else { // posicao.length == 3
                System.out.println("O robô " + nome + " está na posição (" + posicao[0] + "," + posicao[1] + "," + posicao[2] + ") e está virado para o " + direcao);
            }
        }
        System.out.print("\n");
    }

    // Lista todos os obstáculos que estão no ambiente e suas informações.
    public void getTodosObstaculos(){
        TipoObstaculo tipo; int[] posicao;

        for (int i = 0; i < obstaculosAtivos.size(); i++){
            tipo = obstaculosAtivos.get(i).getTipo();
            posicao = obstaculosAtivos.get(i).getPosicao();

            System.out.print("Existe um(a) " + tipo.getNome() + " na posição mínima (" + posicao[0] + "," + posicao[1] + "," + posicao[2] +") e máxima (");

            posicao[0] += tipo.getLargura();
            posicao[1] += tipo.getComprimento();
            posicao[2] += tipo.getAltura();

            System.out.println(posicao[0] + "," + posicao[1] + "," + posicao[2] +")");
        }
        System.out.print("\n");
    }

 
    // Confere se um robô irá bater em um obstáculo terrestre.
    public int existeObstaculoTerrestres(int x, int y){
        TipoObstaculo tipo; int[] posicaoInicial; int[] posicaoFinal = new int[3];
        for (int i = 0; i < obstaculosAtivos.size(); i++){
            tipo = obstaculosAtivos.get(i).getTipo();
            posicaoInicial = obstaculosAtivos.get(i).getPosicao();

            // Calcula a posição final do obsaculo com base na posicao inicial
            posicaoFinal[0] = posicaoInicial[0] + tipo.getLargura();
            posicaoFinal[1] = posicaoInicial[1] + tipo.getComprimento();

            if (((x >= posicaoInicial[0]) && (x <= posicaoFinal[0])) && ((y >= posicaoInicial[1]) && (y <= posicaoFinal[1]))){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }
/*
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
