package com.exemplo.dao;

import java.sql.SQLException;
import java.util.List;

import com.exemplo.model.EstudanteDisciplina;

public interface EstudanteDisciplinaDAO {
    public void insere(EstudanteDisciplina estudanteDisciplina)throws SQLException;
    public void atualiza(EstudanteDisciplina estudanteDisciplina) throws SQLException;
    public void remove(EstudanteDisciplina estudanteDisciplina) throws SQLException;
    public EstudanteDisciplina buscaRegistro (String registro, int codigo) throws SQLException;
    public List<EstudanteDisciplina> listaTodos() throws SQLException;
}
