/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package controller;

import models.Question;
import models.QuestionMultipleChoice;
import models.QuestionNumeric;
import models.QuestionYesNo;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 * <p>
 */
public class TestStatistics implements interfaces.controller.ITestStatistics {

    private final Question[] testQuestions;
    private final int NUMBER_QUESTIONS;

    /**
     * Construtor com a inicialização da estrutura de dados a ser alvo de
     * análise.
     *
     * @param qlist estrutura de dados com as questões para análise estatística
     * @param num número de questões não nulas dentro da estrutura de dados
     */
    public TestStatistics(Question[] qlist, int num) {
        this.testQuestions = qlist;
        this.NUMBER_QUESTIONS = num;
    }

    @Override
    public double meanTimePerAnswer() {
        double totalTimeSum = 0, qTime, mean = 0;
        /*
         * NOTA sobre 'object casting': No caso deste método, durante as
         * iterações no ciclo abaixo, uma vez que a classe 'Question' é
         * abstrata, é ímpossível utilizar a sua instância. Então, será
         * necessário realizar o casting para cada uma das questões. Para isso é
         * utilizado o 'instanceof' como forma de fazer essa verificação.
         */
        try
        {
            for (Question q : this.testQuestions)
            { // Calcular o somatório dos tempos de resposta
                if (q instanceof QuestionYesNo)
                {
                    QuestionYesNo temp = (QuestionYesNo) q;
                    qTime // Calcular o tempo gasto na resposta
                            = temp.getQuestion_metadata().getTimestamp_finish()
                            - temp.getQuestion_metadata().getTimestamp_start();
                    totalTimeSum += qTime; // Somar ao tempo total
                } else if (q instanceof QuestionMultipleChoice)
                {
                    QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                    qTime // Calcular o tempo gasto na resposta
                            = temp.getQuestion_metadata().getTimestamp_finish()
                            - temp.getQuestion_metadata().getTimestamp_start();
                    totalTimeSum += qTime; // Somar ao tempo total
                } else if (q instanceof QuestionNumeric)
                {
                    QuestionNumeric temp = (QuestionNumeric) q;
                    qTime // Calcular o tempo gasto na resposta
                            = temp.getQuestion_metadata().getTimestamp_finish()
                            - temp.getQuestion_metadata().getTimestamp_start();
                    totalTimeSum += qTime; // Somar ao tempo total
                }
            }
            mean = (totalTimeSum / testQuestions.length);
        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return Math.floor(mean * 100) / 100; //arredondar 2 casas decimais
    }

    @Override
    public double standardDeviationTimePerAnsewer() {
        double mean = this.meanTimePerAnswer(); // Buscar a média dos tempos
        double variance = 0, varianceSum = 0, qTime;
        /*
         * NOTA sobre 'object casting': O mesmo que o método
         * 'this.meanTimePerAnswer()'.
         */
        try
        {
            for (Question q : this.testQuestions)
            { // Calcular o somatório para o cálculo da variância
                qTime // Calcular o tempo gasto na resposta
                        = q.getQuestion_metadata().getTimestamp_finish()
                        - q.getQuestion_metadata().getTimestamp_start();
                varianceSum += Math.pow((qTime - mean), 2);
            }
            variance = varianceSum / testQuestions.length;

        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return Math.floor(Math.sqrt(variance) * 100) / 100; // arredondar 2 casas
    }

    @Override
    public double correctAnswerPecentage() {
        double result = ((double) this.correctAnswer()
                / (double) NUMBER_QUESTIONS) * 100D;
        return Math.floor(result * 100) / 100; // arredondar a 2 casas decimais
    }

    @Override
    public double incorrectAnswerPecentage() {
        double result = ((double) this.incorrectAnswer()
                / (double) NUMBER_QUESTIONS) * 100D;
        return Math.floor(result * 100) / 100; // arredondar a 2 casas decimais
    }

    @Override
    public int correctAnswer() {
        int count = 0;
        /*
         * NOTA sobre 'object casting': No caso deste método, durante as
         * iterações no ciclo abaixo, tendo em conta que o método que será
         * chamado é específico de cada uma das questões descendentes, uma vez
         * que se trata de um método abstrato na classe principal, será então
         * necessário realizar o casting para cada uma das questões. Para isso é
         * utilizado o 'instanceof' como forma de fazer essa verificação.
         */
        try
        {
            for (Question q : this.testQuestions)
            { // Verificar todas as posições da estrutura de dados
                if (q instanceof QuestionYesNo)
                {
                    QuestionYesNo temp = (QuestionYesNo) q;
                    if (temp.evaluateAnswer())
                    { // Pergunta avaliada como correta, incrementa o contador
                        count++;
                    }
                } else if (q instanceof QuestionMultipleChoice)
                {
                    QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                    if (temp.evaluateAnswer())
                    { // Pergunta avaliada como correta, incrementa o contador
                        count++;
                    }
                } else if (q instanceof QuestionNumeric)
                {
                    QuestionNumeric temp = (QuestionNumeric) q;
                    if (temp.evaluateAnswer())
                    { // Pergunta avaliada como correta, incrementa o contador
                        count++;
                    }
                }
            }
        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return count;
    }

    @Override
    public int incorrectAnswer() {
        int count = 0;
        /*
         * NOTA sobre 'object casting': O mesmo que o método
         * 'this.correctAnswer()'
         */
        try
        {
            for (Question q : this.testQuestions)
            { // Verificar todas as posições da estrutura de dados
                if (q instanceof QuestionYesNo)
                {
                    QuestionYesNo temp = (QuestionYesNo) q;
                    if (!temp.evaluateAnswer())
                    { // Pergunta avaliada como incorreta, incrementa o contador
                        count++;
                    }
                } else if (q instanceof QuestionMultipleChoice)
                {
                    QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                    if (!temp.evaluateAnswer())
                    { // Pergunta avaliada como incorreta, incrementa o contador
                        count++;
                    }
                } else if (q instanceof QuestionNumeric)
                {
                    QuestionNumeric temp = (QuestionNumeric) q;
                    if (!temp.evaluateAnswer())
                    { // Pergunta avaliada como incorreta, incrementa o contador
                        count++;
                    }
                }
            }
        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return count;
    }

    @Override
    public Question[] incorrectAnswers() {
        int j = this.incorrectAnswer(); // Buscar o número de respostas corretas
        Question[] result
                = // Instanciar uma estrutura com o tamanho necessário
                new Question[j + 1];

        j = 0;
        /*
         * NOTA sobre 'object casting': O mesmo que o método
         * 'this.correctAnswer()'
         */
        try
        {
            for (Question q : testQuestions)
            {
                if (q instanceof QuestionYesNo)
                {
                    QuestionYesNo temp = (QuestionYesNo) q;
                    if (!temp.evaluateAnswer())
                    { // Adicionar as respostas incorretas
                        result[j] = q;
                        j++;
                    }
                } else if (q instanceof QuestionMultipleChoice)
                {
                    QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                    if (!q.evaluateAnswer())
                    { // Adicionar as respostas incorretas
                        result[j] = q;
                        j++;
                    }
                } else if (q instanceof QuestionNumeric)
                {
                    QuestionNumeric temp = (QuestionNumeric) q;
                    if (!q.evaluateAnswer())
                    { // Adicionar as respostas incorretas
                        result[j] = q;
                        j++;
                    }
                }
            }
            return result;
        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return null;
    }

    @Override
    public Question[] correctAnswers() {
        int j = this.correctAnswer(); // Buscar o número de respostas corretas
        Question[] result
                = // Instanciar uma estrutura com o tamanho necessário
                new Question[j + 1];

        j = 0;
        /*
         * NOTA sobre 'object casting': O mesmo que o método
         * 'this.correctAnswer()'
         */
        try
        {
            for (Question q : testQuestions)
            { // Iterar sobre a estrutura de dados principal
                if (q instanceof QuestionYesNo)
                {
                    QuestionYesNo temp = (QuestionYesNo) q;
                    if (!temp.evaluateAnswer())
                    { // Adicionar as respostas corretas
                        result[j] = q;
                        j++;
                    }
                } else if (q instanceof QuestionMultipleChoice)
                {
                    QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                    if (!q.evaluateAnswer())
                    { // Adicionar as respostas corretas
                        result[j] = q;
                        j++;
                    }
                } else if (q instanceof QuestionNumeric)
                {
                    QuestionNumeric temp = (QuestionNumeric) q;
                    if (!q.evaluateAnswer())
                    { // Adicionar as respostas corretas
                        result[j] = q;
                        j++;
                    }
                }
            }
            return result;
        } catch (NullPointerException exc)
        {
            System.out.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return null;
    }
}
