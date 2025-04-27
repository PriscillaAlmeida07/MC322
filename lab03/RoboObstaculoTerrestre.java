public class RoboObstaculoTerrestre extends RoboTerrestre {
    
    // Robô capaz de criar obstáculos posicionando blocos no solo.
    private int numBlocos;

    // Construtor.
    public RoboObstaculoTerrestre(String nome){
        super(nome);
        numBlocos = 1;
        adicionaSensorReporBlocos(new SensorReporBlocos());
    }

    // Posiciona um bloco em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarBlocos(int posicaoX, int posicaoY){
        if ((numBlocos == 0) || ((posicaoX > 96) || (posicaoY > 96))){
            System.out.print("Não há mais blocos disponíveis e/ou posição inválida para adicionar um bloco ao ambiente");
            return null;
        } else { // Ainda tem blocos e a posição é válida.
            Obstaculo bloco = criarBloco(posicaoX, posicaoY);
            numBlocos--;
            return bloco;
        }
    }

    // Cria um novo bloco na posição.
    private Obstaculo criarBloco(int posicaoX, int posicaoY){
        Obstaculo bloco = new Obstaculo(TipoObstaculo.BLOCO, posicaoX, posicaoY, 0);
        return bloco;
    }
}
