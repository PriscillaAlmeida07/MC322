package interfaces;

import ambiente.Ambiente;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;

public interface Sensoreavel {
    
    // Interface implementada por todos os robôs.
    void acionarSensores(Ambiente ambiente, int caso) throws RoboDesligadoException, VidaNulaException;

}