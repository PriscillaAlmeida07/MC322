import java.util.ArrayList;

public class SensorRobo extends Sensor {
    
    public SensorRobo() {
        super();
    }
    @Override
    public ArrayList<Entidade> monitorar(Ambiente ambiente, int[] posicaoRobo, int caso){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Entidade> entidades = ambiente.getArrayEntidades();
        double raio = getRaio();
        ArrayList<Entidade> resultado = new ArrayList<>();

        if (caso == 1){ // Caso robô obstáculo terrestre
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + ")");

            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Robo robo) {
                    if ((robo.getZ() == 0) && Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - robo.getX()), 2)) + (Math.pow((posicaoRobo[1] - robo.getY()), 2))) { // Obstáculo no chão e próximo
                        resultado.add(robo);
                    }
                }
            }

        } else { // (posicaoRobo.length == 3) *Outros robôs*
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + "," + posicaoRobo[2] + ")");

            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Robo robo) {
                    if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - robo.getX()), 2)) + (Math.pow((posicaoRobo[1] - robo.getY()), 2)) + (Math.pow((posicaoRobo[2] - robo.getZ()), 2))) {
                        resultado.add(robo);
                    }
                }
            } 
        }
        return resultado;
    }
}
