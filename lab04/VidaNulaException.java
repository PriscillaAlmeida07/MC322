public class VidaNulaException extends Exception {

    // Excessão lançada quando um robô está morto.
    public VidaNulaException(String mensagem){
        super(mensagem);
    }
}
