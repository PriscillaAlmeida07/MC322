public class TapeteReposicao implements Entidade {

    private final int posicaoX;
    private final int posicaoY;
    private final int posicaoZ;

    // Construtor.
    public TapeteReposicao(int posicaoX, int posicaoY){
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = 25; // Todos os tapetes ficam no chão
    }

    // Obtém posições do tapete:

    @Override
    public int getX(){return posicaoX;}
    @Override
    public int getY(){return posicaoY;}
    @Override
    public int getZ(){return posicaoZ;} 

    // Obtém informações sobre o tapete:
    @Override
    public TipoEntidade getTipo(){return TipoEntidade.TAPETE;}
    @Override
    public String getDescricao(){return "Tapete de reposição: local do ambiente em que os robôs obstáculo terrestre podem repor os seus blocos";}
    @Override
    public char getRepresentacao(){return 'T';}
    
}
