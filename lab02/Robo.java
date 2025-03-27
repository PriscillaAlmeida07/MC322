public class Robo{ // Atributos.
    
    private String nome;
    private String direcao;
    private int posicaoX;
    private int posicaoY;

    public Robo(String nome){ // Construtor.
        this.nome = nome;
        this.posicaoX = 50;
        this.posicaoY = 50; 
    }

    public String getNome(){
        return nome;
    }

    public void mover(int deltaX, int deltaY){ 
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public int[] getPosicao(){
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return vetor;
    }

    public void identificarObstaculo(){ // Lembrar de adicionar o tipo dessa função

    }
}
