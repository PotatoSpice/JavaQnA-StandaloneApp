package models;

public class IQuestionYesNo extends IQuestion implements interfaces.models.IQuestionYesNo {

    private String correct_answer, user_answer;

    @Override
    public String getCorrect_answer(){
        return correct_answer;
    }

    @Override
    public void setCorrect_answer(String var1){
        this.correct_answer=var1;
    };

    @Override
    public String getUser_answer(){
        return user_answer;
    };

    @Override
    public void setUser_answer(String var1){
        this.user_answer=var1;
    };


}
