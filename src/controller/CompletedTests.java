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
import exceptions.CompletedTestsException;
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
 * A abordagem aqui seguida passa pela existência de uma estrutura de dados que
 * armazena durante o tempo de execução todos os testes que vão sendo
 * concluidos. Posteriormente, os dados são armazenados num ficheiro JSON para
 * assim garantir a persistência dos dados.
 * <p>
 * NOTA: é possivel verificar que alguns métodos utilizados nesta classe são
 * 'repetidos' da classe 'Test', ou seja, seria possível haver uma forma de
 * reutilizar esses métodos. Uma forma seria através do uso da API
 * 'ContainerOfObjects', no entanto essa abordagem não foi adotada.
 */
public class CompletedTests implements interfaces.ICompletedTests {

    private final ITest[] saved_tests;
    private final int DEFAULT_SIZE = 50;

    /**
     * Construtor para inicialização da estrutura de dados com tamanho inicial
     * por defeito.
     */
    public CompletedTests() {
        this.saved_tests = new ITest[DEFAULT_SIZE];
    }

    @Override
    public boolean loadTestsFromJSONFile(String path) {
        return false;
    }

    @Override
    public boolean saveTestsToJSONFile(String path, controller.ITest test) {

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
