import java.util.ArrayList;

public class SensorObstaculos extends Sensor {

    // Sensor capaz de descobrir a posição e o tipo dos obstáculos que estão a um raio ou menos do robô que o contém.
    
    // Construtor.
    public SensorObstaculos(){
        super();
    }

    // Encontra e imprime os obstáculos próximos.
    @Override
    public void monitorar(Ambiente ambiente, int[] posicaoRobo){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Obstaculo> obstaculos = ambiente.getArrayObstaculos();
        int[] posicaoObstaculo;
        double raio = getRaio();
        int capturado = 0;

        if (posicaoRobo.length == 2){ // Caso robô obstáculo terrestre
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + ")");

            for (int i = 0; i < obstaculos.size(); i++){
                posicaoObstaculo = obstaculos.get(i).getPosicao();
                if ((posicaoObstaculo[2] == 0) && Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - posicaoObstaculo[0]), 2)) + (Math.pow((posicaoRobo[1] - posicaoObstaculo[1]), 2))) { // Obstáculo no chão e próximo
                    System.out.println("Foi capturado um(a) " + obstaculos.get(i).getTipo().getNome() + " na posição (" + posicaoObstaculo[0] + "," + posicaoObstaculo[1] + ")");
                    capturado++;
                }
            }

        } else { // (posicaoRobo.length == 3) *Outros robôs*
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + "," + posicaoRobo[2] + ")");

            for (int i = 0; i < obstaculos.size(); i++){
                posicaoObstaculo = obstaculos.get(i).getPosicao();
                if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - posicaoObstaculo[0]), 2)) + (Math.pow((posicaoRobo[1] - posicaoObstaculo[1]), 2)) + (Math.pow((posicaoRobo[2] - posicaoObstaculo[2]), 2))) {
                    System.out.println("Foi capturado um(a) " + obstaculos.get(i).getTipo().getNome() + " na posição (" + posicaoObstaculo[0] + "," + posicaoObstaculo[1] + "," + posicaoObstaculo[2] + ")");
                    capturado++;
                }
            } 
        }

        if (capturado == 0){
            System.out.println("Nenhum obstáculo capturado");
        }
        System.out.print("\n");
    }
}
