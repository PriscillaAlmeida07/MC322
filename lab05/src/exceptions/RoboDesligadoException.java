package exceptions;

public class RoboDesligadoException extends Exception{

    // Excessão lançada quando um robô tenta agir/interagir estando desligado.
    public RoboDesligadoException(String mensagem){
        super(mensagem);
    }
}
