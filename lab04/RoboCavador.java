import java.util.ArrayList;

public class RoboCavador extends RoboTerrestre implements Atacante { 

    // Robô terrestre que consegue perfurar o solo.
    private int profundidade;
    private final int profundidadeMaxima;
    private final int dano;

    // Construtor.
    public RoboCavador(String nome, String id){ 
        super(nome, id);
        profundidade = 0;
        profundidadeMaxima = 100;
        dano = 3;
    }

    @Override
    public int getDano(){
        return dano;
    }

    // Obtém a profundidade do robô.
    public int getProfundidade(){
        return profundidade;
    }

    // Obtém a posição (x,y,z) do robô.
    @Override
    public int[] getPosicao(){ 
        int[] vetor1 = super.getPosicao();

        int[] vetor2 = new int[3];
        vetor2[0] = vetor1[0];
        vetor2[1] = vetor1[1];
        vetor2[2] = -profundidade;

        return vetor2;
    }

    // Define a profundidade do robô.
    public void setProfundidade(int deltaZ){
        profundidade -= deltaZ;
    }

    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de perfuração inserido");
        } else if ((deltaZ + profundidade) > profundidadeMaxima){
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else if (deltaZ == 0){
            System.out.println("O robô não cavou");
        } else { // Valor válido de perfuração
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração");
        }
    }

    // Cria um novo buraco na posição
    public Obstaculo criarBuraco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo buraco = new Obstaculo(TipoObstaculo.BURACO, posicaoX, posicaoY, posicaoZ);
        return buraco;
    }

    @Override
    public void atacar(Ambiente ambiente){
        Sensor sensor = getSensorRobo();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(-dano);
            }
        }
    }
}
