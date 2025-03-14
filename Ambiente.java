public class Ambiente{

    private int largura;
    private int altura;

    public Ambiente() {
        largura = 100;
        altura = 100;
    }
    
    public int dentroDosLimites(int x, int y) {
        if ((largura >= x && x >= 0) && (altura >= y && y >= 0))
            return 1;
        else
            return 0;
    }
}
//podemos usar o booleano    
// public static boolean dentroDosLimites(int x, int y) {
 //       return (largura >= x && x >= 0) && (altura >= y && y >= 0);
 // }