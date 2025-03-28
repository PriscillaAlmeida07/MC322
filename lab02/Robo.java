public class Robo{

    private String nome;
    private String direcao;
    private int posicaoX;
    private int posicaoY;

    // Construtor.
    public Robo(String nome){ 
        this.nome = nome;
        posicaoX = 50;
        posicaoY = 50; 
    }

    public String getNome(){
        return nome;
    }

    public int[] getPosicao(){
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return vetor;
    }

    public String getDirecao(){
        return direcao;
    }

    public void setDirecao(String direcao){
        this.direcao = direcao;
    }

    public void mover(int deltaX, int deltaY){ 
        posicaoX += deltaX;
        posicaoY += deltaY;
    }


    public void identificarObstaculo(){ // Lembrar de adicionar o tipo dessa função

    }
}
