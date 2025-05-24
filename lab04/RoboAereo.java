abstract class RoboAereo extends Robo { 

    // Construtor.
    public RoboAereo(String nome, String id){ 
        super(nome, id);
        
    }
    // Movimentação para cima no eixo z.
    public void subir(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de subida inserido");
        } else if (( + deltaZ) > 100){
            System.out.println(deltaZ + " é um valor inválido de voo, pois a  máxima é: " + altitudeMaxima);
        } else { // Movimento válido
             += deltaZ;
            System.out.println(deltaZ + " é um valor válido de voo, ele está na : " + );
        }
    }

    // Movimentação para baixo no eixo z.
    public void descer(int deltaZ){
        if (deltaZ < 0) {
            System.out.println("Valor inválido de descida inserido");
        } else if (( - deltaZ) < 0){
            System.out.println(deltaZ + " é um valor inválido de descida, pois ele passou do chão");
        } else { // Movimento válido
             -= deltaZ;
            System.out.println(deltaZ + " é um valor válido de descida, ele está na : " + );
        }
    }

    public abstract  String getDescricao();
}
