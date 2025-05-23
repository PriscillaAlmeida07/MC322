public interface Atacante {
    void atacar(Ambiente ambiente); // Diminui a vida de um robo proximo detectado pelo sensor
    int getVida();
    int getDano();
}
