package com.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.model.Disciplina;

public class DisciplinaDBDAO implements DisciplinaDAO, IConst {

    // Inserir disciplina (ID gerado automaticamente pelo banco)
    public void insere(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO disciplina (nome) VALUES (?) RETURNING disciplina_id";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    disciplina.setDisciplinaID(rs.getInt("disciplina_id"));
                }
            }
        }
    }

    // Atualizar disciplina
    public void atualiza(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE disciplina SET nome = ? WHERE disciplina_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, disciplina.getNome());
            stmt.setInt(2, disciplina.getDisciplinaID());
            stmt.executeUpdate();
        }
    }

    // Remover disciplina
    public void remove(Disciplina disciplina) throws SQLException {
        String sql = "DELETE FROM disciplina WHERE disciplina_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, disciplina.getDisciplinaID());
            stmt.executeUpdate();
        }
    }

    // Buscar disciplina por c�digo
    public Disciplina buscaPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM disciplina WHERE disciplina_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setDisciplinaID(rs.getInt("disciplina_id"));
                    disciplina.setNome(rs.getString("nome"));
                    return disciplina;
                }
            }
        }
        return null;
    }

    // Listar todas as disciplinas
    public List<Disciplina> listaTodos() throws SQLException {
        String sql = "SELECT * FROM disciplina";
        List<Disciplina> disciplinas = new ArrayList<>();

        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplinaID(rs.getInt("disciplina_id"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        }
        return disciplinas;
    }
}