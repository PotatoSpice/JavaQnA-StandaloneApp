/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package models;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 */
public class QuestionMetadata implements interfaces.models.IQuestionMetadata {

    private long questionstartt, questionfinisht;

    @Override
    public long getTimestamp_start() {
        return questionstartt;
    }

    @Override
    public void setTimestamp_start(long timestamp_start) {
        this.questionstartt = timestamp_start;

    }

    @Override
    public long getTimestamp_finish() {
        return questionfinisht;
    }

    @Override
    public void setTimestamp_finish(long timestamp_finish) {
        /*
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp_finish = Long.parseLong(String.valueOf(timestamp));
         */
        this.questionfinisht = timestamp_finish;
    }

}
