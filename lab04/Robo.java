import java.util.ArrayList;
import java.util.Scanner;

abstract class Robo implements Entidade, Sensoreavel, Comunicavel {

    // Identificação do robô.
    private final String nome, id;

    // Posicionamento do robô.
    private final int posicaoX, posicaoY, posicaoZ;
    private String direcao;

    // Outras características.
    private EstadoRobo estado;
    private int vida;
    private final ArrayList<Sensor> sensores;

    // Construtor.
    public Robo(String nome, String id, EstadoRobo estado){ 

        this.id = id; this.nome = nome; this.estado = estado;
        posicaoX = 25; posicaoY = 25; posicaoZ = 0;
        direcao = "Norte";
        vida = 10;

        // Cria o ArrayList de sensores do robô e adiciona os sensores comuns a todos os robôs: obstáculos e robôs
        sensores = new ArrayList<>();
        sensores.add(new SensorObstaculos(40, TipoSensor.OBSTACULOS));
        sensores.add(new SensorRobos(2, TipoSensor.ROBOS));
    }

    // Adiciona um sensor de reposição de blocos, se o robô o possuir.
    public void adicionaSensorReporBlocos(SensorReporBlocos sensorReporBlocos){
        sensores.add(sensorReporBlocos);
    }

    // Obtém o nome do robô.
    public String getNome(){
        return nome;
    }

    // Obtém a direção do robô.
    public String getDirecao(){
        return direcao;
    }

    // Obtém o estado do robô (ligado/desligado).
    public EstadoRobo getEstadoRobo(){
        return estado;
    }

    // Liga o robô.
    public void ligar(){
        estado = EstadoRobo.LIGADO;
    }

    // Desliga o robô.
    public void desligar(){
        estado = EstadoRobo.DESLIGADO;
    }

    // Obtém a vida atual do robô.
    public int getVida(){
        return vida;
    }

    // Define a vida do robô.
    public void setVida(int dano){
        vida += dano;
    }

    // Obtém a posição (x,y,z) do robô.
    public int[] getPosicao(){
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = posicaoY;

        return vetor;
    }
        
    // Descobre e define a direção do robô.
    public void setDirecao(int deltaX, int deltaY){
        if (deltaX > 0){
            if (deltaY > 0){
                direcao = "Nordeste";
            } else if (deltaY == 0){
                direcao = "Leste";
            } else { // deltaY < 0
                direcao = "Sudeste";
            }

        } else if (deltaX == 0){
            if (deltaY > 0){
                direcao = "Norte";
            } else if (deltaY < 0){
                direcao = "Sul";
            }
            
        } else if (deltaX < 0){
            if (deltaY > 0){
                direcao = "Noroeste";
            } else if (deltaY == 0){
                direcao = "Oeste";
            } else { // deltaY < 0
                direcao = "Sudoeste";
            }
        }
    }

    // Realiza um movimento no ambiente.
    public void moverPara(int deltaX, int deltaY, int deltaZ, Ambiente ambiente) throws ForaDosLimitesException{
        ambiente.foraDosLimites(deltaX, deltaY, deltaZ);
    }

    /* 
    public void moverPara(int deltaX, int deltaY, int deltaZ){ 
        posicaoX += deltaX;
        posicaoY += deltaY;
        posicaoZ += deltaZ;
    }*/

    // Todos os robôs devem implementar a função "executarTarefa"
    public abstract void executarTarefa(Scanner entrada, Ambiente ambiente, int deltaX, int deltaY)throws ForaDosLimitesException;

    // Encontra o sensor de robôs no ArrayList de sensores
    public Sensor getSensorRobos() {
        for (int i=0; i< sensores.size(); i++ ){
            if (sensores.get(i).getTipo() == TipoSensor.ROBOS)
                return sensores.get(i);
        }
        return null;
    }

    // Encontra o sensor de obstáculos no ArrayList de sensores
    public Sensor getSensorObstaculos() {
        for (int i=0; i< sensores.size(); i++ ){
            if (sensores.get(i).getTipo() == TipoSensor.OBSTACULOS)
                return sensores.get(i);
        }
        return null;
    }

    // Utiliza e imprime o resultado de todos os sensores do robô.
    @Override
    public void acionarSensores(Ambiente ambiente, int caso){
        int[] vetorPosicao = getPosicao();
        ArrayList<Entidade> resultado;

        for (int i=0; i< sensores.size(); i++ ){
            resultado = sensores.get(i).monitorar(ambiente, vetorPosicao, caso);
            sensores.get(i).imprimirResultado(resultado, vetorPosicao);
        }
    }

    // Envia uma mensagem para outro robô.
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem){

    }

    // Recebe uma mensagem de outro robô (se não estiver desligado).
    @Override
    public void receberMensagem(String mensagem){

    }

    // Obtém posições do obstáculo:

    @Override
    public int getX(){return posicaoX;}
    @Override
    public int getY(){return posicaoY;}
    @Override
    public int getZ(){return posicaoZ;}

    // Obtém informações sobre o obstáculo:

    @Override
    public TipoEntidade getTipo(){return TipoEntidade.ROBO;}
    @Override
    public abstract String getDescricao();
    @Override
    public char getRepresentacao(){return 'R';}
}
