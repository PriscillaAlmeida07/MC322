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
/* 
    public void subir(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de subida inserido");
        } else if ((getZ() + deltaZ) > 100){
            System.out.println(deltaZ + " é um valor inválido de voo, pois a  máxima é: " + altitudeMaxima);
        } else { // Movimento válido
             += deltaZ;
            System.out.println(deltaZ + " é um valor válido de voo, ele está na : " + );
        }
    }

    // Movimentação para baixo no eixo z.
    public void descer(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de descida inserido");
        } else if (( - deltaZ) < 0){
            System.out.println(deltaZ + " é um valor inválido de descida, pois ele passou do chão");
        } else { // Movimento válido
             -= deltaZ;
            System.out.println(deltaZ + " é um valor válido de descida, ele está na : " + );
        }
    }

}
*/