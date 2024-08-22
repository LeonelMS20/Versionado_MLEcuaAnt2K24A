package MLBusinessLogic.Entity;

public abstract class MLGenoAlimento extends MLAlimento {

    public MLGenoAlimento(String tipo) {
        super(tipo);
    }

    public String getTipo() {
        return mlTipo;
    }
}
