package exceptions;

public class MissaoEncerrada extends Exception{
    public MissaoEncerrada(){
        super("A missao ja esta encerrada");
    }
}
