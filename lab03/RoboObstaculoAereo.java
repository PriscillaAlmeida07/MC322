public class RoboObstaculoAereo extends RoboAereo {
        
    // Robô capaz de criar obstáculos posicionando nuvens no céu.
    private int numNuvens;

    // Construtor.
    public RoboObstaculoAereo(String nome){
        super(nome);
        numNuvens = 1;
    }

    // Posiciona uma nuvem em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarNuvens(int posicaoX, int posicaoY, int posicaoZ){
        if ((numNuvens == 0)){
            System.out.println("Não há mais nuvens disponíveis\n");
            return null;
        } else { // Ainda tem nuvens.
            Obstaculo nuvem = criarNuvem(posicaoX, posicaoY, posicaoZ);
            numNuvens--;
            return nuvem;
        }
    }

    // Criamos uma nova nuvem na posição  
    private Obstaculo criarNuvem(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo nuvem = new Obstaculo(TipoObstaculo.NUVEM, posicaoX, posicaoY, posicaoZ);
        return nuvem;
    }
}

