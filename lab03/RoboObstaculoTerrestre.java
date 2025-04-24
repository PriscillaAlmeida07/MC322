public class RoboObstaculoTerrestre extends RoboTerrestre {
    
    // Robô capaz de criar obstáculos posicionando blocos no solo.
    private int numBlocos;

    // Construtor.
    public RoboObstaculoTerrestre(String nome){
        super(nome);
        adicionaSensorReporBlocos(new SensorReporBlocos());
        numBlocos = 1;
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarBlocos(int posicaoX, int posicaoY){

        if ((numBlocos == 0) && ((posicaoX < 97) && (posicaoY < 97))){
            System.out.println("Não há mais blocos disponíveis\n");
            return null;
        } else{ // Ainda tem blocos.
            Obstaculo bloco = criarBloco(posicaoX, posicaoY);
            numBlocos--;
            return bloco;
        }
    }

    // Criamos um novo bloco na posição  
    private Obstaculo criarBloco(int posicaoX, int posicaoY){

        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, 0);
        return bloco;
    }
}
