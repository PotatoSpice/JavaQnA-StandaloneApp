package models;

import interfaces.exceptions.QuestionException;
import interfaces.models.IQuestionMetadata;

public class IQuestion implements interfaces.models.IQuestion {

    private String title;
    private String description;
    private String user_answer;
    private IQuestionMetadata metadata;
    private boolean done;
    private boolean iscorrect;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String var1) throws QuestionException {
        this.title=var1;
    }

    @Override
    public String getQuestion_description() {
        return description;
    }

    @Override
    public void setQuestion_description(String var1) throws QuestionException {
        this.description=var1;
    }

    @Override
    public IQuestionMetadata getQuestion_metadata() {
        return metadata;
    }

    @Override
    public void setQuestion_metadata(IQuestionMetadata var1) {
        this.metadata= var1;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void setDone(boolean var1) {
        done=var1;
    }

    @Override
    public void answer(String var1) {
        this.user_answer=var1;
    }


    //duvida aqui... O que é suposto retornar mesmo?
    @Override
    public boolean evaluateAnswer() {
        return false;
    }

}
