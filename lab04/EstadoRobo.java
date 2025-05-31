public enum EstadoRobo {
    
    // Indica o estado atual de um robô.
    LIGADO("ligado"), 
    DESLIGADO("desligado");
    
    private final String string;

    private EstadoRobo(String string) {
        this.string = string;
    }

    public String getString(){
        return string;
    }
}
