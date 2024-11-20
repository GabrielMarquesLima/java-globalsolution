package br.com.ecosafe.service;

import br.com.ecosafe.bo.SolicitacaoDescarteBO;
import br.com.ecosafe.model.SolicitacaoDescarte;

import java.sql.SQLException;
import java.util.List;

public class SolicitacaoDescarteService {

    private SolicitacaoDescarteBO bo = new SolicitacaoDescarteBO();

    public List<SolicitacaoDescarte> getAllSolicitacoes() throws SQLException {
        return bo.listarTodas();
    }

    public SolicitacaoDescarte getSolicitacaoById(int id) throws SQLException {
        return bo.obterPorId(id);
    }

    public void createSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        bo.criarSolicitacao(solicitacao);
    }

    public void updateSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        bo.atualizarSolicitacao(solicitacao);
    }

    public void deleteSolicitacao(int id) throws SQLException {
        bo.deletarSolicitacao(id);
    }
}
