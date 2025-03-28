public class RoboCavador extends RoboTerrestre { 

    // Robo terrestre que consegue perfurar o solo.
    private int profundidade;
    private int profundidadeMaxima;

    // Construtor.
    public RoboCavador(String nome){ 
        super(nome);
        profundidadeMaxima = 100;
        profundidade = 0;
    }

    // Método que permite a movimentação abaixo do solo.
    public void cavar(int deltaZ){
        if ((deltaZ + profundidade) > profundidadeMaxima) {
            System.out.println(deltaZ + " é um valor inválido de perfuração, pois a profundidade máxima é: " + profundidadeMaxima);
        } else {
            profundidade += deltaZ;
            System.out.println(deltaZ + " é um valor válido de perfuração, ele está na profundidadde: " + profundidade);
        }
    }
}
