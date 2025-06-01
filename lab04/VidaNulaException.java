public class VidaNulaException extends Exception {

    // Excessão lançada quando um robô está morto.
    public VidaNulaException(){
        super("O robô está morto, portanto só poderá realizar ações quando for curado por outro robô");
    }
}
