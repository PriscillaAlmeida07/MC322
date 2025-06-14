package exceptions;

public class AgenteInteligenteException extends Exception{
    public AgenteInteligenteException(){
        super("Tentativa de matar um Agente Inteligente"); // Agentes Inteligentes não poderão ser mortos
    }
}
