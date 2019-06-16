/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package interfaces;

import models.Question;

public interface IExtraStatistics {

    public double percentagemRespostasNumericasCertas();
    public double percentagemRespostasNumericasErradas();

    public double percentagemRespostasSimNaoCertas();
    public double percentagemRespostasSimNaosErradas();

    public double percentagemRespostasMultiplasCertas();
    public double percentagemRespostasMultiplasErradas();

    public int correctAnswer(Question[] question);
    public int incorrectAnswer(Question[] question);

}
