public class RoboCavador extends RoboTerrestre {
    
    private int profundidade;
    private int profundidadeMaxima;

    public RoboCavador(String nome){
        super(nome);
        this.profundidadeMaxima = 100;
        this.profundidade = 0;
    }

    public void cavar(int deltaZ){
        if (deltaZ < 0) {
            System.err.println("Valor inválido");
        } else {
            profundidade += deltaZ;
        }
    }
}
