package br.com.ecosafe.model;

import java.sql.Timestamp;

public class SolicitacaoDescarte {
    private int idSolicitacao;
    private String cnpj;
    private String cpf;
    private String numeroContato;
    private String cepEmpresa;
    private String logradouroEmpresa;
    private String ufEmpresa;
    private String cidadeEmpresa;
    private int numeroEmpresa;
    private String tipoCaminhao;
    private double valorServico;
    private String formaPagamento;
    private Timestamp dtHrBusca;
    private int idPonto;

    public SolicitacaoDescarte() {}

    public SolicitacaoDescarte(int idSolicitacao, String cnpj, String cpf, String numeroContato,
                               String cepEmpresa, String logradouroEmpresa, String ufEmpresa,
                               String cidadeEmpresa, int numeroEmpresa, String tipoCaminhao,
                               double valorServico, String formaPagamento, Timestamp dtHrBusca,
                               int idPonto) {
        this.idSolicitacao = idSolicitacao;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.numeroContato = numeroContato;
        this.cepEmpresa = cepEmpresa;
        this.logradouroEmpresa = logradouroEmpresa;
        this.ufEmpresa = ufEmpresa;
        this.cidadeEmpresa = cidadeEmpresa;
        this.numeroEmpresa = numeroEmpresa;
        this.tipoCaminhao = tipoCaminhao;
        this.valorServico = valorServico;
        this.formaPagamento = formaPagamento;
        this.dtHrBusca = dtHrBusca;
        this.idPonto = idPonto;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getNumeroContato() {
        return numeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        this.numeroContato = numeroContato;
    }

    public String getCepEmpresa() {
        return cepEmpresa;
    }

    public void setCepEmpresa(String cepEmpresa) {
        this.cepEmpresa = cepEmpresa;
    }

    public String getLogradouroEmpresa() {
        return logradouroEmpresa;
    }

    public void setLogradouroEmpresa(String logradouroEmpresa) {
        this.logradouroEmpresa = logradouroEmpresa;
    }

    public String getUfEmpresa() {
        return ufEmpresa;
    }

    public void setUfEmpresa(String ufEmpresa) {
        this.ufEmpresa = ufEmpresa;
    }

    public String getCidadeEmpresa() {
        return cidadeEmpresa;
    }

    public void setCidadeEmpresa(String cidadeEmpresa) {
        this.cidadeEmpresa = cidadeEmpresa;
    }

    public int getNumeroEmpresa() {
        return numeroEmpresa;
    }

    public void setNumeroEmpresa(int numeroEmpresa) {
        this.numeroEmpresa = numeroEmpresa;
    }

    public String getTipoCaminhao() {
        return tipoCaminhao;
    }

    public void setTipoCaminhao(String tipoCaminhao) {
        this.tipoCaminhao = tipoCaminhao;
    }

    public double getValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Timestamp getDtHrBusca() {
        return dtHrBusca;
    }

    public void setDtHrBusca(Timestamp dtHrBusca) {
        this.dtHrBusca = dtHrBusca;
    }

    public int getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }
}
