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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import java.util.Arrays;
import models.Question;
import models.QuestionMultipleChoice;
import models.QuestionNumeric;
import models.QuestionYesNo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <b>Esta classe implementa todos os métodos definidos no contrato relativo,
 * presente na API 'recursos.jar'. Ou seja, a documentação para cada 'overriden
 * method' encontra-se já especificada na documentação da API.</b>
 * Contudo, novos métodos adicionados ou alterações pertinentes serão
 * devidamente documentadas.
 */
public class Test implements interfaces.controller.ITest {

    private final Question[] questions;
    private final int DEFAULT_SIZE = 50;
    private TestStatistics statistics = null;

    /**
     * Construtor para inicialização da estrutura de dados com tamanho inicial
     * por defeito.
     */
    public Test() {
        this.questions = new Question[this.DEFAULT_SIZE];
    }

    @Override
    public boolean addQuestion(IQuestion q) throws TestException {
        if (q == null)
        { // Lança uma exceção se o objeto for nulo
            throw new TestException();
        }

        int pos = 0;
        while (pos < questions.length)
        { // Iterar sobre a estrutura de dados
            if (questions[pos] == null)
            { // Insere o objeto quando encontrar uma posição vazia
                questions[pos] = (Question) q;
                return true;
            }
            pos++;
        }
        return false;
    }

    @Override
    public IQuestion getQuestion(int pos) throws TestException {
        if (pos < questions.length && pos >= 0)
        { // verificar se a posição especificada pode ser verificada
            if (questions[pos] != null)
            { // retorna o objeto se existir
                return questions[pos];
            } else
            { // se o objeto não existe lança uma exceção
                throw new TestException("Posição especificada não contém nenhuma "
                        + "Questão!");
            }
        }
        return null;
    }

