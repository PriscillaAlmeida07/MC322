package subsistemas;

import ambiente.Ambiente;
import interfaces.*;
import java.util.ArrayList;

import robo.AgenteInteligente;
import robo.Robo;
import sensores.*;

public class GerenciadorSensores {

    // Subsistema que auxilia os agentes inteligentes na utilização e tratamento dos resultados de qualquer sensor.
    
    private ArrayList<Entidade> robosEmAlcance;
    private ArrayList<Robo> robosMortos;
    private ArrayList<Robo> robosFracos;
    private ArrayList<Robo> robosCuradores;
    private ArrayList<Robo> robosProtegidos;

    // Construtor.
    public GerenciadorSensores(){
        robosEmAlcance = null;
        robosMortos = null;
        robosFracos = null;
        robosCuradores = null;
        robosProtegidos = null;
    }

    // Obtém a lista de robôs no raio de alcançe da missão.
    public ArrayList<Entidade> getRobosEmAlcance(){
        return robosEmAlcance;
    }

    // Obtém a lista de robôs mortos afetados pela missão.
    public ArrayList<Robo> getRobosMortos(){
        return robosMortos;
    }

    // Obtém a lista de robôs fracos afetados pela missão.
    public ArrayList<Robo> getRobosFracos(){
        return robosFracos;
    }

    // Obtém a lista de robôs curadores afetados pela missão.
    public ArrayList<Robo> getRobosCuradores(){
        return robosCuradores;
    }

    // Obtém a lista de robôs protegidos afetados pela missão.
    public  ArrayList<Robo> getRobosProtegidos(){
        return robosProtegidos;
    }
    
    // Utiliza o sensor robôs para o raio da missão e depois reseta as suas configurações.
    public ArrayList<Entidade> utilizarSensorRobos(Ambiente ambiente, Robo robo, int raio){

        // Adquire os componentes necessários para o funcionamento do sensor e determina o raio desejado
        int[] vetorPosicao = robo.getPosicao();
        Sensor sensor = robo.getSensorRobos();
        sensor.setRaio(raio);

        // Utiliza o sensor e retorna ele às suas configurações iniciais
        ArrayList<Entidade> robosEmAlcance = sensor.monitorar(ambiente, vetorPosicao, 1);
        sensor.setRaio(4);

        // Retorno.
        this.robosEmAlcance = robosEmAlcance;
        return robosEmAlcance;
    }

    // Confere quais robôs no alcançe estão protegidos.
    public ArrayList<Robo> encontrarRobosProtegidos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosProtegidos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo && !(robo instanceof AgenteInteligente)){ // Garante que não estaremos protegendo agentes, apenas robôs
                robosProtegidos.add(robo);
            }
        }
        this.robosProtegidos = robosProtegidos;
        return robosProtegidos;       
    }

    // Confere quais robôs no alcançe estão mortos.
    public ArrayList<Robo> encontrarRobosMortos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosMortos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo && !(robo instanceof AgenteInteligente)){ // Garante que não estaremos protegendo agentes, apenas robôs
                if (robo.getVida() == 0){
                    robosMortos.add(robo);
                }
            }
        }
        this.robosMortos = robosMortos;
        return robosMortos;
    }

    // Confere quais robôs no alcançe estão fracos.
    public ArrayList<Robo> encontrarRobosFracos(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosFracos = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Robo robo && !(robo instanceof AgenteInteligente)){ // Garante que não estaremos protegendo agentes, apenas robôs
                if ((robo.getVida() >= 1) && (robo.getVida() < 5)){
                    robosFracos.add(robo);
                }
            }
        }
        this.robosFracos = robosFracos;
        return robosFracos;
    }

    // Confere quais robôs no alcançe são curadores (e converte para uma ArrayList de Robo).
    public ArrayList<Robo> encontrarRobosCuradores(ArrayList<Entidade> robosEmAlcance){
        ArrayList<Robo> robosCuradores = new ArrayList<>();
        for (int i = 0; i < robosEmAlcance.size(); i++){
            if (robosEmAlcance.get(i) instanceof Curador curador){ // Aqui só curadores serão adicionados, já é garantido que os agentes não serão armazenados no array
                if (curador instanceof Robo robo){
                    robosCuradores.add(robo);
                }
            }
        }
        this.robosCuradores = robosCuradores;
        return robosCuradores;
    }
}
