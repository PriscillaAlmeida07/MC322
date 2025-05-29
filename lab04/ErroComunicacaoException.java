public class ErroComunicacaoException extends Exception {
    public ErroComunicacaoException(){
        super("O robô atingiu o limite de velocidade permitida");
    }
}
