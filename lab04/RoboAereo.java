abstract class RoboAereo extends Robo { 

    // Robôs que podem realizar subidas no eixo Z

    // Construtor.
    public RoboAereo(String nome, String id, EstadoRobo estado, int posicaoX, int posicaoY, int posicaoZ){ 
        super(nome, id, estado, posicaoX, posicaoY, posicaoZ);
    }

    
    // Movimentação para cima no eixo z.
    public void subir(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException{
        moverPara(deltaX, deltaY, deltaZ, ambiente);
    }

    public void descer(Ambiente ambiente, int deltaX, int deltaY, int deltaZ) throws ForaDosLimitesException, ColisaoException{
        moverPara(deltaX, deltaY, -deltaZ, ambiente);
    }

    
}
