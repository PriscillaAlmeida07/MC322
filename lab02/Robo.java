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

    public int[] mover(int deltaX, int deltaY){ // Retornar a posicao X e Y em um vetor.
        int[] vetor = new int[2];
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;

        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return vetor;
    }

    public void getPosicao(){
        System.out.println("(" + posicaoX + "," + posicaoY + ")"); // RETORNAR AO INVÉS DE PRINTAR
    }

    public void identificarObstaculo(){ // Lembrar de adicionar o tipo dessa função

    }
}
