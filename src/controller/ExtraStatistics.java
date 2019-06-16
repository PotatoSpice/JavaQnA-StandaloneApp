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

import interfaces.IExtraStatistics;
import models.Question;
import models.QuestionMultipleChoice;
import models.QuestionNumeric;
import models.QuestionYesNo;

public class ExtraStatistics implements IExtraStatistics {

    private final Question[] testQuestions;
    private int counternum, counteryesno, countermulti;
    private final Question[] multiquestion;
    private final Question[] yesnoquestion;
    private final Question[] numericquestion;


    /**
     * Construtor com a inicialização de {@link #testQuestions}, alvo de
     * análise. Separa os tipos de questão em três arrays diferentes para posterior utilização
     *
     * @param qlist estrutura de dados com as questões para análise estatística
     */
    public ExtraStatistics(Question[] qlist) {
        this.testQuestions = qlist;
        counternum = 0;
        countermulti = 0;
        counteryesno = 0;

        for (Question q : qlist) {
            //Aqui estava a pensar em dividir os tres tipos de questao em tres arrays diferentes para depois calcular em cada um sem necessitar de
            //utlizar 6 ciclos for (um para cada)ficando apenas dois (um para tamanho dos arrays, outro para a sua alimentação

            if (q instanceof QuestionMultipleChoice)
                countermulti++;

            if (q instanceof QuestionNumeric)
                counternum++;

            if (q instanceof QuestionYesNo)
                counteryesno++;

        }

        multiquestion = new Question[countermulti];
        yesnoquestion = new Question[counteryesno];
        numericquestion = new Question[counternum];

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;


        for (Question q : qlist) {
            if (q instanceof QuestionMultipleChoice) {
                multiquestion[c1] = q;
                c1++;
            }

            if (q instanceof QuestionNumeric) {
                numericquestion[c2] = q;
                c2++;
            }

            if (q instanceof QuestionYesNo) {
                numericquestion[c3] = q;
                c3++;

            }
        }


    }

    /**
     * Calcula a percentagem de respostas a perguntas do tipo "Numéricas" cuja resposta do utilizador é acertada
     * @return percentagem de respostas a perguntas do tipo "Numéricas" cuja resposta do utilizador é acertada
     */
    @Override
    public double percentagemRespostasNumericasCertas() {
        double result = ((double) this.correctAnswer(numericquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    /**
     *  Calcula a percentagem de respostas a perguntas do tipo "Numéricas" cuja resposta do utilizador é incorreta
     * @return percentagem de respostas a perguntas do tipo "Numéricas" cuja resposta do utilizador é incorreta
     */
    @Override
    public double percentagemRespostasNumericasErradas() {
        double result = ((double) this.incorrectAnswer(numericquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    /** Calcula a percentagem de respostas a perguntas do tipo "Sim ou não" cuja resposta do utilizador é acertada
     * @return percentagem de respostas a perguntas do tipo "Sim ou não" cuja resposta do utilizador é acertada
     */
    @Override
    public double percentagemRespostasSimNaoCertas() {
        double result = ((double) this.correctAnswer(yesnoquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    /** Calcula a percentagem de respostas a perguntas do tipo "Sim ou não" cuja resposta do utilizador é incorreta
     * @return percentagem de respostas a perguntas do tipo "Sim ou não" cuja resposta do utilizador é incorreta
     */
    @Override
    public double percentagemRespostasSimNaosErradas() {
        double result = ((double) this.incorrectAnswer(yesnoquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    /**
     * Calcula a percentagem de respostas a perguntas do tipo "Escolha Multipla" cuja resposta do utilizador é acertada
     * @return percentagem de respostas a perguntas do tipo "Escolha Multipla" cuja resposta do utilizador é acertada
     */
    @Override
    public double percentagemRespostasMultiplasCertas() {
        double result = ((double) this.correctAnswer(multiquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    /**
     * Calcula a percentagem de respostas a perguntas do tipo "Escolha Multipla" cuja resposta do utilizador é acertada
     * @return percentagem de respostas a perguntas do tipo "Escolha Multipla" cuja resposta do utilizador é incorreta
     */
    @Override
    public double percentagemRespostasMultiplasErradas() {
        double result = ((double) this.incorrectAnswer(multiquestion)
                / (double) testQuestions.length) * 100D;
        return Math.floor(result * 100) / 100;
    }

    @Override
    public int correctAnswer(Question[] questions) {
        int count = 0;
        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : questions)
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
    public int incorrectAnswer(Question[] questions) {
        int count = 0;
        try
        { // Testar a ocorrência de elementos nulos
            for (Question q : questions)
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

}
