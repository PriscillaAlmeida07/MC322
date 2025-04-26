public class TapeteReposicao {

    private int posicaoX;
    private int posicaoY;

    public TapeteReposicao(int posicaoX, int posicaoY) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public int[] getPosicao() {
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        return  vetor;
    }
    
}
