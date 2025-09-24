package com.exemplo.model;
public class EstudanteDisciplina {
    /*
     * A classe EstudanteDisciplina reutiliza a classes Estudante e Disciplina por meio de
     * Composi��o.
     * Como exemplo: o atributo ou vari�vel de inst�ncia estudante � uma refer�ncia a
     * objetos da classe Estudante
     */
    private Estudante estudante;
    private Disciplina disciplina;

    public EstudanteDisciplina() {}

    public EstudanteDisciplina(Estudante estudante, Disciplina disciplina) {
        this.estudante = estudante;
        this.disciplina = disciplina;
    }

    public Estudante getEstudante() {
        return estudante;
    }

    public void setEstudante(Estudante estudante) {
        this.estudante = estudante;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public String toString() {
        return "EstudanteDisciplina{" + "estudante=" + this.estudante + ", disciplina=" + this.disciplina + '}';
    }
}