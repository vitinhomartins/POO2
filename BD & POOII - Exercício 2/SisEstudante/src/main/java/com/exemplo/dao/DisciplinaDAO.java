package com.exemplo.dao;

import java.sql.SQLException;
import java.util.List;
import com.exemplo.model.Disciplina;

public interface DisciplinaDAO {
    public void insere(Disciplina disciplina) throws SQLException;
    public void atualiza(Disciplina disciplina) throws SQLException;
    public void remove(Disciplina disciplina) throws SQLException;
    public Disciplina buscaPorCodigo(int codigo) throws SQLException;
    public List<Disciplina> listaTodos() throws SQLException;
}