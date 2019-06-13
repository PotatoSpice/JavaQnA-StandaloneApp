package controller;

import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;

public class Test implements interfaces.controller.ITest {

    @Override
    public boolean addQuestion(IQuestion var1) throws TestException {
        return false;
    }

    @Override
    public IQuestion getQuestion(int var1) throws TestException {
        return null;
    }

    @Override
    public boolean removeQuestion(int var1) {
        return false;
    }

    @Override
    public boolean removeQuestion(IQuestion var1) {
        return false;
    }

    @Override
    public int numberQuestions() {
        return 0;
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public ITestStatistics getTestStatistics() {
        return null;
    }

    @Override
    public boolean loadFromJSONFile(String var1) throws TestException {
        return false;
    }
}
