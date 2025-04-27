public class RoboAereo extends Robo { 

    private int altitude;
    private final int altitudeMaxima;

    // Construtor.
    public RoboAereo(String nome){ 
        super(nome);
        altitude = 0;
        altitudeMaxima = 100;
    }

    // Obtém a altitude do robô.
    public int getAltitude(){
        return altitude;
    }
    
    // Define a altitude do robô.
    public void setAltitude(int deltaZ){
        altitude -= deltaZ;
    }

    // Obtém a posição (x,y,z) do robô.
    @Override
    public int[] getPosicao(){ 
        int[] vetor1 = super.getPosicao();

        int[] vetor2 = new int[3];
        vetor2[0] = vetor1[0];
        vetor2[1] = vetor1[1];
        vetor2[2] = altitude;

        return vetor2;
    }

    // Movimentação para cima no eixo z.
    public void subir(int deltaZ){
        if ((altitude + deltaZ) > 100){
            System.out.println(deltaZ + " é um valor inválido de voo, pois a altitude máxima é: " + altitudeMaxima);
        } else { // Movimento válido
            altitude += deltaZ;
            System.out.println(deltaZ + " é um valor válido de voo, ele está na altitude: " + altitude);
        }
    }

    // Movimentação para baixo no eixo z.
    public void descer(int deltaZ){
        if ((altitude - deltaZ) < 0){
            System.out.println(deltaZ + " é um valor inválido de descida, pois ele passou do chão");
        } else { // Movimento válido
            altitude -= deltaZ;
            System.out.println(deltaZ + " é um valor válido de descida, ele está na altitude: " + altitude);
        }
    }
}
