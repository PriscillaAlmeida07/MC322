public interface Entidade {

    // Interface implementada por robôs, obstáculos e tapetes de reposição.
    int getX();
    int getY();
    int getZ();
    
    TipoEntidade getTipo();
    String getDescricao();
    char getRepresentacao();

}
