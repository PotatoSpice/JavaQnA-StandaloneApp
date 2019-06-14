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
 * {@link interfaces.models.IQuestion}.</b>
 * Ou seja, a documentação para cada 'overriden method' encontra-se já
 * especificada na documentação da API. Contudo, novos métodos adicionados ou
 * alterações pertinentes serão devidamente documentadas.
 * <p>
 * <b>Nota:</b> Em relação aos métodos {@link #answer(java.lang.String)} e
 * {@link #evaluateAnswer()}, faz mais sentido que a sua implementação seja
 * realizada somente nas classes descendentes. Por essa razão, estão definidos
 * como métodos abstratos e, consequentemente, também a própria classe.
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
        if (title == null)
        {
            throw new QuestionException();
        }
        this.title = title;
    }

    @Override
    public String getQuestion_description() {
        return question_description;
    }

    @Override
    public void setQuestion_description(String description) throws QuestionException {
        if (description == null)
        {
            throw new QuestionException();
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
    
    @Override
    abstract public void answer(String user_answer);

    @Override
    abstract public boolean evaluateAnswer();

}
