public class VidaNulaException extends Exception {

    public VidaNulaException(){
        super("O robô está morto, ele só poderá realizar ações quando for curado por outro robô");
    }

}
