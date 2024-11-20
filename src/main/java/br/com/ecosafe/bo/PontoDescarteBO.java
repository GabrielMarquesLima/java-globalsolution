package br.com.ecosafe.bo;

import br.com.ecosafe.dao.PontoDescarteDAO;
import br.com.ecosafe.model.PontoDescarte;

import java.sql.SQLException;
import java.util.List;

public class PontoDescarteBO {

    private PontoDescarteDAO dao = new PontoDescarteDAO();

    public List<PontoDescarte> listarTodos() throws SQLException {
        return dao.getAllPontos();
    }

    public PontoDescarte obterPorId(int id) throws SQLException {
        PontoDescarte ponto = dao.getPontoById(id);
        if (ponto == null) {
            throw new IllegalArgumentException("Ponto de descarte não encontrado.");
        }
        return ponto;
    }

    public void criarPonto(PontoDescarte ponto) throws SQLException {
        if (existeEndereco(ponto)) {
            throw new IllegalArgumentException("Já existe um ponto de descarte com este endereço.");
        }
        dao.createPonto(ponto);
        // O ID é definido no objeto dentro do DAO após a inserção
    }

    public void atualizarPonto(PontoDescarte ponto) throws SQLException {
        if (dao.getPontoById(ponto.getIdPonto()) == null) {
            throw new IllegalArgumentException("Ponto de descarte não encontrado para atualização.");
        }
        if (existeEndereco(ponto)) {
            throw new IllegalArgumentException("Já existe um ponto de descarte com este endereço.");
        }

        dao.updatePonto(ponto);
    }

    public void deletarPonto(int id) throws SQLException {
        if (dao.getPontoById(id) == null) {
            throw new IllegalArgumentException("Ponto de descarte não encontrado para exclusão.");
        }
        dao.deletePonto(id);
    }

    private boolean existeEndereco(PontoDescarte ponto) throws SQLException {
        List<PontoDescarte> pontos = dao.getAllPontos();
        for (PontoDescarte p : pontos) {
            if (p.getCepPonto().equals(ponto.getCepPonto()) &&
                    p.getLogradouroPonto().equalsIgnoreCase(ponto.getLogradouroPonto()) &&
                    p.getNumPonto() == ponto.getNumPonto() &&
                    p.getCidadePonto().equalsIgnoreCase(ponto.getCidadePonto())) {
                return true;
            }
        }
        return false;
    }

    public List<PontoDescarte> getPontosByUfAndCidade(String uf, String cidade) throws SQLException {
        return dao.getPontosByUfAndCidade(uf, cidade);
    }
}
