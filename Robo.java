public class Robo{
    
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String nome){
        this.nome = nome;
        this.posicaoX = 0;
        this.posicaoY = 0;
    }

    public void mover(int deltaX, int deltaY){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }

    public void exibirPosicao(){
        System.out.println(this.posicaoX);
        System.out.println(this.posicaoY);
    }
}