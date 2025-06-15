package subsistemas;

import java.util.ArrayList;

import ambiente.Ambiente;
import interfaces.Curador;
import interfaces.Entidade;
import robo.Robo;
import sensores.*;

public class GerenciadorSensores {
    
    private ArrayList<Entidade> robosEmAlcance;
    private ArrayList<Robo> robosFracos;
    private ArrayList<Robo> robosMortos;
    private ArrayList<Curador> robosCuradores;

    public GerenciadorSensores(){
        robosEmAlcance = null;
        robosCuradores = null;
        robosFracos = null;
        robosMortos = null;
    }

    public ArrayList<Entidade> getRobosEmAlcance(){
        return robosEmAlcance;
    }

    public ArrayList<Robo> getRobosFracos(){
        return robosFracos;
    }

    public ArrayList<Robo> getRobosMortos(){
        return robosMortos;
    }

    public ArrayList<Curador> getRobosCuradores(){
        return robosCuradores;
    }
    
    public ArrayList<Entidade> utilizarSensorRobos(Ambiente ambiente, Robo robo){

        // Adquire os componentes necessários para o funcionamento do sensor e determina o raio desejado
        int[] vetorPosicao = robo.getPosicao();
        Sensor sensor = getSensorRobos();
        sensor.setRaio(20);

        // Utiliza o sensor e retorna ele às suas configurações iniciais
        ArrayList<Entidade> robosEmAlcance = sensor.monitorar(ambiente, vetorPosicao, 1);
        sensor.setRaio(4);

        // Retorno.
        this.robosEmAlcance = robosEmAlcance;
        return robosEmAlcance;
    }

    public ArrayList<Robo> encontrarRobosMortos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosMortos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo){
                if (robo.getVida() == 0){
                    robosMortos.add(robo);
                }
            }
        }
        this.robosMortos = robosMortos;
        return robosMortos;
    }

    public ArrayList<Robo> encontrarRobosFracos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosFracos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo){
                if ((robo.getVida() >= 1) && (robo.getVida() < 5)){
                    robosFracos.add(robo);
                }
            }
        }
        this.robosFracos = robosFracos;
        return robosFracos;
    }

    public ArrayList<Curador> encontrarRobosCuradores(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Curador> robosCuradores = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Curador curador){
                robosCuradores.add(curador);
            }
        }
        this.robosCuradores = robosCuradores;
        return robosCuradores;
    }
}
