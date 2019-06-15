package controller;

import interfaces.IExtraStatistics;

public class ExtraStatistics implements IExtraStatistics {

    private final Question[] testQuestions;


    /**
     * Construtor com a inicialização de {@link #testQuestions}, alvo de
     * análise.
     *
     * @param qlist estrutura de dados com as questões para análise estatística
     */
    public ExtraStatistics(Question[] qlist) {
        this.testQuestions = qlist;

        for(Question q: qlist){
            //Aqui estava a pensar em dividir os tres tipos de questao em tres arrays diferentes para depois calcular em cada um sem necessitar de
            //utlizar 6 ciclos for (um para cada)ficando apenas dois (um para tamanho dos arrays, outro para a sua alimentação
        }

    }

    @Override
    public double percentagemRespostasNumericasCertas() {
        return 0;
    }

    @Override
    public double percentagemRespostasNumericasErradas() {
        return 0;
    }

    @Override
    public double percentagemRespostasSimNaoCertas() {
        return 0;
    }

    @Override
    public double percentagemRespostasSimNaosErradas() {
        return 0;
    }

    @Override
    public double percentagemRespostasMultiplasCertas() {
        return 0;
    }

    @Override
    public double percentagemRespostasMultiplasErradas() {
        return 0;
    }
}
