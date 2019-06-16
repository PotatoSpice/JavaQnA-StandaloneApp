package interfaces;


import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;

public interface ITestMultipleChoices extends ITest {

    @Override
    boolean addQuestion(IQuestion iQuestion) throws TestException;
    
}
