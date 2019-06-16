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
 * {@link interfaces.models.IQuestionNumeric}.</b>
 * Ou seja, a documentação para cada 'overriden method' encontra-se já
 * especificada na documentação da API. Contudo, novos métodos adicionados ou
 * alterações pertinentes serão devidamente documentadas.
 */
public class QuestionNumeric extends Question implements interfaces.models.IQuestionNumeric {

    private double correct_answer, user_answer;

    public QuestionNumeric() {
        super();
    }

    @Override
    public double getCorrect_anwser() {
        return correct_answer;
    }

    @Override
    public void setCorrect_anwser(double correct_answer) {
        this.correct_answer = correct_answer;
    }

    @Override
    public double getUser_answer() {
        return user_answer;
    }

    @Override
    public void setUser_answer(double user_answer) {
        this.user_answer = user_answer;
    }

    @Override
    public void answer(String user_answer) {
        try {
            this.setUser_answer(Double.parseDouble(user_answer));
            this.setDone(true); // Sinalizar a realização da resposta
        } catch (NumberFormatException exc) {
            System.err.println("AVISO (em: " + this.getClass().toString()
                    + " )\n> Resposta terá de ser um número!\n");
        }
    }

    @Override
    public boolean evaluateAnswer() {
        return correct_answer == user_answer;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("\n[ Questão Numerica ]");
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
