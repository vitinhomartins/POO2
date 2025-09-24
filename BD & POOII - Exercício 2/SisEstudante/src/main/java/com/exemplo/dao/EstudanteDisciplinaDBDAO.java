package com.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exemplo.model.Estudante;
import com.exemplo.model.Disciplina;

public class EstudanteDisciplinaDBDAO implements IConst {

    // Insere rela��o (matr�cula do estudante em uma disciplina)
    public void insere(int estudanteId, int disciplinaId) throws SQLException {
        String sql = "INSERT INTO estudante_disciplina (estudante_id, disciplina_id) VALUES (?, ?)";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, estudanteId);
            stmt.setInt(2, disciplinaId);
            stmt.executeUpdate();
        }
    }

    // Remove rela��o (desmatr�cula)
    public void remove(int estudanteId, int disciplinaId) throws SQLException {
        String sql = "DELETE FROM estudante_disciplina WHERE estudante_id = ? AND disciplina_id = ?";
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, estudanteId);
            stmt.setInt(2, disciplinaId);
            stmt.executeUpdate();
        }
    }

    // retorna as disciplinas (estudante_id, nome, idade)
    // em que um estudante est� matriculado (estudanteID).
    // """ -> forma de escrever strings multilinhas em Java em vez de usar "\n" ou "+"
    public List<Disciplina> buscaDisciplinasPorEstudante(int estudanteId) throws SQLException {
        String sql = """
            SELECT d.disciplina_id, d.nome FROM disciplina d
            /* jun��o interna de todas as disciplinas
            em que um estudante est� matriculado */ 
            INNER JOIN estudante_disciplina ed ON d.disciplina_id = ed.disciplina_id
            WHERE ed.estudante_id = ? 
        """;

        List<Disciplina> disciplinas = new ArrayList<>();
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, estudanteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setDisciplinaID(rs.getInt("disciplina_id"));
                    disciplina.setNome(rs.getString("nome"));
                    disciplinas.add(disciplina);
                }
            }
        }
        return disciplinas;
    }

    // retorna os estudantes (estudante_id, nome, idade)
    // que est�o matriculados em uma disciplina informada.
    public List<Estudante> buscaEstudantesPorDisciplina(int disciplinaId) throws SQLException {
        String sql = """
            SELECT e.estudante_id, e.nome, e.idade
            FROM estudante e
            /* jun��o interna de todos os estudantes que est�o matriculados
            em uma disciplina espec�fica */  
            INNER JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id
            WHERE ed.disciplina_id = ?
        """;

        List<Estudante> estudantes = new ArrayList<>();
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, disciplinaId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estudante estudante = new Estudante();
                    estudante.setEstudanteID(rs.getInt("estudante_id"));
                    estudante.setNome(rs.getString("nome"));
                    estudante.setIdade(rs.getInt("idade"));
                    estudantes.add(estudante);
                }
            }
        }
        return estudantes;
    }

    // Listar todas as matr�culas (estudante + disciplina)
    public List<String> listaTodasMatriculas() throws SQLException {
        String sql = """
            SELECT e.nome AS estudante, d.nome AS disciplina
            FROM estudante e
            INNER JOIN estudante_disciplina ed ON e.estudante_id = ed.estudante_id
            INNER JOIN disciplina d ON d.disciplina_id = ed.disciplina_id
            ORDER BY e.nome, d.nome
        """;

        List<String> matriculas = new ArrayList<>();
        try (Connection con = Conexao.getConexao(Conexao.stringDeConexao, Conexao.usuario, Conexao.senha);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String registro = rs.getString("estudante") + " -> " + rs.getString("disciplina");
                matriculas.add(registro);
            }
        }
        return matriculas;
    }
}
