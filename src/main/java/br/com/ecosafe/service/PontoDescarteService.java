package br.com.ecosafe.service;

import br.com.ecosafe.bo.PontoDescarteBO;
import br.com.ecosafe.model.PontoDescarte;

import java.sql.SQLException;
import java.util.List;

public class PontoDescarteService {

    private PontoDescarteBO bo = new PontoDescarteBO();

    public List<PontoDescarte> getAllPontos() throws SQLException {
        return bo.listarTodos();
    }

    public PontoDescarte getPontoById(int id) throws SQLException {
        return bo.obterPorId(id);
    }

    public void createPonto(PontoDescarte ponto) throws SQLException {
        bo.criarPonto(ponto);
    }

    public void updatePonto(PontoDescarte ponto) throws SQLException {
        bo.atualizarPonto(ponto);
    }

    public void deletePonto(int id) throws SQLException {
        bo.deletarPonto(id);
    }

    public List<PontoDescarte> getPontosByUfAndCidade(String uf, String cidade) throws SQLException {
        return bo.getPontosByUfAndCidade(uf, cidade);
    }
}
