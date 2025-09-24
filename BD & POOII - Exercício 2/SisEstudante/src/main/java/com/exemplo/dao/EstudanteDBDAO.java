package com.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.model.Estudante;

public class EstudanteDBDAO implements EstudanteDAO, IConst {

    // Inserir estudante (sem passar o ID, gerado automaticamente pelo banco)
    public void insere(Estudante estudante) throws SQLException {
        String sql = "INSERT INTO estudante (nome, idade) VALUES (?, ?) RETURNING estudante_id";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, estudante.getNome());
            stmt.setInt(2, estudante.getIdade());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estudante.setEstudanteID(rs.getInt("estudante_id"));
                }
            }
        }
    }

    // Atualizar estudante
    public void atualiza(Estudante estudante) throws SQLException {
        String sql = "UPDATE estudante SET nome = ?, idade = ? WHERE estudante_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, estudante.getNome());
            stmt.setInt(2, estudante.getIdade());
            stmt.setInt(3, estudante.getEstudanteID());
            stmt.executeUpdate();
        }
    }

    // Remover estudante
    public void remove(Estudante estudante) throws SQLException {
        String sql = "DELETE FROM estudante WHERE estudante_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, estudante.getEstudanteID());
            stmt.executeUpdate();
        }
    }

    // Buscar estudante por c�digo
    public Estudante buscaPorCodigo(int estudanteID) throws SQLException {
        String sql = "SELECT * FROM estudante WHERE estudante_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, estudanteID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudante estudante = new Estudante();
                    estudante.setEstudanteID(rs.getInt("estudante_id"));
                    estudante.setNome(rs.getString("nome"));
                    estudante.setIdade(rs.getInt("idade"));
                    return estudante;
                }
            }
        }
        return null;
    }

    // Listar todos os estudantes
    public List<Estudante> listaTodos() throws SQLException {
        String sql = "SELECT * FROM estudante";
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estudante estudante = new Estudante();
                estudante.setEstudanteID(rs.getInt("estudante_id"));
                estudante.setNome(rs.getString("nome"));
                estudante.setIdade(rs.getInt("idade"));
                estudantes.add(estudante);
            }
        }
        return estudantes;
    }
}