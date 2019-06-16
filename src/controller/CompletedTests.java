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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interfaces.controller.ITest;
import interfaces.exceptions.TestException;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Question;

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
    
    @Override
    public JsonElement loadTestsFromJSONFile(String path) {
        JsonElement json; // JsonElement correspondente ao ficheiro
        try
        { // Leitura do ficheiro e parse para uma instância de JsonElement
            FileReader inputFile = new FileReader(path);

            JsonParser parser = new JsonParser();
            json = parser.parse(inputFile);

        } catch (FileNotFoundException ex)
        { // Retorna null se o ficheiro não existir
            return null;
        }
        
        if (json.isJsonArray() && json.getAsJsonArray().size() == 0)
        { // Se dados forem do tipo JsonArray e não existirem testes, retorna null
            return null;
        }

        return json;
    }

    @Override
    public boolean saveCompletedTest(String path, ITest t) {

        Gson gson = new Gson(); // Instância gson para escrever o ficheiro Json
        Test test = (Test) t; // Type casting para uma instância de Test
        File pathf = new File(path); // Ficheiro de destino
        
        JsonElement file = this.loadTestsFromJSONFile(path); 
        JsonArray testes
                // Se no caminho especificado já existirem dados válidos, adiciona
                // o teste aos dados anteriores, senão cria uma nova instância
                = (file != null && file.isJsonArray() 
                ? file.getAsJsonArray() : new JsonArray());
        
        JsonObject teste 
        // Instância para transformação do teste disponibilizado num objeto JSON
                = new JsonObject();
        JsonArray questoes = new JsonArray();
        //JsonObject info = new JsonObject();

        int qnumber = test.numberQuestions();
        
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'H'HH:mm:ss'Z'z");
        teste.addProperty("ID_Teste", "T" + format.format(date)); // Identificação do teste

        for(int ix=0; ix<qnumber; ix++){
            try {
                Question q = (Question) test.getQuestion(ix);
                // Converter as questões do teste em JsonObjects
                questoes.add(gson.toJsonTree(q));
            } catch (TestException e) {
                System.err.println("[" + e.getClass().getName() + "] "
                + "Questão a adicionar é inválida.");
                return false;
            }
        }

        teste.add("Questions", questoes); // Adicionar as questões ao teste (Json)
        testes.add(teste);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathf))) {
            
            writer.write(gson.toJson(testes));
            writer.flush();

        } catch (IOException ex) {
            System.err.println("[" + ex.getClass().getName() + "] "
                    + "Erro na escrita do ficheiro - path: " + path);
            return false;
        }

        return true;
    }

    @Override
    public JsonElement orderTestsByCorrectAnswers(String path) {
        JsonElement file = this.loadTestsFromJSONFile(path);
        JsonArray testes;
        if (file != null && file.isJsonArray()) 
        { // Se não existirem dados válidos no ficheiro, retorna null
            testes = file.getAsJsonArray();
        } else {
            return null;
        }
        
        /* Ordenar */
        
        return null;
    }
}
