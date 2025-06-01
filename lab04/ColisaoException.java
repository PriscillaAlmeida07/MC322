public class ColisaoException extends Exception{

    // Excessão lançada quando um robô colide com um obstáculo ou com outro robô.
    public ColisaoException(String mensagem){
        super(mensagem);
    }
}
