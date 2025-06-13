package interfaces;

import ambiente.Ambiente;
import exceptions.AreaProtegidaException;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;

public interface Atacante {

    // Interface implementada pelo Robô Obstáculo Aéreo e Robo Cavador.
    int getVida();
    int getDano();
    void atacar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException, AreaProtegidaException;
    
}
