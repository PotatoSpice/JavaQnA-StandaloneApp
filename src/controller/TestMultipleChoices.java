package controller;

import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import models.Question;
import models.QuestionMultipleChoice;

public class TestMultipleChoices extends Test {
    private final Question[] questions;
    private final int DEFAULT_SIZE = 50;

    /**
     * Construtor para inicialização de {@link #questions} com tamanho inicial
     * por defeito.
     */
    public TestMultipleChoices() {
        this.questions = new Question[this.DEFAULT_SIZE];
    }

    @Override
    public boolean addQuestion(IQuestion q) throws TestException {
        if (q == null)
        { // Lança uma exceção se o objeto for nulo
            throw new TestException();
        }
        /*
         * Nota sobre implementação:
         * O enunciado não fala nisto mas seria conveniente que o método não
         * permitisse que fossem introduzidas questões repetidas. Isso fazia-se
         * facilmente com o código abaixo:
         * if (this.findQuestion(q) != -1) {
         *      return false;
         *      // ou
         *      // throw new TestException();
         * }
         */
        int pos = 0;
        while (pos < questions.length)
        { // Iterar sobre a estrutura de dados
            if (questions[pos] == null)
            { // Insere o objeto quando encontrar uma posição vazia

                if(q instanceof QuestionMultipleChoice) {
                    questions[pos] = (Question) q;
                    return true;
                }
            }
            pos++;
        }
        return false;
    }


}
