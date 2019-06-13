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
import models.Question;

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

    private Question[] questions;
    private int data_size = 50;
    private TestStatistics statistics = null;

    /**
     * Construtor para inicialização da estrutura de dados com tamanho inicial
     * por defeito.
     */
    public Test() {
        this.questions = new Question[this.data_size];
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
        if (pos >= questions.length || pos < 0)
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

        return removeQuestion(findQuestion( (Question) q));

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
        for (Question q : questions)
        { // Itera sobre a estrutura de dados até que a condição se realize
            if (!q.isDone())
            { // Ao encontrar uma única questão incompleta, retorna falso
                return false;
            }
        }
        return true;
    }

    @Override
    public TestStatistics getTestStatistics() {
        return this.statistics = new TestStatistics(this.questions);
    }

    @Override
    public boolean loadFromJSONFile(String path) throws TestException {
        Gson gson = new Gson();
        Question[] fileQuestions = null; //gson.fromJson(path, Question[].class);

        try {
            FileReader inputFile = new FileReader(path);
            BufferedReader bufferReader = new BufferedReader(inputFile);

            JsonReader reader = new JsonReader(bufferReader);
            reader.beginArray();
            reader.beginObject();
            //Fica aqui ou depois?
            //fileQuestions = gson.fromJson(reader, Question[].class);

            while(reader.hasNext()){



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

        for (Question q : fileQuestions)
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
        for (IQuestion q : this.questions)
        {
            builder.append("----------------\nQuestion:\n");
            builder.append(gson.toJson(q));
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
     * de uma posição inicial.<p>
     * <b>Nota:</b> Este método não está a ser utilizado devido a como a
     * estrutura de dados está a ser tratada. Isto é, de tal forma que não
     * necessário o seu uso.
     *
     * @param pos posição inicial
     */
    public void trimData(int pos) {
        int i = pos, j;
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
            }
            i++;
        }
    }
}
