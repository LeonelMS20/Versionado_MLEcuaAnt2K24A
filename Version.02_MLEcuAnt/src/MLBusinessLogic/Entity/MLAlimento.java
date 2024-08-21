package MLBusinessLogic.Entity;

public abstract class MLAlimento {
    protected String mlTipo;

    public MLAlimento(String tipo) {
        this.mlTipo = tipo;
    }

    @Override
    public String toString() {
        if(mlTipo == null)
            mlTipo = "";
        return mlTipo.toUpperCase();
    }
}
