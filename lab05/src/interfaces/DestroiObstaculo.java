package interfaces;

import ambiente.Ambiente;
import exceptions.RoboDesligadoException;
import exceptions.VidaNulaException;

public interface DestroiObstaculo {

    // Interface implementada pelo robô cavador.
    void getObstaculoMaisProx(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException;

}
