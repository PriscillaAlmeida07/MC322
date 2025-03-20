public class Ambiente{ // Atributos.

    private int largura;
    private int altura;

    public Ambiente() { // Construtor.
        largura = 100;
        altura = 100;
    }
    
    public int dentroDosLimites(int x, int y) {
        if ((largura >= x && x >= 0) && (altura >= y && y >= 0))
            return 1;
        else // Não está dentro dos limites.
            return 0;
    }
}
