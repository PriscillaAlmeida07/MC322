public class ForaDosLimitesException extends Exception {

    // Excessão lançada quando um robô tenta ir para fora do mapa definido.
    public ForaDosLimitesException(String mensagem){
        super(mensagem);
    }
}
