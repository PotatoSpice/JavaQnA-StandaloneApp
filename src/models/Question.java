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

import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 * <p>
 * <b>Nota:</b> Em relação aos 'overriden methods', a implementação de dois
 * desses métodos faz mais sentido ser realizada somente nas classes
 * descendentes. Então, encontram-se definidos como métodos abstratos e
 * consequentemente também a própria classe.
 */
public abstract class Question implements interfaces.models.IQuestion {

    private String title;
    private String question_description;
    private IQuestionMetadata question_metadata;
    private boolean done = false;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) throws QuestionException {
        if (done) {
            throw new QuestionException("Questão já foi respondida! "
                    + "Impossível alterar o título.");
        }
        this.title = title;
    }

    @Override
    public String getQuestion_description() {
        return question_description;
    }

    @Override
    public void setQuestion_description(String description) throws QuestionException {
        if (done) {
            throw new QuestionException("Questão já foi respondida! "
                    + "Impossivel alterar a descrição.");
        }
        this.question_description = description;
    }

    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return question_metadata;
    }

    @Override
    public void setQuestion_metadata(IQuestionMetadata metadata) {
        this.question_metadata = metadata;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Este método só será usado por classes descendentes, tendo em conta que
     * não faria sentido ser utilizado nesta classe.
     *
     * @param user_answer
     */
    @Override
    abstract public void answer(String user_answer);

    /**
     * A avaliação da resposta é realizada apenas nas classes descendentes. Ou
     * seja, o método desta classe será abstrato.
     *
     * @return verdadeiro se a resposta estiver correta, falso no contrário
     */
    @Override
    abstract public boolean evaluateAnswer();

}
