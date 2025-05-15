import java.util.ArrayList;

public class Ambiente { 

    private final int largura, comprimento, altura;
    private TipoEntidade mapa[][][];
    private ArrayList<Entidade> entidades;

    // Construtor.
    public Ambiente(){ 
        largura = 100;
        comprimento = 100;
        altura = 200;
        entidades = new ArrayList<>();
        mapa = new TipoEntidade[100][100][200];
    }

    public void inicializarMapa(){
        for(int x=0; x< largura; x++){
            for(int y=0; y< comprimento; y++){
                for (int z=0; z< altura; z++){
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                } 
            }
        }
    }

    public void adicionarEntidade(Entidade entidade){
        entidades.add(entidade);
        if(entidade instanceof Obstaculo obstaculo)
        {
            int largura = obstaculo.getTipoObstaculo().getLargura();
            int comprimento = obstaculo.getTipoObstaculo().getComprimento();
            int altura = obstaculo.getTipoObstaculo().getAltura();

            for(int x=0; x< largura; x++){
                for(int y=0; y< comprimento; y++){
                    for (int z=0; z< altura; z++){
                        mapa[entidade.getX() + x][entidade.getY() + y][entidade.getZ() + z]= TipoEntidade.OBSTACULO;
                    } 
                }
            }
        }
        else{
            mapa[entidade.getX()][entidade.getY()][entidade.getZ()]= entidade.getTipo();
        }
       
    }

    public void removerEntidade(Entidade entidade){
        entidades.remove(entidade);
        if(entidade instanceof Obstaculo obstaculo)
        {
            int largura = obstaculo.getTipoObstaculo().getLargura();
            int comprimento = obstaculo.getTipoObstaculo().getComprimento();
            int altura = obstaculo.getTipoObstaculo().getAltura();

            for(int x=0; x< largura; x++){
                for(int y=0; y< comprimento; y++){
                    for (int z=0; z< altura; z++){
                        mapa[entidade.getX() + x][entidade.getY() + y][entidade.getZ() + z]= TipoEntidade.VAZIO;
                    } 
                }
            }
        }
        else{
            mapa[entidade.getX()][entidade.getY()][entidade.getZ()]= TipoEntidade.VAZIO;
        }
       
    }

    // Confere se um robô está dentro dos limites do ambiente.
    public int dentroDosLimites(int x, int y, int z){
        if ((largura >= x && x >= 0) && (comprimento >= y && y >= 0) && (altura >= z && z >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }

    public int estaOcupado(int x, int y, int z){
        if(mapa[x][y][z] != TipoEntidade.VAZIO){
            return  0;
        } else {
            return  1;
       }
    }
/* 
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
        TipoObstaculo tipo; int[] posicaoInicial; int[] posicaoFinal = new int[2];

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

    // Confere se um robô irá bater em um obstáculo aéreo.
    public int existeObstaculoAereos(int x, int y, int z){
        TipoObstaculo tipo; int[] posicaoInicial; int[] posicaoFinal = new int[3];

        for (int i = 0; i < obstaculosAtivos.size(); i++){
            tipo = obstaculosAtivos.get(i).getTipo();
            posicaoInicial = obstaculosAtivos.get(i).getPosicao();

            // Calcula a posição final do obsaculo com base na posicao inicial
            posicaoFinal[0] = posicaoInicial[0] + tipo.getLargura();
            posicaoFinal[1] = posicaoInicial[1] + tipo.getComprimento();
            posicaoFinal[2] = posicaoInicial[2] + tipo.getAltura();

            if (((x >= posicaoInicial[0]) && (x <= posicaoFinal[0])) && ((y >= posicaoInicial[1]) && (y <= posicaoFinal[1])) && ((z >= posicaoInicial[2]) && (z <= posicaoFinal[2]))){
                return 0;
            }
        }
        return 1; // Caso o robô esteja em uma posição válida
    }

    // Confere se o robô irá parar em um tapete de reposição de blocos
    public int existeTapeteReposicao(int[] posicaoRobo){
        int[] posicaoTapete;

        for (int i = 0; i < tapetes.size(); i++){
            posicaoTapete = tapetes.get(i).getPosicao();
            if ((posicaoRobo[0] == posicaoTapete[0]) && (posicaoRobo[1] == posicaoTapete[1])) {
                return 1;
            }
        }
        return 0; // Se ele não estiver parado em um tapete de reposição
    */
}
