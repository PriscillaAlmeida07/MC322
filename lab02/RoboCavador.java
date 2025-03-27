public class RoboCavador extends RoboTerrestre {
    
    // Robo terrestre oposto ao robo aereo simples
    private int profundidade;
    private int profundidadeMaxima;

    // Construtor
    public RoboCavador(String nome){
        super(nome);
        this.profundidadeMaxima = 100;
        this.profundidade = 0;
    }

    // Método que permite a movimentação abaixo do solo
    public void cavar(int deltaZ){
        if (deltaZ < 0) {
            System.err.println("Valor inválido");
        } else {
            profundidade += deltaZ;
        }
    }
}
