package br.com.ecosafe.dao;

import br.com.ecosafe.connection.DatabaseConnection;
import br.com.ecosafe.model.SolicitacaoDescarte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoDescarteDAO {

    public List<SolicitacaoDescarte> getAllSolicitacoes() throws SQLException {
        List<SolicitacaoDescarte> solicitacoes = new ArrayList<>();
        String sql = "SELECT * FROM ES_SOLICITACAO_DESCARTE";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SolicitacaoDescarte solicitacao = new SolicitacaoDescarte();
                solicitacao.setIdSolicitacao(rs.getInt("id_solicitacao"));
                solicitacao.setCnpj(rs.getString("cnpj"));
                solicitacao.setCpf(rs.getString("cpf"));
                solicitacao.setNumeroContato(rs.getString("numero_contato"));
                solicitacao.setCepEmpresa(rs.getString("cep_empresa"));
                solicitacao.setLogradouroEmpresa(rs.getString("logradouro_empresa"));
                solicitacao.setUfEmpresa(rs.getString("uf_empresa"));
                solicitacao.setCidadeEmpresa(rs.getString("cidade_empresa"));
                solicitacao.setNumeroEmpresa(rs.getInt("numero_empresa"));
                solicitacao.setTipoCaminhao(rs.getString("tipo_caminhao"));
                solicitacao.setValorServico(rs.getDouble("valor_servico"));
                solicitacao.setFormaPagamento(rs.getString("forma_pagamento"));
                solicitacao.setDtHrBusca(rs.getTimestamp("dt_hr_busca"));
                solicitacao.setIdPonto(rs.getInt("id_ponto"));
                solicitacoes.add(solicitacao);
            }
        }

        return solicitacoes;
    }

    public SolicitacaoDescarte getSolicitacaoById(int id) throws SQLException {
        SolicitacaoDescarte solicitacao = null;
        String sql = "SELECT * FROM ES_SOLICITACAO_DESCARTE WHERE id_solicitacao = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                solicitacao = new SolicitacaoDescarte();
                solicitacao.setIdSolicitacao(rs.getInt("id_solicitacao"));
                solicitacao.setCnpj(rs.getString("cnpj"));
                solicitacao.setCpf(rs.getString("cpf"));
                solicitacao.setNumeroContato(rs.getString("numero_contato"));
                solicitacao.setCepEmpresa(rs.getString("cep_empresa"));
                solicitacao.setLogradouroEmpresa(rs.getString("logradouro_empresa"));
                solicitacao.setUfEmpresa(rs.getString("uf_empresa"));
                solicitacao.setCidadeEmpresa(rs.getString("cidade_empresa"));
                solicitacao.setNumeroEmpresa(rs.getInt("numero_empresa"));
                solicitacao.setTipoCaminhao(rs.getString("tipo_caminhao"));
                solicitacao.setValorServico(rs.getDouble("valor_servico"));
                solicitacao.setFormaPagamento(rs.getString("forma_pagamento"));
                solicitacao.setDtHrBusca(rs.getTimestamp("dt_hr_busca"));
                solicitacao.setIdPonto(rs.getInt("id_ponto"));
            }
        }

        return solicitacao;
    }

    public void createSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        String sql = "INSERT INTO ES_SOLICITACAO_DESCARTE (cnpj, cpf, numero_contato, "
                + "cep_empresa, logradouro_empresa, uf_empresa, cidade_empresa, numero_empresa, "
                + "tipo_caminhao, valor_servico, forma_pagamento, dt_hr_busca, id_ponto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "id_solicitacao" })) {

            pstmt.setString(1, solicitacao.getCnpj());
            pstmt.setString(2, solicitacao.getCpf());
            pstmt.setString(3, solicitacao.getNumeroContato());
            pstmt.setString(4, solicitacao.getCepEmpresa());
            pstmt.setString(5, solicitacao.getLogradouroEmpresa());
            pstmt.setString(6, solicitacao.getUfEmpresa());
            pstmt.setString(7, solicitacao.getCidadeEmpresa());
            pstmt.setInt(8, solicitacao.getNumeroEmpresa());
            pstmt.setString(9, solicitacao.getTipoCaminhao());
            pstmt.setDouble(10, solicitacao.getValorServico());
            pstmt.setString(11, solicitacao.getFormaPagamento());
            pstmt.setTimestamp(12, solicitacao.getDtHrBusca());
            pstmt.setInt(13, solicitacao.getIdPonto());

            pstmt.executeUpdate();

            // Recuperar o ID gerado automaticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    solicitacao.setIdSolicitacao(generatedKeys.getBigDecimal(1).intValue());
                } else {
                    throw new SQLException("Falha ao criar a solicitação de descarte, ID não obtido.");
                }
            }
        }
    }

    public void updateSolicitacao(SolicitacaoDescarte solicitacao) throws SQLException {
        String sql = "UPDATE ES_SOLICITACAO_DESCARTE SET cnpj = ?, cpf = ?, numero_contato = ?, "
                + "cep_empresa = ?, logradouro_empresa = ?, uf_empresa = ?, cidade_empresa = ?, "
                + "numero_empresa = ?, tipo_caminhao = ?, valor_servico = ?, forma_pagamento = ?, "
                + "dt_hr_busca = ?, id_ponto = ? WHERE id_solicitacao = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, solicitacao.getCnpj());
            pstmt.setString(2, solicitacao.getCpf());
            pstmt.setString(3, solicitacao.getNumeroContato());
            pstmt.setString(4, solicitacao.getCepEmpresa());
            pstmt.setString(5, solicitacao.getLogradouroEmpresa());
            pstmt.setString(6, solicitacao.getUfEmpresa());
            pstmt.setString(7, solicitacao.getCidadeEmpresa());
            pstmt.setInt(8, solicitacao.getNumeroEmpresa());
            pstmt.setString(9, solicitacao.getTipoCaminhao());
            pstmt.setDouble(10, solicitacao.getValorServico());
            pstmt.setString(11, solicitacao.getFormaPagamento());
            pstmt.setTimestamp(12, solicitacao.getDtHrBusca());
            pstmt.setInt(13, solicitacao.getIdPonto());
            pstmt.setInt(14, solicitacao.getIdSolicitacao());

            pstmt.executeUpdate();
        }
    }

    public void deleteSolicitacao(int id) throws SQLException {
        String sql = "DELETE FROM ES_SOLICITACAO_DESCARTE WHERE id_solicitacao = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
