import java.util.ArrayList;
import java.util.Scanner;

public class RoboObstaculoTerrestre extends RoboTerrestre implements Curador {
    
    // Descrição do Robo Obstaculo Terrestre:
    @Override
    public String getDescricao(){return "Robô terrestre capaz de criar obstáculos posicionando blocos no solo";}

    // Atributos:
    private int numBlocos;
    private final int reparo;

    // Construtor.
    public RoboObstaculoTerrestre(String nome, String id, EstadoRobo estado){
        super(nome, id, estado);
        numBlocos = 1;
        reparo = 3;

        adicionaSensorReporBlocos(new SensorReporBlocos(30, TipoSensor.REPORBLOCOS));
    }

    // Obtém o reparo que o robô é capaz de realizar
    @Override
    public int getReparo(){
        return reparo;
    }

    // Aumenta a quantidade de blocos que o robô carrega.
    public void reporBlocos(){
        numBlocos += 3;
    }

    // Executa uma tarefa inerente ao Robô Obstaculo Terrestre.
    @Override
    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY)throws ForaDosLimitesException{

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

    // Cura todos os robôs próximos.
    @Override
    public void curar(Ambiente ambiente){
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = getSensorRobos().monitorar(ambiente, vetorPosicao, 1);

        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo) {
                robo.setVida(reparo);
            }
        }
    }
}
