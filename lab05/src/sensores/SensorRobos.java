package sensores;

import java.util.ArrayList;

import ambiente.Ambiente;
import enums.TipoSensor;
import interfaces.Entidade;
import robo.Robo;

public class SensorRobos extends Sensor {

    // Sensor capaz de detectar robôs próximos (em distância para ataque/cura).
    
    // Construtor.
    public SensorRobos(double raio, TipoSensor tipo){
        super(raio, tipo);
    }

    // Encontra os robôs proximos e guarda-os em uma ArrayList.
    @Override
    public ArrayList<Entidade> monitorar(Ambiente ambiente, int[] posicaoRobo, int caso){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Entidade> entidades = ambiente.getArrayEntidades();
        double raio = getRaio();
        ArrayList<Entidade> resultado = new ArrayList<>();

        if (caso == 1){ // Caso robô obstáculo terrestre
            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Robo robo){
                    if ((robo.getZ() == 25) && (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - robo.getX()), 2)) + (Math.pow((posicaoRobo[1] - robo.getY()), 2)))){ // Obstáculo no chão e próximo
                        resultado.add(robo);
                    }
                }
            }

        } else { // caso == 2 *Outros robôs*
            for (int i = 0; i < entidades.size(); i++){
                if (entidades.get(i) instanceof Robo robo){
                    if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - robo.getX()), 2)) + (Math.pow((posicaoRobo[1] - robo.getY()), 2)) + (Math.pow((posicaoRobo[2] - robo.getZ()), 2))){
                        resultado.add(robo);
                    }
                }
            } 
        }
        return resultado;
    }

    // Imprime os robôs encontrados pelo sensor.
    @Override
    public void imprimirResultado(ArrayList<Entidade> resultado, int[] posicaoRobo){
        
        System.out.println("Resultado do sensor de robôs para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + "," + (posicaoRobo[2] - 25) + ")");
        for (int i = 0; i < resultado.size(); i++){
            if (resultado.get(i) instanceof Robo robo){
                if ((posicaoRobo[0] != robo.getX()) || (posicaoRobo[1] != robo.getY()) || (posicaoRobo[2] != robo.getZ())){
                    System.out.println("Foi capturado um robô na posição: (" + robo.getX() + "," + robo.getY() + "," + robo.getZUsuario() + ")");
                }
            }
        }
        if (resultado.isEmpty()){
            System.out.println("Nenhum robô capturado");
        }
        System.out.print("\n");
    }
}
