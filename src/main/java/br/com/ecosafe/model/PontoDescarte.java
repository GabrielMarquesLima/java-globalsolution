package br.com.ecosafe.model;

public class PontoDescarte {
    private int idPonto;
    private String cepPonto;
    private String logradouroPonto;
    private String ufPonto;
    private String cidadePonto;
    private int numPonto;

    public PontoDescarte() {}

    public PontoDescarte(int idPonto, String cepPonto, String logradouroPonto,
                         String ufPonto, String cidadePonto, int numPonto) {
        this.idPonto = idPonto;
        this.cepPonto = cepPonto;
        this.logradouroPonto = logradouroPonto;
        this.ufPonto = ufPonto;
        this.cidadePonto = cidadePonto;
        this.numPonto = numPonto;
    }

    public int getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }

    public String getCepPonto() {
        return cepPonto;
    }

    public void setCepPonto(String cepPonto) {
        this.cepPonto = cepPonto;
    }

    public String getLogradouroPonto() {
        return logradouroPonto;
    }

    public void setLogradouroPonto(String logradouroPonto) {
        this.logradouroPonto = logradouroPonto;
    }

    public String getUfPonto() {
        return ufPonto;
    }

    public void setUfPonto(String ufPonto) {
        this.ufPonto = ufPonto;
    }

    public String getCidadePonto() {
        return cidadePonto;
    }

    public void setCidadePonto(String cidadePonto) {
        this.cidadePonto = cidadePonto;
    }

    public int getNumPonto() {
        return numPonto;
    }

    public void setNumPonto(int numPonto) {
        this.numPonto = numPonto;
    }
}
