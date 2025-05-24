
import java.util.ArrayList;
import java.util.Scanner;

abstract class Robo implements Sensoreavel, Comunicavel, Entidade{

    private final ArrayList<Sensor> sensores;
    private final String nome;
    private final String id;
    private String direcao;
    private EstadoRobo estado;
    private final TipoEntidade tipo;
    private int posicaoX, posicaoY, posicaoZ;
    private int vida;

    // Construtor.
    public Robo(String nome, String id){ 
        sensores = new ArrayList<>();
        sensores.add(new SensorObstaculos(40));
        sensores.add(new SensorRobo(2, TipoSensor.ROBO));

        tipo = TipoEntidade.ROBO;
        estado = EstadoRobo.DESLIGADO;
        this.id = id;
        this.nome = nome;
        posicaoX = 25;
        posicaoY = 25; 
        posicaoZ = 50;
        direcao = "Norte";
        vida = 10;
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

    public EstadoRobo getEstadoRobo(){
        return estado;
    }
    // Obtém a posição (x,y) do robô.
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

    // Realiza um movimento no plano XY.
    public void moverPara(int deltaX, int deltaY, int deltaZ){ 
        posicaoX += deltaX;
        posicaoY += deltaY;
        posicaoZ += deltaZ;
    }
    
    public void ligar(){
        estado = EstadoRobo.LIGADO;
    }

    public void desligar(){
        estado = EstadoRobo.DESLIGADO;
    }

    public abstract void executarTarefa(Scanner entrada);

    public Sensor getSensorRobo() {
        for (int i=0; i< sensores.size(); i++ ){
            if (sensores.get(i).getTipo() == TipoSensor.ROBO)
                return sensores.get(i);
        }
        return null;
    }

    // Utiliza todos os sensores do robô.
    public void acionarSensores(Ambiente ambiente, int caso){
        int[] vetorPosicao = getPosicao();
        for (int i=0; i< sensores.size(); i++ ){
            // acredito que precisaremos guardar o array de entidades
            
            sensores.get(i).monitorar(ambiente, vetorPosicao, caso);
        }
    }

    public void enviarMensagem(Comunicavel destinatario, String mensagem){

    }
    public void receberMensagem(String mensagem){

    }

    public void setVida(int dano){
        vida += dano;
    }

    public int getVida(){
        return vida;
    }

    public int getX(){return posicaoX;}
    public int getY(){ return posicaoY;}
    public int getZ(){return posicaoZ;}
    public TipoEntidade getTipo(){return tipo;}
    public abstract String getDescricao();
    public char getRepresentacao(){return 'R';}
}
