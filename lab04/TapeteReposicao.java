public class TapeteReposicao implements Entidade {

    private final int posicaoX;
    private final int posicaoY;

    // Construtor.
    public TapeteReposicao(int posicaoX, int posicaoY){
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    // Obtém posições do tapete:

    @Override
    public int getX(){return posicaoX;}
    @Override
    public int getY(){return posicaoY;}
    @Override
    public int getZ(){return 0;} // Todos os tapetes ficam no chão

    // Obtém informações sobre o tapete:

    @Override
    public TipoEntidade getTipo(){return TipoEntidade.TAPETE;}
    @Override
    public String getDescricao(){return "Local do ambiente em que os robôs obstáculo terrestre podem repor os seus blocos";}
    @Override
    public char getRepresentacao(){return 'T';}
    
}
