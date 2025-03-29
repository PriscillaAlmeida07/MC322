public class RoboAereo extends Robo { 

    private int altitude;
    private int altitudeMaxima;

    // Construtor.
    public RoboAereo(String nome){ 
        super(nome);
        altitude = 0;
        altitudeMaxima = 100;
    }

    // Obtém a posição (x,y,z) do robô.
    public int[] getPosicao(int posicaoX, int posicaoY){ 
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = altitude;

        return vetor;
    }

    // Define a altitude atual do robô.
    public void setAltitude(int z){
        altitude += z;
    }

    // 
    public int getAltitude(){
        return altitude;
    }

    // Movimentação para cima no eixo z.
    public void subir(int deltaZ){
        if ((altitude + deltaZ) > 100){
            System.out.println(deltaZ + " é um valor inválido de voo, pois a altitude máxima é: " + altitudeMaxima);
        } else {
            altitude += deltaZ;
            System.out.println(deltaZ + " é um valor válido de voo, ele está na altitude: " + altitude);
        }
    }

    // Movimentação para baixo no eixo z.
    public void descer(int deltaZ){
        if ((altitude - deltaZ) < 0){
            System.out.println(deltaZ + " é um valor inválido de descida, pois ele passou do chão");
        } else {
            altitude -= deltaZ;
            System.out.println(deltaZ + " é um valor válido de descida, ele está na altitude: " + altitude);
        }
    }
}
