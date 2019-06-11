package models;

public class IQuestionNumeric extends IQuestion implements interfaces.models.IQuestionNumeric {

    private double correct_answer, user_answer;

    @Override
    public double getCorrect_anwser(){
        return correct_answer;
    };

    @Override
    public void setCorrect_anwser(double var1){
        this.correct_answer=var1;
    };

    @Override
    public double getUser_answer(){
        return user_answer;
    };

    @Override
    public void setUser_answer(double var1){
        this.user_answer=var1;
    };
}
