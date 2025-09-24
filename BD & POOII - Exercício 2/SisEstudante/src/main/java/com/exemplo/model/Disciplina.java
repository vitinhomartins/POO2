package com.exemplo.model;

public class Disciplina {
    private int disciplinaID;
    private String nome;

    public Disciplina() {}

    public Disciplina(String nome) {
        this.nome = nome;
    }

    public Disciplina(int disciplinaID, String nome) {
        this.disciplinaID = disciplinaID;
        this.nome = nome;
    }

    public Disciplina(String nome, int disciplinaID) {
        this.disciplinaID = disciplinaID;
        this.nome = nome;
    }

    public int getDisciplinaID() {
        return disciplinaID;
    }

    public void setDisciplinaID(int disciplinaID) {
        this.disciplinaID = disciplinaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Disciplina{" + "codigo=" + disciplinaID + ", nome=" + nome + '}';
    }
}
