public class RoboAereo extends Robo {
    
    private int altitude;
    private int altitudeMaxima;

    public RoboAereo(String nome){
        super(nome);
        this.altitudeMaxima = 100;
        this.altitude = 0;
    }

    public int[] getPosicao(int posicaoX, int posicaoY){ 
        int[] vetor = new int[3];
        vetor[0] = posicaoX;
        vetor[1] = posicaoY;
        vetor[2] = altitude;

        return vetor;
    }

    public void subir(int deltaZ){
        if ((deltaZ + altitude) > 100){
            System.out.println(deltaZ + " é um valor inválido de voo, pois a altitude máxima é: " + altitudeMaxima);
        } else {
            altitude += deltaZ;
            System.out.println(deltaZ + " é um valor válido de voo, ele está na altitude: " + altitude);
        }
    }

    public void descer(int deltaZ){
        if ((deltaZ - altitude) < 0){
            System.out.println(deltaZ + " é um valor inválido de descida, pois ele passou do chão");
        } else {
            altitude -= deltaZ;
            System.out.println(deltaZ + " é um valor válido de descida, ele está na altitude: " + altitude);
        }
    }


}
