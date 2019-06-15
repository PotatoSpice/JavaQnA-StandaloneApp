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
import models.QuestionMetadata;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * {@link interfaces.controller.ITestStatistics}.</b>
 * Ou seja, a documentação para cada 'overriden method' encontra-se já
 * especificada na documentação da API. Contudo, novos métodos adicionados ou
 * alterações pertinentes serão devidamente documentadas.
 */
public class TestStatistics implements interfaces.controller.ITestStatistics {

    private final Question[] testQuestions;

    /**
     * Construtor com a inicialização de {@link #testQuestions}, alvo de
     * análise.
     *
     * @param qlist estrutura de dados com as questões para análise estatística
     */
    public TestStatistics(Question[] qlist) {
        this.testQuestions = qlist;
    }

    @Override
    public double meanTimePerAnswer() {
        double totalTimeSum = 0, qTime, mean = 0;
        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : this.testQuestions)
            { // Calcular o somatório dos tempos de resposta
                QuestionMetadata meta = (QuestionMetadata) q.getQuestion_metadata();
                meta.getDoneTimeMilliseconds();
                qTime = meta.getDoneTimeMilliseconds(); // Tempo de resposta
                totalTimeSum += qTime; // Somar ao tempo total
            }
            mean = (totalTimeSum / testQuestions.length);
        } catch (NullPointerException exc)
        {
            System.err.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        mean /= 1000; // Colocar o tempo em segundos
        return Math.floor(mean * 100) / 100; //arredondar 2 casas decimais
    }

    @Override
    public double standardDeviationTimePerAnsewer() {
        double mean = this.meanTimePerAnswer(); // Buscar a média dos tempos
        double variance = 0, varianceSum = 0, qTime;

        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : this.testQuestions)
            { // Calcular o somatório para o cálculo da variância
                QuestionMetadata meta = (QuestionMetadata) q.getQuestion_metadata();
                qTime = meta.getDoneTimeSeconds(); // Tempo de Resposta, segundos
                // Média dos tempos está em segundos, logo igual para o tempo
                varianceSum += Math.pow((qTime - mean), 2);
            }
            variance = varianceSum / testQuestions.length;

        } catch (NullPointerException exc)
        {
            System.err.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return Math.ceil(Math.sqrt(variance) * 10000) / 10000; // arredondar 4 casas
    }

    @Override
    public double correctAnswerPecentage() {
        double result = ((double) this.correctAnswer()
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100; // arredondar a 2 casas decimais
    }

    @Override
    public double incorrectAnswerPecentage() {
        double result = ((double) this.incorrectAnswer()
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100; // arredondar a 2 casas decimais
    }

    @Override
    public int correctAnswer() {
        int count = 0;
        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : this.testQuestions)
            { // Verificar todas as posições da estrutura de dados original
                if (q.evaluateAnswer())
                { // Pergunta avaliada como correta, incrementa o contador
                    count++;
                }
            }
        } catch (NullPointerException exc)
        {
            System.err.println("Class Name: " + this.getClass().getName() + " - "
                    + "A estrutura de dados disponibilizada contém elementos inválidos!");
        }
        return count;
    }

    @Override
    public int incorrectAnswer() {
        int count = 0;
        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : this.testQuestions)
            { // Verificar todas as posições da estrutura de dados original
                if (!q.evaluateAnswer())
                { // Pergunta avaliada como incorreta, incrementa o contador
                    count++;
                }
            }
        } catch (NullPointerException exc)
        {
            System.err.println("Class Name: " + this.getClass().getName() + " - "
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
        for (Question q : testQuestions)
        { // Iterar sobre a estrutura de dados principal para inserir as questões
            if (q != null && !q.evaluateAnswer())
            { // Adicionar as respostas incorretas
                result[j] = q;
                j++; // Incrementar o índice da estrutura para retorno
            }
        }
        return result;
    }

    @Override
    public Question[] correctAnswers() {
        int j = this.correctAnswer(); // Buscar o número de respostas corretas
        Question[] result
                = // Instanciar uma estrutura com o tamanho necessário
                new Question[j + 1];

        j = 0;
        for (Question q : testQuestions)
        { // Iterar sobre a estrutura de dados principal para inserir as questões
            if (q != null && q.evaluateAnswer())
            { // Adicionar as respostas corretas
                result[j] = q;
                j++; // Incrementar o índice da estrutura para retorno
            }
        }
        return result;
    }
}
