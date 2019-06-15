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
