package models;


import java.sql.Timestamp;

public class IQuestionMetadata implements interfaces.models.IQuestionMetadata {

    private long questionstartt;
    private long questionfinisht;

    @Override
    public long getTimestamp_start() {

        return questionstartt;


    }

    @Override
    public void setTimestamp_start(long var1){

       Timestamp timestamp = new Timestamp(System.currentTimeMillis());
       var1=Long.parseLong(String.valueOf(timestamp));

       this.questionstartt=var1;

    };

    @Override
    public long getTimestamp_finish(){
        return questionfinisht;
    };

    @Override
    public void setTimestamp_finish(long var1){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        var1=Long.parseLong(String.valueOf(timestamp));

        this.questionfinisht=var1;

    };

}
