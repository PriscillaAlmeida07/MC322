public class Robo{
    
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String nome){
        this.nome = nome;
        this.posicaoX = 50;
        this.posicaoY = 50;
    }

    public String exibirNome(){
        return nome;
    }


    public int[] mover(int deltaX, int deltaY){
        int[] vetor = new int[2];
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;

        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return vetor;
    }


    public void exibirPosicao(){
        System.out.println("(" + posicaoX + "," + posicaoY + ")");
    }
}


//this nos metodos mover e exibir nao é necessario
//precisamos de um getnome
//podemos printar "o robo 1 esta em 0,0, dgite para qual posicao vc quer mover o robo 1"

//release