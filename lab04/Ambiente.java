import java.util.ArrayList;

public class Ambiente { 

    private final int largura, comprimento, altura;
    private TipoEntidade mapa[][][];
    private ArrayList<Entidade> entidades;

    // Construtor.
    public Ambiente(){ 
        largura = 50;
        comprimento = 50;
        altura = 50;
        entidades = new ArrayList<>();
        mapa = new TipoEntidade[largura][comprimento][altura];
        inicializarMapa();
    }

    public void inicializarMapa(){
        for(int x=0; x< largura; x++){
            for(int y=0; y< comprimento; y++){
                for (int z=0; z< altura; z++){
                    mapa[x][y][z] = TipoEntidade.VAZIO;
                } 
            }
        }
    }

    // Adiciona a entidade no array de entidades e no mapa
    public void adicionarEntidade(Entidade entidade){
        entidades.add(entidade);
        if(entidade instanceof Obstaculo obstaculo)
        {
            int larguraObst = obstaculo.getTipoObstaculo().getLargura();
            int comprimentoObst = obstaculo.getTipoObstaculo().getComprimento();
            int alturaObst = obstaculo.getTipoObstaculo().getAltura();

            for(int x=0; x< larguraObst; x++){
                for(int y=0; y< comprimentoObst; y++){
                    for (int z=0; z< alturaObst; z++){
                        mapa[entidade.getX() + x][entidade.getY() + y][entidade.getZ() + z]= TipoEntidade.OBSTACULO;
                    } 
                }
            }
        }
        else{
            mapa[entidade.getX()][entidade.getY()][entidade.getZ()]= entidade.getTipo();
        }
       
    }

    // Remove a entidade do array de entidades e do mapa
    public void removerEntidade(Entidade entidade){
        entidades.remove(entidade);
        if(entidade instanceof Obstaculo obstaculo)
        {
            int larguraObst = obstaculo.getTipoObstaculo().getLargura();
            int comprimentoObst = obstaculo.getTipoObstaculo().getComprimento();
            int alturaObst = obstaculo.getTipoObstaculo().getAltura();

            for(int x=0; x< larguraObst; x++){
                for(int y=0; y< comprimentoObst; y++){
                    for (int z=0; z< alturaObst; z++){
                        mapa[entidade.getX() + x][entidade.getY() + y][entidade.getZ() + z]= TipoEntidade.VAZIO;
                    } 
                }
            }
        }
        else
            mapa[entidade.getX()][entidade.getY()][entidade.getZ()]= TipoEntidade.VAZIO;
    }
    // Imprime o mapa e em cada posição exibe o caractere associado a cada entidade.
    // Só conseguimos visualizar recortes, o chão no caso
    public void visualizarAmbiente(){
        for(int x=0; x< largura; x++){
            for(int y=0; y < comprimento ; y++){
                TipoEntidade tipo = mapa[x][y][25];                
                System.out.print( "|" + tipo.getCaractere());
        
            }
            System.out.print( "|");
            System.out.print("\n");
        }
    }

    // Quando alguma entidade se movimentar, também refletiremos essa alteração de posição no mapa
    public void moverEntidade(Entidade entidade, int[] posicaoAnterior){
        // Registramos a posição anterior como vazio no mapa
        mapa[posicaoAnterior[0]][posicaoAnterior[1]][posicaoAnterior[2]] = TipoEntidade.VAZIO;
        // Registramos a nova posição da entidade no mapa
        mapa[entidade.getX()][entidade.getY()][entidade.getZ()]= entidade.getTipo();
    }

    // Confere se um robô está dentro dos limites do ambiente.
    public void dentroDosLimites(int x, int y, int z, String mensagem) throws ForaDosLimitesException {
        if ((x < 0 || x >= largura || y < 0 || y >= comprimento || z < 0 || z >= altura))
            // Se não estiver, geramos uma exceção que será tratada posteriormente
            throw new ForaDosLimitesException(mensagem);
    }

