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
    public int[] getPosicao(){ 
        int[] vetor1 = new int[2];
        int[] vetor2 = new int[3];
        vetor1 = super.getPosicao();
        vetor2[0] = vetor1[0];
        vetor2[1] = vetor1[1];
        vetor2[2] = altitude;

        return vetor2;
    }

    // Define a altitude atual do robô.
    public void setAltitude(int z){
        altitude -= z;
    }

    // Obtém a altitude do robô.
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
