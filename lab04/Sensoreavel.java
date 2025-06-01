
public interface Sensoreavel {
    
    // Interface implementada por todos os robôs.
    void acionarSensores(Ambiente ambiente, int caso) throws RoboDesligadoException, VidaNulaException;

}