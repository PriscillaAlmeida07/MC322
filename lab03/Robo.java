
import java.util.ArrayList;

public class Robo {

    private final ArrayList<Sensor> sensores;
    private final String nome;
    private String direcao;
    private int posicaoX;
    private int posicaoY;

    // Construtor.
    public Robo(String nome){ 
        sensores = new ArrayList<>();
        sensores.add(new SensorObstaculos());

        this.nome = nome;
        posicaoX = 50;
        posicaoY = 50; 
        direcao = "Norte";
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

    // Obtém a posição (x,y) do robô.
    public int[] getPosicao(){
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

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
    public void mover(int deltaX, int deltaY){ 
        posicaoX += deltaX;
        posicaoY += deltaY;
    }

    // Utiliza todos os sensores do robô.
    public void usarSensores(Ambiente ambiente, int[] vetorPosicao){
        for (int i=0; i< sensores.size(); i++ ){
            sensores.get(i).monitorar(ambiente, vetorPosicao);
        }
    }
}
