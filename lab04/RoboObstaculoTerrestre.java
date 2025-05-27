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
    public RoboObstaculoTerrestre(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
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
    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException{
        // A tarefa especifica do RobôObstaculoAereo é soltar nuvens
        soltarBlocos(ambiente);
    }
    // Posiciona uma bloco na proxima posição e mesma direção que a dele, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public void soltarBlocos(Ambiente ambiente) throws ForaDosLimitesException, ColisaoException{
        if (numBlocos == 0)
            System.out.print("Não há mais blocos disponíveis");
        else {
            int x = getX();
            int y = getY();
            int z = getZ();

            switch (getDirecao()) {
                case "Norte":     
                    x += 1; 
                    break;
                case "Sul":       
                    x -= 1;
                    break;
                case "Leste":    
                    y += 1; 
                    break;
                case "Oeste":     
                    y -= 1; 
                    break;
                case "Nordeste":  
                    x += 1; y += 1; 
                    break;
                case "Noroeste":  
                    x += 1; y -= 1; 
                    break;
                case "Sudeste":   
                    x -= 1; y += 1; 
                    break;
                case "Sudoeste": 
                    x -= 1; y -= 1; 
                    break;
            }

            Posicionarbloco(ambiente, x, y, z);
        }
    }

    private void Posicionarbloco(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException{
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar um bloco fora do ambiente");
        ambiente.estaOcupado(x, y, z, "Erro: Tentativa de colocar um bloco em uma posição já ocupada");
        Obstaculo bloco = criarbloco(x, y, z);
        ambiente.adicionarEntidade(bloco);
        numBlocos--;
    }

    // Cria uma nova bloco na posição.
    private Obstaculo criarbloco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, posicaoZ);
        return bloco;
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
