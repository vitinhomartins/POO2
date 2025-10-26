package br.com.quiz.quiz.model;

public class Pergunta {
    private int perguntaId;
    private String textoPergunta;
    private int numeroQuestao;
    private String[] alternativas;
    public Pergunta(int perguntaId, String textoPergunta, int numeroQuestao, String[] alternativas) {
        this.perguntaId = perguntaId;
        this.textoPergunta = textoPergunta;
        this.numeroQuestao = numeroQuestao;
        this.alternativas = alternativas;
    }

    public int getPerguntaId() {
        return perguntaId;
    }

    public String getTextoPergunta() {
        return textoPergunta;
    }

    public int getNumeroQuestao() {
        return numeroQuestao;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public void setIdPergunta(int perguntaId) {
        this.perguntaId = perguntaId;
    }
}
