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

    // Obtém o nome do robô.
    public String getNome(){
        return nome;
    }

    // Obtém a direção do robô.
    public String getDirecao(){
        return direcao;
    }
        
    // Descobre e define a direção do robô.
    public void setDirecao(int deltaX, int deltaY){
        if (deltaX > 0){
            if (deltaY > 0){
                direcao = "Nordeste";
            } else if (deltaY == 0){
                direcao = "Leste";
            } else { // deltaY < 0
                direcao = "Sudeste";
            }

        } else if (deltaX == 0){
            if (deltaY > 0){
                direcao = "Norte";
            } else { // deltaY < 0
                direcao = "Sul";
            }
            
        } else if (deltaX < 0){
            if (deltaY > 0){
                direcao = "Noroeste";
            } else if (deltaY == 0){
                direcao = "Oeste";
            } else { // deltaY < 0
                direcao = "Sudoeste";
            }
        }
    }

    // Obtém a posição (x,y) do robô.
    public int[] getPosicao(){
        int[] vetor = new int[2];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;

        return vetor;
    }

    // Realiza um movimento no plano XY.
    public void mover(int deltaX, int deltaY){ 
        posicaoX += deltaX;
        posicaoY += deltaY;
    }
}
