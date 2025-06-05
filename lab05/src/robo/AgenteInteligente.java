package robo;

public abstract class AgenteInteligente extends Robo {
    
    protected Missao missao;

    public void definirMissao(Missao missao){
        this.missao = missao;
    }

    public boolean temMissao() {
        return missao != null;
    }

    public abstract void executarMissao(Ambiente ambiente);
}
