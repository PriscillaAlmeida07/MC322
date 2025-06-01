public class VelocidadeMaxException extends Exception {

    // Excessão lançada quando um robô passou da velocidade máxima permitida.
    public VelocidadeMaxException(){
        super("O robô atingiu o limite de velocidade permitida");
    }
}
