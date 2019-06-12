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

import interfaces.models.IQuestion;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 */
public class TestStatistics implements interfaces.controller.ITestStatistics {

    private final IQuestion[] testQuestions;

    /**
     * Construtor com a inicialização da estrutura de dados a ser alvo de
     * análise.
     *
     * @param qlist estrutura de dados com as questões para análise estatística
     */
    public TestStatistics(IQuestion[] qlist) {
        this.testQuestions = qlist;
    }

    @Override
    public double meanTimePerAnswer() {
        double totalTimeSum = 0, qTime;
        for (IQuestion q : this.testQuestions)
        { // Calcular o somatório dos tempos de resposta
            qTime // Calcular o tempo gasto na resposta
                    = q.getQuestion_metadata().getTimestamp_finish()
                    - q.getQuestion_metadata().getTimestamp_start();
            totalTimeSum += qTime; // Somar ao tempo total
        }
        /* 
        Utilização do Math.floor, juntamente com a multiplicação e divisão
        por 100, serve para arredondar o valor calculado da média do tempo de 
        resposta a duas casas decimais.
         */
        return Math.floor((totalTimeSum / testQuestions.length) * 100) / 100;
    }

    @Override
    public double standardDeviationTimePerAnsewer() {
        double mean = this.meanTimePerAnswer(); // Buscar a média dos tempos
        double variance, varianceSum = 0, qTime;

        for (IQuestion q : this.testQuestions)
        { // Calcular o somatório para o cálculo da variância
            qTime // Calcular o tempo gasto na resposta
                    = q.getQuestion_metadata().getTimestamp_finish()
                    - q.getQuestion_metadata().getTimestamp_start();
            varianceSum += Math.pow((qTime - mean), 2);
        }

        variance = varianceSum / testQuestions.length;

        /* 
        Utilização do Math.floor, juntamente com a multiplicação e divisão
        por 100, serve para arredondar o valor calculado do desvio padrão a 
        duas casas decimais.
         */
        return Math.floor(Math.sqrt(variance) * 100) / 100;
    }

    @Override
    public double correctAnswerPecentage() {
        return (correctAnswer() / testQuestions.length) * 100;
    }

    @Override
    public double incorrectAnswerPecentage() {
        return (incorrectAnswer() / testQuestions.length) * 100;
    }

    @Override
    public int correctAnswer() {
        int count = 0;
        for (IQuestion q : this.testQuestions)
        { // Verificar todas as posições da estrutura de dados
            if (q.evaluateAnswer())
            { // Quando encontrar uma pergunta avaliada como correta, incrementa
                count++;
            }
        }
        return count;
    }

    @Override
    public int incorrectAnswer() {
        int count = 0;
        for (IQuestion q : this.testQuestions)
        { // Verificar todas as posições da estrutura de dados
            if (!q.evaluateAnswer())
            { // Quando encontrar uma pergunta avaliada como incorreta, incrementa
                count++;
            }
        }
        return count;
    }

    @Override
    public IQuestion[] incorrectAnswers() {
        int j = this.incorrectAnswer(); // Buscar o número de respostas corretas
        IQuestion[] temp
                = // Instanciar uma estrutura com o tamanho necessário
                new IQuestion[j];

        j = 0;
        for (IQuestion q : testQuestions)
        { // Iterar sobre a estrutura de dados principal
            if (!q.evaluateAnswer())
            { // Adicionar as respostas corretas
                temp[j] = q;
                j++;
            }
        }
        return temp;
    }

    @Override
    public IQuestion[] correctAnswers() {
        int j = this.correctAnswer(); // Buscar o número de respostas corretas
        IQuestion[] temp
                = // Instanciar uma estrutura com o tamanho necessário
                new IQuestion[j];

        j = 0;
        for (IQuestion q : testQuestions)
        { // Iterar sobre a estrutura de dados principal
            if (q.evaluateAnswer())
            { // Adicionar as respostas corretas
                temp[j] = q;
                j++;
            }
        }
        return temp;
    }
}
