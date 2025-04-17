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
    public int[] getPosicao(){ 
        int[] vetor1 = new int[2];
        int[] vetor2 = new int[3];
        vetor1 = super.getPosicao();
        vetor2[0] = vetor1[0];
        vetor2[1] = vetor1[1];
        vetor2[2] = -profundidade;

        return vetor2;
    }

    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if ((deltaZ + profundidade) > profundidadeMaxima){
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else { // Valor válido de perfuração
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração");
        }
    }

    //Redefine a profundidade, pois ele nao vai conseguir perfurar pois está em uma posicao invalida
    public void setProfundidade(int deltaZ){
        profundidade -= deltaZ;
    }
}
