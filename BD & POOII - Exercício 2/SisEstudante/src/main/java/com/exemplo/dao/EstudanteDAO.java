package com.exemplo.dao;

import java.sql.SQLException;
import java.util.List;

import com.exemplo.model.Estudante;

public interface EstudanteDAO {
    public void insere(Estudante estudante) throws SQLException;

    public void atualiza(Estudante estudante) throws SQLException;

    public void remove(Estudante estudante) throws SQLException;

    public Estudante buscaPorCodigo(int estudanteID) throws SQLException;

    public List<Estudante> listaTodos() throws SQLException;
}
