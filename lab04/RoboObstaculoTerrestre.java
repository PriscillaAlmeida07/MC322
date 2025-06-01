import java.util.ArrayList;
import java.util.Scanner;

public class RoboObstaculoTerrestre extends RoboTerrestre implements Curador {
    
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

    // Descrição do Robo Obstaculo Terrestre:
    @Override
    public String getDescricao(){return "Robô terrestre capaz de criar obstáculos posicionando blocos no solo";}

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
    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        soltarBlocos(ambiente);
    }

    // Define a posição que o bloco será posicionado de acordo com a direção do robô
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

    // Metodo para posicionar o bloco de acordo com a direção do robô
    private void Posicionarbloco(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException{
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar um bloco fora do ambiente");
        ambiente.verificarColisoes(x, y, z, "Erro: Tentativa de colocar um bloco em uma posição já ocupada");
        Obstaculo bloco = criarBloco(x, y, z);
        ambiente.adicionarEntidade(bloco);
        System.out.println("O bloco está na posição: (" + bloco.getX() + "," + bloco.getY() +"," + bloco.getZ() + ")");
        numBlocos--;
    }

    // Cria uma nova bloco na posição.
    private Obstaculo criarBloco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, posicaoZ);
        return bloco;
    }

    // Cura todos os robôs próximos.
    @Override
    public void curar(Ambiente ambiente) throws RoboDesligadoException{
        if(this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = getSensorRobos().monitorar(ambiente, vetorPosicao, 1);
        for (int i = 0; i < robos.size(); i++){
            if (robos.get(i) instanceof Robo robo){
                if(!robo.getID().equals(this.getID())){
                    if (robo.getVida() == 10) {
                        System.out.println("O " + robo.getNome() + " não pode ser curado, pois já está com a vida máxima");
                    } else if ((robo.getVida() + reparo) >= 10){
                        robo.setVida(10 - robo.getVida());
                        System.out.println("O " + this.getNome() + " curou completamente o " + robo.getNome() + " que possui agora 10/10 de vida");
                    } else {
                        robo.setVida(reparo);
                        System.out.println("O " + this.getNome() + " curou o " + robo.getNome() + " que possui agora " + robo.getVida() + "/10 de vida");
                    }
                }
            }
        }
        System.out.print("\n");
    }
}