    @Override
    public boolean removeQuestion(int pos) {
        if (pos > 0 && pos < questions.length && questions[pos] != null)
        { // verificar se a posição pode ser verificada e se o conteúdo existe
            questions[pos] = null;
            // trim(pos);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeQuestion(IQuestion q) {

        return removeQuestion(findQuestion((Question) q));

    }

    @Override
    public int numberQuestions() {
        int count = 0;
        for (Question q : questions)
        { // Itera sobre toda a estutura de dados
            if (q != null)
            { // Incrementa o contador de cada vez que encontra uma questão
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean isComplete() {
        if (questions.length == 0)
        { // Se não existirem questões, retorna falso
            return false;
        }
        /*
         * NOTA sobre 'object casting': No caso deste método, durante as
         * iterações no ciclo abaixo, uma vez que a classe 'Question' é
         * abstrata, é ímpossível utilizar a sua instância. Então, será
         * necessário realizar o casting para cada uma das questões. Para isso é
         * utilizado o 'instanceof' como forma de fazer essa verificação.
         */
        for (Question q : questions)
        { // Itera sobre a estrutura de dados até que a condição se realize
            if (q instanceof QuestionYesNo)
            {
                QuestionYesNo temp = (QuestionYesNo) q;
                if (!q.isDone())
                { // Ao encontrar uma única questão incompleta, retorna falso
                    return false;
                }
            } else if (q instanceof QuestionMultipleChoice)
            {
                QuestionMultipleChoice temp = (QuestionMultipleChoice) q;
                if (!q.isDone())
                { // Ao encontrar uma única questão incompleta, retorna falso
                    return false;
                }
            } else if (q instanceof QuestionNumeric)
            {
                QuestionNumeric temp = (QuestionNumeric) q;
                if (!q.isDone())
                { // Ao encontrar uma única questão incompleta, retorna falso
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public ITestStatistics getTestStatistics() {
        if (this.statistics == null)
        { // Organizar os dados e criar uma instância para estatísticas
            int num = this.organizeData(0);
            this.statistics = new TestStatistics(this.questions, num);
        }
        return this.statistics;
    }

    @Override
    public boolean loadFromJSONFile(String path) throws TestException {
        Gson gson = new Gson();
        
        Question[] fileQuestions = null; //gson.fromJson(path, Question[].class);



        QuestionMultipleChoice[] multipleChoice = new QuestionMultipleChoice[50];
        int counter1=0;
        QuestionNumeric[] questionNumerics = new QuestionNumeric[50];
        int counter2=0;
        QuestionYesNo[] questionYesNos = new QuestionYesNo[50];
        int counter3=0;


        try {
            FileReader inputFile = new FileReader(path);
            BufferedReader bufferReader = new BufferedReader(inputFile);

            JsonReader reader = new JsonReader(bufferReader);
            reader.beginArray();
            reader.beginObject();
            //Fica aqui ou depois?
            //fileQuestions = gson.fromJson(reader, Question[].class);


            while(reader.hasNext()){
                if(reader.nextName().equals("type")){
                    String type = reader.nextString();

                    if(type.equals("MultipleChoices")){

                    }

                    if(type.equals("YesNo")){

                    }

                    if(type.equals("Numeric")){

                    }
                    /*reader.beginObject();
                    while(reader.hasNext()){
                        if(reader.nextName().equals("question")){
                            while(reader.hasNext()){
                                if(reader.nextName().equals("title")){

                                }

                                if(reader.nextName().equals("question_description")){

                                }

                                if(type.equals("MultipleChoice"))
                                    if(reader.nextName().equals("options")){

                                    }

                                if(reader.nextName().equals("correct_answers")){

                                }

                            }
                        }
                    } */

                }


            }
            reader.endObject();
            reader.endArray();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* try {
            FileReader inputFile = new FileReader(path);
            BufferedReader bufferReader = new BufferedReader(inputFile);
            String line;
            while ((line = bufferReader.readLine()) != null) {

                fileQuestions = gson.fromJson(bufferReader, Question[].class);
                JsonElement element = gson.fromJson(line, JsonElement.class);
                JsonObject jsonObj = element.getAsJsonObject();

            }
            // Close the buffer reader
            bufferReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } */

        if (fileQuestions.length == 0)
        { // Se não existirem questões, retorna falso
            return false;
        }

        for (IQuestion q : fileQuestions)
        { // Iterar sobre o novo vetor criado a partir do ficheiro JSON
            /*
            Nota: como o método 'addQuestion()' já faz o lançamento da exceção
            'TestException()' quando não existe uma questão na posição em 
            específico, não é necessário voltar a instânciar e a lançar
            a mesma exceção.
             */
            if (!addQuestion(q))
            { // Não sendo possível adicionar uma única questão, retorna falso
                return false;
            }
            // Questão adicionada, prossegue para a próxima posição.
        }
        return true;
    }

    /**
     * Override do método por defeito '.toString()' para permitir a definição de
     * um formato personalizado.
     *
     * @return resultados do teste em formato String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Gson gson = new Gson();
        int size = this.organizeData(0);
        for (int i = 0; i < size; i++)
        {
            builder.append("----------------\nQuestion:\n");
            builder.append(gson.toJson(questions[i]));
            builder.append('\n');
        }
        return builder.toString();
    }

    /**
     * Procura e devolve a posição de um elemento em {@link Question}.
     *
     * @param q elemento a ser encontrado
     * @return posição em que se encontra o elemento encontrado ou -1 se não
     * existir
     */
    public int findQuestion(Question q) {
        int pos = 0;
        while (pos < questions.length)
        { // Iterar sobre a estrutura de dados
            if (questions[pos].equals(q))
            { // Se encontrar um objeto igual ao parâmetro, retorna a posição
                return pos;
            }
            // Senão, avança para a próxima posição
            pos++;
        }
        return -1;
    }

    /**
     * Elimina elementos vazios entre cada dois elementos consecutivos, a partir
     * de uma posição inicial. Adicionalmente, retorna o número de elementos não
     * nulos total.<p>
     * <b>Nota:</b> Este método não é muito utilizado dentro da classe. No
     * entanto, pode surgir mais utilidade eventualmente.
     *
     * @param pos posição inicial
     * @return total de elementos não nulos
     */
    public int organizeData(int pos) {
        int i = pos, j, count = 0;
        while (i < questions.length)
        { // Iterar sobre toda a estrutura de dados
            if (questions[i] == null)
            { // Quando encontrar um elemento nulo
                j = i;
                while (j < questions.length - 1 && questions[j] == null)
                { // Arrasta o elemento seguinte para a posição anterior até 
                    // que mais nenhum elemento nulo exista
                    questions[j] = questions[j + 1];
                    j++;
                }
                count++; // Incrementa o contador a cada valor nulo encontrado.
            }
            i++;
        }
        return (i - count) + 1;
    }

    /**
     * Este método deveria estar na interface fornecida pelos recursos, uma vez
     * que está no UML no enunciado. No entanto, não é o caso. (Enviar email aos
     * profs sobre isto)
     *
     * @param path
     */
    void saveTestResults(String path) {

    }
}
