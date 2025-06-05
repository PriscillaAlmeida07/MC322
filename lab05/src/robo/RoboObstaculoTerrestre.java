package robo;

import java.util.ArrayList;
import java.util.Scanner;

public class RoboObstaculoTerrestre extends RoboTerrestre implements Curador {
    
    private int numBlocos;
    private final int reparo;

    // Construtor.
    public RoboObstaculoTerrestre(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
        numBlocos = 1;
        reparo = 3;

        adicionaSensorReporBlocos(new SensorReporBlocos(30, TipoSensor.REPORBLOCOS));
    }

    // Obtém a descrição desse robô.
    @Override
    public String getDescricao(){return "Robô terrestre capaz de criar obstáculos posicionando blocos no solo";}

    // Obtém o tanto de vida que o robô é capaz de curar.
    @Override
    public int getReparo(){
        return reparo;
    }

    // Aumenta a quantidade de blocos que o robô carrega.
    public void reporBlocos(){
        numBlocos += 3;
    }

    // A tarefa especifica do RobôObstaculoTerrestre é soltar nuvens.
    @Override
    public void  executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY, int deltaZ, int caso) throws ForaDosLimitesException, ColisaoException, RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O " + this.getNome() + " está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for curado por outro robô");

        // Realiza a tarefa
        posicaoBloco(ambiente);
    }

    // Define a posição que o bloco será posicionado de acordo com a direção do robô
    public void posicaoBloco(Ambiente ambiente) throws ForaDosLimitesException, ColisaoException {

        // Caso os blocos já tenham acabado
        if (numBlocos == 0)
            System.out.print("Não há mais blocos disponíveis");

        else { // Ainda tem blocos
            int x = getX();
            int y = getY();
            int z = getZ();

            switch (getDirecao()){
                case "Norte" -> x += 1;
                case "Sul" -> x -= 1;
                case "Leste" -> y += 1;
                case "Oeste" -> y -= 1;
                case "Nordeste" -> {
                    x += 1; y += 1;
                }
                case "Noroeste" -> {
                    x += 1; y -= 1;
                }
                case "Sudeste" -> {
                    x -= 1; y += 1;
                }
                case "Sudoeste" -> {
                    x -= 1; y -= 1;
                }
            }

            // Posiciona o bloco no local encontrado
            soltarBloco(ambiente, x, y, z);
        }
    }

    // Posiciona um bloco no ambiente.
    private void soltarBloco(Ambiente ambiente, int x, int y, int z) throws ForaDosLimitesException, ColisaoException{
        ambiente.dentroDosLimites(x, y, z, "Erro: Tentativa de colocar um bloco fora do ambiente");
        ambiente.verificarColisoes(x, y, z, "Erro: Tentativa de colocar um bloco em uma posição já ocupada");

        Obstaculo bloco = criarBloco(x, y, z);
        ambiente.adicionarEntidade(bloco);
        numBlocos--;
        System.out.println("O bloco está na posição mínima (" + bloco.getX() + "," + bloco.getY() +"," + (bloco.getZ() - 25) + ") e máxima (" +
                            (bloco.getX() + bloco.getTipoObstaculo().getLargura()) + "," + (bloco.getY() + bloco.getTipoObstaculo().getComprimento()) + "," + (bloco.getZ() + bloco.getTipoObstaculo().getAltura() - 25) + ")");
    }

    // Cria uma nova bloco na posição.
    private Obstaculo criarBloco(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, posicaoZ);
        return bloco;
    }

    // Cura todos os robôs próximos (menos ele mesmo).
    @Override
    public void curar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException {
        if (this.getEstadoRobo() == EstadoRobo.DESLIGADO)
            throw new RoboDesligadoException("O robô está desligado");
        if (this.getVida() == 0)
            throw new VidaNulaException("O " + this.getNome() + " está morto, portanto só poderá realizar ações quando for curado por outro robô");
        
        // Informações necessárias para o funcionamento da função:
        Sensor sensor = getSensorRobos();
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> robos = sensor.monitorar(ambiente, vetorPosicao, 1);

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
