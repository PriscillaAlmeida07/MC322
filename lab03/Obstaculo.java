public class Obstaculo {
    
    // Posições iniciais dos obstáculos.
    private int posicaoX;
    private int posicaoY;
    private int posicaoZ;

    private TipoObstaculo tipo;

    public Obstaculo(TipoObstaculo tipo, int posicaoX, int posicaoY, int posicaoZ){
        this.tipo = tipo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
    }
}