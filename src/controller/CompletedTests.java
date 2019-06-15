/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package controller;

import com.google.gson.JsonObject;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na package 'interfaces'. Ou seja, a documentação para cada
 * 'overriden method' encontra-se já especificada na documentação da
 * interface.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 * <p>
 * A abordagem antiga não era a melhor. Uma nova abordagem será escolhida
 * eventualmente.
 */
public class CompletedTests implements interfaces.ICompletedTests {

    /**
     * 
     */
    public CompletedTests() {
        
    }

    @Override
    public boolean loadTestsFromJSONFile(String path) {
        return false;
    }

    @Override
    public boolean saveTestsToJSONFile(String path, ITest t) {

        Test test = (Test) t;
        File pathf = new File(path);

        JsonObject geral = new JsonObject();
        JsonObject teste = new JsonObject();
        JsonObject questoes = new JsonObject();
        //JsonObject info = new JsonObject();

        int qnumber = test.numberQuestions();
        teste.addProperty("Nome_Teste", "Teste"+System.currentTimeMillis());

        for(int ix=0; ix<qnumber; ix++){
            try {
                questoes.addProperty("Question"+ix+1, test.getQuestion(ix).getTitle());
                questoes.addProperty("Answer", String.valueOf(test.getQuestion(ix).evaluateAnswer()));
            } catch (TestException e) {
                e.printStackTrace();
            }
        }

        geral.add("Questoes", questoes);

        teste.add("Questions", questoes);


        try {
            FileWriter writer = new FileWriter(pathf);

            writer.write(teste.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
