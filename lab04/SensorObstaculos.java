import java.util.ArrayList;

public class SensorObstaculos extends Sensor {

    // Sensor capaz de descobrir a posição e o tipo dos obstáculos que estão a um raio ou menos do robô que o contém.
    
    // Construtor.
    public SensorObstaculos(double raio, TipoSensor tipo){
        super(raio, tipo);
    }

    // Encontra e imprime os obstáculos próximos.
    @Override
    public ArrayList<Entidade> monitorar(Ambiente ambiente, int[] posicaoRobo, int caso){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Entidade> entidades = ambiente.getArrayEntidades();
        double raio = getRaio();
        ArrayList<Entidade> resultado = new ArrayList<>();

        if (caso == 1){ // Caso robô obstáculo terrestre
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + ")");

            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Obstaculo obstaculo) {
                    if ((obstaculo.getZ() == 0) && Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - obstaculo.getX()), 2)) + (Math.pow((posicaoRobo[1] - obstaculo.getY()), 2))) { // Obstáculo no chão e próximo
                        resultado.add(obstaculo);
                    }
                }
            }

        } else { // (posicaoRobo.length == 3) *Outros robôs*
            System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + "," + posicaoRobo[2] + ")");

            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Obstaculo obstaculo) {
                    if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - obstaculo.getX()), 2)) + (Math.pow((posicaoRobo[1] - obstaculo.getY()), 2)) + (Math.pow((posicaoRobo[2] - obstaculo.getZ()), 2))) {
                        resultado.add(obstaculo);
                    }
                }
            } 
        }
        return resultado;
    }
}

// System.out.println("Foi capturado um(a) " + obstaculo.getTipoObstaculo().getNome() + " na posição (" + obstaculo.getX() + "," + obstaculo.getY() + ")");
// System.out.println("Foi capturado um(a) " + obstaculo.getTipoObstaculo().getNome() + " na posição (" + obstaculo.getX() + "," + obstaculo.getY() + "," + obstaculo.getZ() + ")");