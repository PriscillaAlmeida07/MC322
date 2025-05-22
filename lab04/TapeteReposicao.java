public class TapeteReposicao implements Entidade{

    // Local do ambiente em que os robôs obstáculo terrestre podem repor os seus blocos.
    private final int posicaoX;
    private final int posicaoY;

    // Construtor.
    public TapeteReposicao(int posicaoX, int posicaoY){
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    // Obtém a posição do tapete.
    public int[] getPosicao(){
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return  vetor;
    }
    
}
