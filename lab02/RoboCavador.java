public class RoboCavador extends RoboTerrestre { 

    // Robô terrestre que consegue perfurar o solo.
    private int profundidade;
    private int profundidadeMaxima;

    // Construtor.
    public RoboCavador(String nome){ 
        super(nome);
        profundidade = 0;
        profundidadeMaxima = 100;
    }

    // Obtém a profundidade do robô.
    public int getProfundidade(){
        return profundidade;
    }

    // Obtém a posição (x,y,z) do robô.
    public int[] getPosicao(int posicaoX, int posicaoY){ 
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = -profundidade;

        return vetor;
    }

    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if ((deltaZ + profundidade) > profundidadeMaxima){
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else { // Valor válido de perfuração
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração, logo ele perfurou o solo");
        }
    }

    //Redefine a profundidade, pois ele nao vai conseguir perfurar pois está em uma posicao invalida
    public void setProfundidade(int deltaZ){
        profundidade -= deltaZ;
    }
}
