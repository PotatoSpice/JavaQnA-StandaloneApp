package controller;

import interfaces.models.IQuestion;

public class TestStatistics implements interfaces.controller.ITestStatistics {
    @Override
    public double meanTimePerAnswer() {
        return 0;
    }

    @Override
    public double standardDeviationTimePerAnsewer() {
        return 0;
    }

    @Override
    public double correctAnswerPecentage() {
        return 0;
    }

    @Override
    public double incorrectAnswerPecentage() {
        return 0;
    }

    @Override
    public int correctAnswer() {
        return 0;
    }

    @Override
    public int incorrectAnswer() {
        return 0;
    }

    @Override
    public IQuestion[] incorrectAnswers() {
        return new IQuestion[0];
    }

    @Override
    public IQuestion[] correctAnswers() {
        return new IQuestion[0];
    }
}
