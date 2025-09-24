package com.exemplo.main;

import java.sql.SQLException;

import com.exemplo.dao.DisciplinaDBDAO;
import com.exemplo.dao.EstudanteDBDAO;
import com.exemplo.dao.EstudanteDisciplinaDBDAO;
import com.exemplo.model.Disciplina;
import com.exemplo.model.Estudante;

public class Main {

    public static void main(String[] args) throws SQLException {
        EstudanteDBDAO estudanteDAO = new EstudanteDBDAO();
        DisciplinaDBDAO disciplinaDAO = new DisciplinaDBDAO();
        EstudanteDisciplinaDBDAO estudanteDisciplinaDAO = new EstudanteDisciplinaDBDAO();

        System.out.println("######### Opera��es sobre a tabela Estudante ###########");

        // Insere estudantes (sem passar ID, gerado pelo banco)
        Estudante estudante = new Estudante("Vicente", 22);
        estudanteDAO.insere(estudante);
        System.out.println("Insere o estudante Vicente: " + estudante);

        System.out.println("Lista os estudantes no banco:");
        System.out.println(estudanteDAO.listaTodos());

        estudante.setNome("Vicente Theodoro");
        estudante.setIdade(17);
        estudanteDAO.atualiza(estudante);
        System.out.println("Atualiza estudante Vicente -> " + estudante);

        System.out.println("Lista os estudantes no banco:");
        System.out.println(estudanteDAO.listaTodos());

        Estudante estudante02 = new Estudante("Mario", 23);
        estudanteDAO.insere(estudante02);
        System.out.println("Insere o estudante Mario: " + estudante02);

        Estudante estudante03 = new Estudante("Mariane", 19);
        estudanteDAO.insere(estudante03);
        System.out.println("Insere a estudante Mariane: " + estudante03);

        System.out.println("Lista os estudantes no banco:");
        System.out.println(estudanteDAO.listaTodos());

        System.out.println("Retorna o estudante Mario:");
        System.out.println(estudanteDAO.buscaPorCodigo(estudante02.getEstudanteID()));

        System.out.println("Remove o estudante Mario:");
        estudanteDAO.remove(estudante02);

        System.out.println("Lista os estudantes no banco:");
        System.out.println(estudanteDAO.listaTodos());

        System.out.println("\n######### Opera��es sobre a tabela Disciplina ###########");

        Disciplina disciplina = new Disciplina("Matem�tica");
        disciplinaDAO.insere(disciplina);
        System.out.println("Insere a disciplina Matem�tica: " + disciplina);

        System.out.println("Lista as disciplinas no banco:");
        System.out.println(disciplinaDAO.listaTodos());

        disciplina.setNome("C�lculo I");
        disciplinaDAO.atualiza(disciplina);
        System.out.println("Atualiza disciplina Matem�tica -> " + disciplina);

        Disciplina disciplina02 = new Disciplina("Portugu�s");
        disciplinaDAO.insere(disciplina02);
        System.out.println("Insere a disciplina Portugu�s: " + disciplina02);

        Disciplina disciplina03 = new Disciplina("Ingl�s");
        disciplinaDAO.insere(disciplina03);
        System.out.println("Insere a disciplina Ingl�s: " + disciplina03);

        System.out.println("Lista as disciplinas no banco:");
        System.out.println(disciplinaDAO.listaTodos());

        System.out.println("Retorna a disciplina Portugu�s:");
        System.out.println(disciplinaDAO.buscaPorCodigo(disciplina02.getDisciplinaID()));

        System.out.println("Remove a disciplina Portugu�s:");
        disciplinaDAO.remove(disciplina02);

        System.out.println("Lista as disciplinas no banco:");
        System.out.println(disciplinaDAO.listaTodos());

        System.out.println("\n######### Opera��es sobre a tabela EstudanteDisciplina ###########");

        // Cria rela��o entre estudante (Vicente) e disciplina (C�lculo I)
        estudanteDisciplinaDAO.insere(estudante.getEstudanteID(), disciplina.getDisciplinaID());
        System.out.println("Cria rela��o entre estudante Vicente e disciplina C�lculo I");

        System.out.println("Lista todas as rela��es entre estudante e disciplina:");
        System.out.println(estudanteDisciplinaDAO.listaTodasMatriculas());

        System.out.println("\n######### Consultas com JOIN ###########");

        // Buscar as disciplinas de um estudante (ex: Vicente)
        System.out.println("Disciplinas do estudante Vicente:");
        System.out.println(estudanteDisciplinaDAO.buscaDisciplinasPorEstudante(estudante.getEstudanteID()));

        // Buscar os estudantes de uma disciplina (ex: C�lculo I)
        System.out.println("Estudantes da disciplina C�lculo I:");
        System.out.println(estudanteDisciplinaDAO.buscaEstudantesPorDisciplina(disciplina.getDisciplinaID()));

    }
}