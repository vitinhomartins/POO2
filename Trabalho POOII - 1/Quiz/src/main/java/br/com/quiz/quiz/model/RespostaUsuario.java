package br.com.quiz.quiz.model;

import java.sql.Timestamp;

public class RespostaUsuario {
    private int idResposta;
    private int idUsuario;
    private int idPergunta;
    private char respostaEscolhida;
    private boolean correta;
    private Timestamp dataResposta;

    public RespostaUsuario() {
    }

    public RespostaUsuario(int idUsuario, int idPergunta, char respostaEscolhida, boolean correta) {
        this.idUsuario = idUsuario;
        this.idPergunta = idPergunta;
        this.respostaEscolhida = respostaEscolhida;
        this.correta = correta;
        // dataResposta pode ser setada automaticamente no DAO se necessário
    }

    public int getIdResposta() {
        return idResposta;
    }

    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public char getRespostaEscolhida() {
        return respostaEscolhida;
    }

    public void setRespostaEscolhida(char respostaEscolhida) {
        this.respostaEscolhida = respostaEscolhida;
    }

    public boolean isCorreta() {
        return correta;
    }

    public void setCorreta(boolean correta) {
        this.correta = correta;
    }

    public Timestamp getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(Timestamp dataResposta) {
        this.dataResposta = dataResposta;
    }
}
