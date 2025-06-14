package missao;

import java.util.ArrayList;

import ambiente.Ambiente;
import comunicacao.CentralComunicacao;
import interfaces.Entidade;
import robo.Robo;

public class MissaoSeguranca implements Missao {
    
    private final double raio;

    public MissaoSeguranca(){
        raio = 10;
    }

    @Override
    // acho q as missoes sempre vao precisar de sensores entao faz sentido retornar arrays
    public ArrayList<Robo> executar(Robo robo, Ambiente ambiente, CentralComunicacao centralComunicacao, ArrayList<Entidade> robosProtegidos){
        ArrayList<Robo> robos = new ArrayList<>(); // so criei por ser abstract e precisar retornar nao era necessario

        for (int i = 0; i < robosProtegidos.size(); i++){
            if(robosProtegidos.get(i) instanceof Robo roboProtegido){
                roboProtegido.setProtegido(); // vai mudar para true
                robos.add(roboProtegido);
    
            }
        }
        return robos;
    }

    public double getRaio(){
        return raio;
    }

}
