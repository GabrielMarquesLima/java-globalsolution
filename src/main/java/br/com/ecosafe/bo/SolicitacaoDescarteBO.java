package br.com.ecosafe.bo;

import br.com.ecosafe.dao.PontoDescarteDAO;
import br.com.ecosafe.dao.SolicitacaoDescarteDAO;
import br.com.ecosafe.model.SolicitacaoDescarte;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class SolicitacaoDescarteBO {

    private SolicitacaoDescarteDAO dao = new SolicitacaoDescarteDAO();
    private PontoDescarteDAO pontoDao = new PontoDescarteDAO();

    public List<SolicitacaoDescarte> listarTodas() throws SQLException {
        return dao.getAllSolicitacoes();
    }

    public SolicitacaoDescarte obterPorId(int id) throws SQLException {
        SolicitacaoDescarte solicitacao = dao.getSolicitacaoById(id);
        if (solicitacao == null) {
            throw new IllegalArgumentException("Solicitação de descarte não encontrada.");
        }
        return solicitacao;
    }

    public void criarSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        validarCpfOuCnpj(solicitacao);
        validarDataHoraBusca(solicitacao);
        if (pontoDao.getPontoById(solicitacao.getIdPonto()) == null) {
            throw new IllegalArgumentException("O ponto de descarte associado não existe.");
        }
        dao.createSolicitacao(solicitacao);
        // O ID é definido no objeto dentro do DA
    }

    public void atualizarSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        if (dao.getSolicitacaoById(solicitacao.getIdSolicitacao()) == null) {
            throw new IllegalArgumentException("Solicitação de descarte não encontrada para atualização.");
        }
        validarCpfOuCnpj(solicitacao);
        validarDataHoraBusca(solicitacao);
        dao.updateSolicitacao(solicitacao);
    }

    public void deletarSolicitacao(int id) throws SQLException {
        if (dao.getSolicitacaoById(id) == null) {
            throw new IllegalArgumentException("Solicitação de descarte não encontrada para exclusão.");
        }
        dao.deleteSolicitacao(id);
    }

    private void validarCpfOuCnpj(SolicitacaoDescarte solicitacao) {
        boolean cpfPreenchido = solicitacao.getCpf() != null && !solicitacao.getCpf().isEmpty();
        boolean cnpjPreenchido = solicitacao.getCnpj() != null && !solicitacao.getCnpj().isEmpty();

        if (cpfPreenchido == cnpjPreenchido) {
            throw new IllegalArgumentException("Deve ser informado somente o CPF ou o CNPJ.");
        }

        if (cpfPreenchido && !validarCPF(solicitacao.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        if (cnpjPreenchido && !validarCNPJ(solicitacao.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
    }

    private void validarDataHoraBusca(SolicitacaoDescarte solicitacao) {
        LocalDateTime dataAtual = LocalDateTime.now();
        if (solicitacao.getDtHrBusca().toLocalDateTime().isBefore(dataAtual)) {
            throw new IllegalArgumentException("A data e hora da busca devem ser no futuro.");
        }
    }

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}");
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj.matches("\\d{14}");
    }
}
