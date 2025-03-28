public class RoboCavador extends RoboTerrestre { 

    // Robô terrestre que consegue perfurar o solo.
    private int profundidade;
    private int profundidadeMaxima;

    // Construtor.
    public RoboCavador(String nome){ 
        super(nome);
        profundidade = 0;
        profundidadeMaxima = 100;
    }

    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if ((deltaZ + profundidade) > profundidadeMaxima){
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else { // Valor válido de perfuração
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração, logo ele perfurou o solo");
        }
    }
}
