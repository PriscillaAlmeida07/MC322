import java.util.ArrayList;

public class RoboObstaculoTerrestre extends RoboTerrestre implements Curador {
    
    // Robô capaz de criar obstáculos posicionando blocos no solo.
    private int numBlocos;
    private final int reparo;

    // Construtor.
    public RoboObstaculoTerrestre(String nome, String id){
        super(nome, id);
        numBlocos = 1;
        adicionaSensorReporBlocos(new SensorReporBlocos());
        reparo = 3;
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarBlocos(int posicaoX, int posicaoY){
        if ((numBlocos == 0) || ((posicaoX > 96) || (posicaoY > 96))){
            System.out.print("Não há mais blocos disponíveis e/ou posição inválida para adicionar um bloco ao ambiente");
            return null;
        } else { // Ainda tem blocos e a posição é válida.
            Obstaculo bloco = criarBloco(posicaoX, posicaoY);
            numBlocos--;
            return bloco;
        }
    }

    // Cria um novo bloco na posição.
    private Obstaculo criarBloco(int posicaoX, int posicaoY){
        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, 0);
        return bloco;
    }

    // Aumenta a quantidade de blocos que o robô carrega
    public void reporBlocos(){
        numBlocos += 3;
    }

    
    @Override
    public int getReparo(){
        return reparo;
    }

    @Override
    public void curar(Ambiente ambiente){
        Sensor sensor = getSensorRobo();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(reparo);
            }
        }
    }
}
