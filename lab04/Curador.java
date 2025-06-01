public interface Curador {

    // Interface implementada pelo Robô Flutuador e Robô Obstáculo Terrestre.
    int getVida();
    int getReparo();
    void curar(Ambiente ambiente) throws RoboDesligadoException, VidaNulaException;

}