    // Confere se não ha nenhuma entidade na posição
    public void verificarColisoes(int x, int y, int z, String mensagem) throws ColisaoException {
        if(mapa[x][y][z] != TipoEntidade.VAZIO){
            // Se estiver ocupado, geramos uma exceção que será tratada posteriormente
            throw new ColisaoException(mensagem);
        } 
    }

    //Nao estamos usando
    public void executarSensores() throws RoboDesligadoException{
        for (int i = 0; i < entidades.size(); i++) {
            if (entidades.get(i) instanceof Robo robo) {
                if (robo instanceof RoboObstaculoTerrestre roboObsTer){
                    roboObsTer.acionarSensores(this, 1);
                } else {
                    robo.acionarSensores(this, 2);
                }
                
            }
        }
    }

    public ArrayList<Entidade> getArrayEntidades(){
        return entidades;
    }

    public Robo getDestinatario(String id){
        for (int i=0; i< entidades.size(); i++){
            if (entidades.get(i) instanceof Robo robo){
                if (robo.getID().equals(id)){
                    return robo;
                }
            }
        }
        return null;
    }

    // nao sei se está sendo usado
    public int existeTapete(int posicaoX, int posicaoY, int posicaoZ){
        if((mapa[posicaoX][posicaoY][posicaoZ] == TipoEntidade.TAPETE))
            return 1;
        else 
            return 0;
    }

    public ArrayList<Robo> getArrayRobos(){
        ArrayList<Robo> robos = new ArrayList<>(); 
        for (int i = 0; i < entidades.size(); i++){
            if (entidades.get(i) instanceof Robo robo){
                robos.add(robo);
            }
        }
        return robos;
    }

    public void listarDescricoesRobos(){
        ArrayList<Robo> robos = getArrayRobos();
        System.out.println("Segue descrição de cada um dos robôs que estão no ambiente:");
        for (int i = 0; i < robos.size(); i++){
            System.out.println(robos.get(i).getNome() + ": " + robos.get(i).getDescricao());
        }
        System.out.print("\n");
    }

    public void listarEstadosRobos(){
        ArrayList<Robo> robos = getArrayRobos();
        System.out.println("O estado atual de cada um dos robôs é:");
        for (int i = 0; i < robos.size(); i++){
            System.out.println(robos.get(i).getNome() + ": " + robos.get(i).getEstadoRobo().getString());
        }
        System.out.print("\n");
    }

    public void listarVidaRobos(){
        ArrayList<Robo> robos = getArrayRobos();
        System.out.println("A vida atual de cada um dos robôs é:");
        for (int i = 0; i < robos.size(); i++){
            System.out.println(robos.get(i).getNome() + ": " + robos.get(i).getVida() + "/10");
        }
        System.out.print("\n");
    }

    public void listarPosicaoRobos(){
        ArrayList<Robo> robos = getArrayRobos();
        System.out.println("A posição atual de cada um dos robôs é:");
        for (int i = 0; i < robos.size(); i++){
            System.out.println(robos.get(i).getNome() + ": (" + robos.get(i).getX() + "," + robos.get(i).getY() + "," + robos.get(i).getZUsuario() + ")");
        }
        System.out.print("\n");
    }

    public void listarObjetos(){
        for (int i = 0; i < entidades.size(); i++){
            if (entidades.get(i) instanceof Obstaculo obstaculo){
                System.out.print(obstaculo.getTipoObstaculo().getNome() + ": " + "("+ obstaculo.getX() + "," + obstaculo.getY() + "," + obstaculo.getZ() + ")\n");
            }
            if (entidades.get(i) instanceof TapeteReposicao tapete){
                System.out.print("Tapete: " + "("+ tapete.getX() + "," + tapete.getY() + "," + tapete.getZ() + ")\n");
            }
        }
        System.out.print("\n");
    }
}
