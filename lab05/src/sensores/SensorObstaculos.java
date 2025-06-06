package sensores;

import java.util.ArrayList;

import ambiente.Ambiente;
import enums.TipoSensor;
import interfaces.Entidade;
import obstaculos_tapetes.Obstaculo;

public class SensorObstaculos extends Sensor {

    // Sensor capaz de descobrir a posição e o tipo dos obstáculos que estão a um raio ou menos do robô que o contém.
    
    // Construtor.
    public SensorObstaculos(double raio, TipoSensor tipo){
        super(raio, tipo);
    }

    // Encontra os obstáculos próximos e guarda-os em uma ArrayList.
    @Override
    public ArrayList<Entidade> monitorar(Ambiente ambiente, int[] posicaoRobo, int caso){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Entidade> entidades = ambiente.getArrayEntidades();
        double raio = getRaio();
        ArrayList<Entidade> resultado = new ArrayList<>();

        if (caso == 1){ // Caso robô obstáculo terrestre
            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Obstaculo obstaculo) {
                    if ((obstaculo.getZ() == 25) && Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - obstaculo.getX()), 2)) + (Math.pow((posicaoRobo[1] - obstaculo.getY()), 2))) { // Obstáculo no chão e próximo
                        resultado.add(obstaculo);
                    }
                }
            }

        } else { // caso == 2 *Outros robôs*
            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Obstaculo obstaculo){
                    if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - obstaculo.getX()), 2)) + (Math.pow((posicaoRobo[1] - obstaculo.getY()), 2)) + (Math.pow((posicaoRobo[2] - obstaculo.getZ()), 2))) {
                        resultado.add(obstaculo);
                    }
                }
            } 
        }
        return resultado;
    }

    // Imprime os obstáculos encontrados pelo sensor.
    @Override
    public void imprimirResultado(ArrayList<Entidade> resultado, int[] posicaoRobo){
        
        System.out.println("Resultado do sensor de obstáculos para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + "," + (posicaoRobo[2] - 25) + ")");
        for (int i = 0; i < resultado.size(); i++){
            if (resultado.get(i) instanceof Obstaculo obstaculo){
                System.out.println("Foi capturado um(a) " + obstaculo.getTipoObstaculo().getNome() + " na posição mínima (" + obstaculo.getX() + "," + obstaculo.getY() + "," + obstaculo.getZObstaculo() + ") e máxima (" + 
                                    (obstaculo.getX() + obstaculo.getTipoObstaculo().getLargura()) + "," + (obstaculo.getY() + obstaculo.getTipoObstaculo().getComprimento()) + "," + (obstaculo.getZObstaculo() + obstaculo.getTipoObstaculo().getAltura()) + ")");
            }
        }
        if (resultado.isEmpty()){
            System.out.println("Nenhum obstáculo capturado");
        }
        System.out.print("\n");
    }
}
