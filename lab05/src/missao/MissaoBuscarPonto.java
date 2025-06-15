package missao;
import ambiente.Ambiente;
import arquivos.Arquivo;
import exceptions.ColisaoException;
import exceptions.ForaDosLimitesException;
import robo.AgenteInteligente;

public class MissaoBuscarPonto implements Missao {
    
    // Missão capaz de encontrar um caminho para levar os Agentes a um ponto específico do mapa

    @Override
    // Ainda tem que dar um jeito de escolher o ponto final que ele quer ir
    public void executar(AgenteInteligente robo, Ambiente ambiente, Arquivo arquivo){
        int Xfinal = 30; int Yfinal = 30;
        int deltaX = Xfinal - robo.getX();
        int deltaY = Yfinal - robo.getY();
        // Vamos tratar aqui
        try {
            robo.getControleMovimento().movimentarAgente(robo, ambiente, deltaX, deltaY, 0);
        }catch (ForaDosLimitesException | ColisaoException e){
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
