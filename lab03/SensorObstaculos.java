import java.util.ArrayList;

public class SensorObstaculos extends Sensor {
    
    public SensorObstaculos() {
        super();
    }

    public void monitorar(Ambiente ambiente, int[] vetorPosicao){

        ArrayList<Obstaculo> obstaculos = ambiente.getArrayObstaculos();
        int[] posicaoObstaculo = new int[3];
        double raio = getRaio();
        int capturado = 0;
        if(vetorPosicao.length == 2){

            for (int i=0; i< obstaculos.size(); i++ ){
                posicaoObstaculo = obstaculos.get(i).getPosicao();
                if((posicaoObstaculo[2] != 0) && Math.pow(raio, 2) >= (Math.pow((vetorPosicao[0] - posicaoObstaculo[0]),2)) + (Math.pow((vetorPosicao[1] - posicaoObstaculo[1]),2))){
                    capturado = 1;
                    System.out.println("Foi capturado um " + obstaculos.get(i).getTipo().getNome() + "na posição (" + posicaoObstaculo[0] + "," + posicaoObstaculo[1] + ")");
                }
            }
        }
        else{
            for (int i=0; i< obstaculos.size(); i++ ){
                posicaoObstaculo = obstaculos.get(i).getPosicao();
                if(Math.pow(raio, 2) >= (Math.pow((vetorPosicao[0] - posicaoObstaculo[0]),2)) + (Math.pow((vetorPosicao[1] - posicaoObstaculo[1]),2)) +  (Math.pow((vetorPosicao[2] - posicaoObstaculo[2]),2))){
                    capturado = 1;
                    System.out.println("Foi capturado um " + obstaculos.get(i).getTipo().getNome() + "na posição (" + posicaoObstaculo[0] + "," + posicaoObstaculo[1] + "," + posicaoObstaculo[2] + ")");
                }
            } 
        }

        if(capturado == 0){
            System.out.println("Nada encontrado");
        }
    }
}
