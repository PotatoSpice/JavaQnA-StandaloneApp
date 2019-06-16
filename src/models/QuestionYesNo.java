/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package models;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * {@link interfaces.models.IQuestionYesNo}.</b>
 * Ou seja, a documentação para cada 'overriden method' encontra-se já
 * especificada na documentação da API. Contudo, novos métodos adicionados ou
 * alterações pertinentes serão devidamente documentadas.
 */
public class QuestionYesNo extends Question implements interfaces.models.IQuestionYesNo {

    private String correct_answer, user_answer;

    public QuestionYesNo() {
        super();
    }

    @Override
    public String getCorrect_answer() {
        return correct_answer;
    }

    @Override
    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    @Override
    public String getUser_answer() {
        return user_answer;
    }

    @Override
    public void setUser_answer(String user_answer) {
        this.user_answer = user_answer;
    }

    @Override
    public void answer(String user_answer) {
        this.setUser_answer(user_answer);
        super.setDone(true); // Sinalizar a realização da resposta
    }

    @Override
    public boolean evaluateAnswer() {
        return correct_answer.equalsIgnoreCase(user_answer);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("\n[ Questão Sim / Não ]");
        if (super.isDone()) {
            QuestionMetadata meta = (QuestionMetadata) super.getQuestion_metadata();
            builder.append("\nQuestão Concluida! ")
                    .append(": Tempo de Realização: ")
                    .append(Math.round(meta.getDoneTimeSeconds())).append(" segundos");
        } else {
            builder.append("\n{ Questão Incompleta! }");
        }
        builder.append("\n\tTítulo: ").append(super.getTitle())
                .append("\n\tDescrição: ").append(super.getQuestion_description());
        builder.append("\n\t-\n\tResposta Correta: ").append(this.correct_answer)
                .append("\n\tResposta Utilizador: ").append(this.user_answer)
                .append('\n');
        return builder.toString();
    }
}
