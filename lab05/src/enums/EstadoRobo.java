package enums;

public enum EstadoRobo {
    
    // Indica o estado atual de um robô.
    LIGADO("ligado"), 
    DESLIGADO("desligado");
    
    private final String string;

    // Construtor.
    private EstadoRobo(String string) {
        this.string = string;
    }

    // Obtém a string que representa o estado atual do robô (para impressão).
    public String getString(){
        return string;
    }
}
