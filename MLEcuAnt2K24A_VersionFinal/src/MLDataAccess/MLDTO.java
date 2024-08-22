package MLDataAccess;

public class MLDTO {
    private String tipoHormiga;
    private String sexo;
    private String genoAlimento;
    private String ingestaNativa;
    private String estado;
    private String ubicacion;

    public MLDTO(String tipoHormiga, String sexo, String genoAlimento, String ingestaNativa, String estado, String ubicacion) {
        this.tipoHormiga = tipoHormiga;
        this.sexo = sexo;
        this.genoAlimento = genoAlimento;
        this.ingestaNativa = ingestaNativa;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public String toCSV() {
        return tipoHormiga + "," + sexo + "," + genoAlimento + "," + ingestaNativa + "," + estado + "," + ubicacion;
    }
}
