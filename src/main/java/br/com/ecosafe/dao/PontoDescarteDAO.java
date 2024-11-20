package br.com.ecosafe.dao;

import br.com.ecosafe.connection.DatabaseConnection;
import br.com.ecosafe.model.PontoDescarte;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontoDescarteDAO {

    public List<PontoDescarte> getAllPontos() throws SQLException {
        List<PontoDescarte> pontos = new ArrayList<>();
        String sql = "SELECT * FROM ES_PONTO_DESCARTE";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PontoDescarte ponto = new PontoDescarte();
                ponto.setIdPonto(rs.getInt("id_ponto"));
                ponto.setCepPonto(rs.getString("cep_ponto"));
                ponto.setLogradouroPonto(rs.getString("logradouro_ponto"));
                ponto.setUfPonto(rs.getString("uf_ponto"));
                ponto.setCidadePonto(rs.getString("cidade_ponto"));
                ponto.setNumPonto(rs.getInt("num_ponto"));
                pontos.add(ponto);
            }
        }

        return pontos;
    }

    public PontoDescarte getPontoById(int id) throws SQLException {
        PontoDescarte ponto = null;
        String sql = "SELECT * FROM ES_PONTO_DESCARTE WHERE id_ponto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ponto = new PontoDescarte();
                ponto.setIdPonto(rs.getInt("id_ponto"));
                ponto.setCepPonto(rs.getString("cep_ponto"));
                ponto.setLogradouroPonto(rs.getString("logradouro_ponto"));
                ponto.setUfPonto(rs.getString("uf_ponto"));
                ponto.setCidadePonto(rs.getString("cidade_ponto"));
                ponto.setNumPonto(rs.getInt("num_ponto"));
            }
        }

        return ponto;
    }

    public void createPonto(PontoDescarte ponto) throws SQLException {
        String sql = "INSERT INTO ES_PONTO_DESCARTE (cep_ponto, logradouro_ponto, uf_ponto, cidade_ponto, num_ponto) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] { "id_ponto" })) {

            pstmt.setString(1, ponto.getCepPonto());
            pstmt.setString(2, ponto.getLogradouroPonto());
            pstmt.setString(3, ponto.getUfPonto());
            pstmt.setString(4, ponto.getCidadePonto());
            pstmt.setInt(5, ponto.getNumPonto());

            pstmt.executeUpdate();

            // Recuperar o ID gerado automaticamente
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ponto.setIdPonto(generatedKeys.getBigDecimal(1).intValue());
                } else {
                    throw new SQLException("Falha ao criar o ponto de descarte, ID não obtido.");
                }
            }
        }
    }

    public void updatePonto(PontoDescarte ponto) throws SQLException {
        String sql = "UPDATE ES_PONTO_DESCARTE SET cep_ponto = ?, logradouro_ponto = ?, "
                + "uf_ponto = ?, cidade_ponto = ?, num_ponto = ? WHERE id_ponto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ponto.getCepPonto());
            pstmt.setString(2, ponto.getLogradouroPonto());
            pstmt.setString(3, ponto.getUfPonto());
            pstmt.setString(4, ponto.getCidadePonto());
            pstmt.setInt(5, ponto.getNumPonto());
            pstmt.setInt(6, ponto.getIdPonto());

            pstmt.executeUpdate();
        }
    }

    public void deletePonto(int id) throws SQLException {
        String sql = "DELETE FROM ES_PONTO_DESCARTE WHERE id_ponto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<PontoDescarte> getPontosByUfAndCidade(String uf, String cidade) throws SQLException {
        List<PontoDescarte> pontos = new ArrayList<>();
        String sql = "SELECT * FROM ES_PONTO_DESCARTE WHERE uf_ponto = ? AND cidade_ponto = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, uf);
            pstmt.setString(2, cidade);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PontoDescarte ponto = new PontoDescarte();
                ponto.setIdPonto(rs.getInt("id_ponto"));
                ponto.setCepPonto(rs.getString("cep_ponto"));
                ponto.setLogradouroPonto(rs.getString("logradouro_ponto"));
                ponto.setUfPonto(rs.getString("uf_ponto"));
                ponto.setCidadePonto(rs.getString("cidade_ponto"));
                ponto.setNumPonto(rs.getInt("num_ponto"));
                pontos.add(ponto);
            }
        }

        return pontos;
    }
}
