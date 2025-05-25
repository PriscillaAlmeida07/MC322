import java.util.ArrayList;

public class SensorReporBlocos extends Sensor {

    // Sensor capaz de descobrir os tapetes de reposição de blocos que estão a um raio ou menos do robô que o contém.

    // Construtor.
    public SensorReporBlocos(double raio, TipoSensor tipo){
        super(raio, tipo);
    }

    // Encontra os tapetes de reposição próximos e guarda-os em uma ArrayList.
    @Override
    public ArrayList<Entidade> monitorar(Ambiente ambiente, int[] posicaoRobo, int caso){

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<Entidade> entidades = ambiente.getArrayEntidades();
        double raio = getRaio();
        ArrayList<Entidade> resultado = new ArrayList<>();

        for (int i = 0; i < entidades.size(); i++){
            if (entidades.get(i) instanceof TapeteReposicao tapete){
                if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - tapete.getX()), 2)) + (Math.pow((posicaoRobo[1] - tapete.getY()), 2))) {
                    resultado.add(tapete);
                }
            }
        }
        return resultado;
    }

    // Imprime os tapetes de reposição encontrados pelo sensor
    @Override
    public void imprimirResultado(ArrayList<Entidade> resultado, int[] posicaoRobo){
        System.out.println("Resultado do sensor de reposição para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + ")");
        for (int i = 0; i < resultado.size(); i++){
            System.out.println("Foi capturado um tapete de reposição na posição (" + resultado.get(i).getX() + "," + resultado.get(i).getY() + ")");
        }
        if (resultado.isEmpty()){
            System.out.println("Nenhum tapete de reposição capturado");
        }
        System.out.print("\n");
    }
}
