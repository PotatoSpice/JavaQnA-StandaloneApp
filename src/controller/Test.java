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
import com.google.gson.stream.JsonReader;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
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
 * {@link interfaces.controller.ITest}.</b>
 * Ou seja, a documentação para cada 'overriden method' encontra-se já
 * especificada na documentação da API. Contudo, novos métodos adicionados ou
 * alterações pertinentes serão devidamente documentadas.
 */
public class Test implements interfaces.controller.ITest {

    private final Question[] questions;
    private final int DEFAULT_SIZE = 50;
    private TestStatistics statistics = null;

    /**
     * Construtor para inicialização de {@link #questions} com tamanho inicial
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
        /*
        Nota sobre implementação: 
        O enunciado não fala nisto mas seria conveniente que o método não
        permitisse que fossem introduzidas questões repetidas. Isso fazia-se
        facilmente com o código abaixo:
        if (this.findQuestion(q) != -1) {
            return false;
            // ou
            // throw new TestException();
        }
         */
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
        if (pos < questions.length && pos >= 0 && questions[pos] != null)
        { // verificar se a posição pode ser verificada e se o conteúdo existe
            questions[pos] = null;
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

        for (Question question : questions)
        { // Itera sobre a estrutura de dados até que a condição se realize
            if (!question.isDone())
            { // Ao encontrar uma única questão incompleta, retorna falso
                return false;
            }
        }
        return true;
    }

    @Override
    public ITestStatistics getTestStatistics() {
        if (this.statistics == null)
        { // Organizar os dados e criar uma instância para estatísticas
            this.statistics = new TestStatistics(this.organizeData());
        }
        return this.statistics;
    }

    @Override
    public boolean loadFromJSONFile(String path) throws TestException {
        Gson gson = new Gson();

        Question[] fileQuestions = null; //gson.fromJson(path, Question[].class);

        QuestionMultipleChoice[] multipleChoice = new QuestionMultipleChoice[50];
        int counter1 = 0;
        QuestionNumeric[] questionNumerics = new QuestionNumeric[50];
        int counter2 = 0;
        QuestionYesNo[] questionYesNos = new QuestionYesNo[50];
        int counter3 = 0;

        try
        {
            FileReader inputFile = new FileReader(path);
            BufferedReader bufferReader = new BufferedReader(inputFile);

            JsonReader reader = new JsonReader(bufferReader);
            reader.beginArray();
            reader.beginObject();
            //Fica aqui ou depois?
            //fileQuestions = gson.fromJson(reader, Question[].class);

            while (reader.hasNext())
            {
                if (reader.nextName().equals("type"))
                {
                    String type = reader.nextString();

                    if (type.equals("MultipleChoices"))
                    {

                    }

                    if (type.equals("YesNo"))
                    {

                    }

                    if (type.equals("Numeric"))
                    {

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

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
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
     * Override do método por defeito {@link #toString()} para permitir a
     * definição de um formato personalizado.
     *
     * @return resultados do teste em formato String
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Gson gson = new Gson();
        Question[] data = this.organizeData();
        builder.append("\n----------------");
        for (Question q : data)
        {
            if (q instanceof QuestionYesNo)
            {
                builder.append("\nQuestão de Sim ou Nao:\n");
                builder.append(gson.toJson(q, QuestionYesNo.class));
            } else if (q instanceof QuestionMultipleChoice)
            {
                builder.append("\nQuestão de Escolha Multipla:\n");
                builder.append(gson.toJson(q, QuestionMultipleChoice.class));
            } else if (q instanceof QuestionNumeric)
            {
                builder.append("\nQuestão Numerica:\n");
                builder.append(gson.toJson(q, QuestionNumeric.class));
            }
            builder.append("\n----------------");
        }
        return builder.toString();
    }

    /**
     * Procura e devolve a posição de um elemento em {@link #questions}.
     *
     * @param q elemento a ser encontrado
     * @return posição em que se encontra o elemento encontrado ou -1 se não
     * existir
     */
    public int findQuestion(IQuestion q) {
        int pos = 0;
        while (pos < questions.length)
        { // Iterar sobre a estrutura de dados, em valores não nulos
            if (questions[pos] != null && questions[pos].equals((Question) q))
            { // Se encontrar um objeto igual ao parâmetro, retorna a posição
                return pos;
            }
            // Senão, avança para a próxima posição
            pos++;
        }
        return -1;
    }

    /**
     * Elimina elementos vazios entre cada dois elementos consecutivos em
     * {@link #questions}.
     * <p>
     * Adicionalmente, retorna uma versão organizada, sem elementos nulos e só
     * com o tamanho necessário, de {@link #questions}.
     * <p>
     * <b>Nota:</b> Este método foi adicionado principalmente por conveniência.
     * Uma outra alternativa seria utilizar como estrutura de dados desta classe
     * um array de 'tamanho variável'. No entanto, para isso teriam que ser
     * criados, da mesma forma, outros métodos que fizessem essa mesma gestão.
     * Logo, decidiu-se continuar com esta abordagem.
     *
     * @return estrutura de dados original sem elementos nulos
     */
    public Question[] organizeData() {
        int i = 0, j, k = 0, questionNum = this.numberQuestions();
        Question[] temp = new Question[questionNum];
        while (i < questionNum)
        { // Tendo em conta que os ciclos abaixo irão compactar todas as respostas
            // não nulas, só será necessário iterar sobre o número dessas respostas
            while (questions[i] == null)
            { // Enquanto um elemento encontrado for nulo...
                j = i;
                while (j < questions.length - 1 && questions[j] == null)
                { // Arrasta o elemento seguinte para a posição anterior até 
                    // encontrar um elemento não nulo
                    questions[j] = questions[j + 1];
                    j++;
                }
                if (questions[j - 1].equals(questions[j]))
                {
                    questions[j] = null; // O último valor é colocado a null,
                    // o penúltimo (j - 1) e último (j) elementos se repetem.
                }
            }
            temp[k] = questions[i];
            k++; // Incrementar índice da nova estrutura
            i++; // Incrementar índice da estrutura original
        }
        return temp;
    }

    @Override
    public boolean saveTestResults(String path) throws TestException {
        /*
        Falta implementar este método.
        Ter cuidado com o uso da exceção. Como diz no javadoc 
        "Throws:
        TestException - if there is no question at the specified position"
        Neste caso, é igual a como está no método this.getQuestion().
         */
        return false;
    }
}
