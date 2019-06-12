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
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 */
public class QuestionMultipleChoice extends Question implements interfaces.models.IQuestionMultipleChoice {

    private String[] options;
    private String correct_answer, user_answer;

    @Override
    public String[] getOptions() {
        return options;
    }

    @Override
    public void setOptions(String[] options) {
        this.options = options;
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
    }
    
    @Override
    public boolean evaluateAnswer() {
        return correct_answer.equals(user_answer);
    }

}
