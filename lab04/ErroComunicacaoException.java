public class ErroComunicacaoException extends Exception {

    // Excessão lançada quando um robô tenta se comunicar com ele mesmo.
    public ErroComunicacaoException(String mensagem){
        super(mensagem);
    }
}
