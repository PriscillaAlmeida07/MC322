public class RoboObstaculoAereo extends RoboAereo {
        
    // Robô capaz de criar obstáculos posicionando nuvens no céu.
    private int numNuvens;

    // Construtor.
    public RoboObstaculoAereo(String nome){
        super(nome);
        numNuvens = 3;
    }

    // Posiciona uma nuvem em sua posição, criando um obstáculo para outros robôs. Adicionaremos ao ambiente na main
    public Obstaculo soltarNuvens(int posicaoX, int posicaoY, int posicaoZ){
        if ((numNuvens == 0) || ((posicaoX > 96) || (posicaoY > 96) || (posicaoZ > 99))){
            System.out.print("Não há mais nuvens disponíveis e/ou posição inválida para adicionar uma nuvem ao ambiente");
            return null;
        } else { // Ainda tem nuvens e a posição é válida.
            Obstaculo nuvem = criarNuvem(posicaoX, posicaoY, posicaoZ);
            numNuvens--;
            return nuvem;
        }
    }

    // Cria uma nova nuvem na posição.
    private Obstaculo criarNuvem(int posicaoX, int posicaoY, int posicaoZ){
        Obstaculo nuvem = new Obstaculo(TipoObstaculo.NUVEM, posicaoX, posicaoY, posicaoZ);
        return nuvem;
    }

    // Conserta a altitude do robô caso ele tenha tentado ir para uma posição inadequada.
    public void setAltitude(int deltaZ, int caso){
        if (caso == 1){ // Ele tentou subir, mas descerá para retornar a posição anterior
            super.setAltitude(deltaZ);

        } else { // (caso == 2) Ele tentou descer, mas subirá para retornar a posição anterior
            super.setAltitude(-deltaZ);
        }
    }
}

