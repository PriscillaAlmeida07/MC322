import java.util.ArrayList;

public class SensorReporBlocos extends Sensor {

    // Sensor capaz de descobrir os tapetes de reposição de blocos que estão a um raio ou menos do robô que o contém.

    // Construtor.
    public SensorReporBlocos() {
        super();
    }

    // Encontra e imprime os tapetes de reposição próximos.
    @Override
    public void monitorar(Ambiente ambiente, int[] posicaoRobo) {

        // Obtendo as variáveis necessárias para o sensor
        ArrayList<TapeteReposicao> tapetes = ambiente.getArrayTapetes();
        int[] posicaoTapete;
        double raio = getRaio();
        int capturado = 0;

        System.out.println("Resultado do sensor de tapetes de reposição para o robô na posição: (" + posicaoRobo[0] + "," + posicaoRobo[1] + ")");

        for (int i = 0; i < tapetes.size(); i++) {
            posicaoTapete = tapetes.get(i).getPosicao();
            if (Math.pow(raio, 2) >= (Math.pow((posicaoRobo[0] - posicaoTapete[0]), 2)) + (Math.pow((posicaoRobo[1] - posicaoTapete[1]), 2))) {
                System.out.println("Foi capturado um tapete de reposição na posição (" + posicaoTapete[0] + "," + posicaoTapete[1] + ")");
                capturado++;
            }
        }
        if (capturado == 0) {
            System.out.println("Nenhum tapete de reposição capturado");
        }
        System.out.print("\n");
    }
}
