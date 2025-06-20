package exceptions;

public class AreaProtegidaException extends Exception {

    // Excessão lançada quando um robô tenta atacar em uma área protegida pelo agente segurança.
    public  AreaProtegidaException(String mensagem){
        super(mensagem);
    }
}
