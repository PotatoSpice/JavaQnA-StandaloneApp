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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import interfaces.controller.ITestStatistics;
import interfaces.exceptions.TestException;
import interfaces.models.IQuestion;
import java.io.BufferedWriter;
import models.Question;
import models.QuestionMultipleChoice;
import models.QuestionNumeric;
import models.QuestionYesNo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
         * Nota sobre implementação: 
         * O enunciado não fala nisto mas seria conveniente que o método não
         * permitisse que fossem introduzidas questões repetidas. Isso fazia-se
         * facilmente com o código abaixo:
         * if (this.findQuestion(q) != -1) {
         *      return false;
         *      // ou
         *      // throw new TestException();
         * }
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
            if (question != null && !question.isDone())
            { // Ao encontrar uma única questão incompleta, retorna falso
                return false;
            }
        }
        return true;
    }

    @Override
    public ITestStatistics getTestStatistics() {
        try
        { // Testar se existem questões
            if (this.statistics == null)
            { // Organizar os dados e criar uma instância para estatísticas
                this.statistics = new TestStatistics(this.organizeData());
            }
            return this.statistics;
        } catch (TestException ex)
        { // Se não existirem questões, retorna null
            System.out.println(this.getClass().getName() + " - Não existem questões.");
            return null;
        }
    }

    @Override
    public boolean loadFromJSONFile(String path) throws TestException {
        Gson gson = new Gson(); // Instância para desserialização de JsonElements
        JsonArray jsonArray; // JSONArray correspondente ao ficheiro
        try
        { // Leitura do ficheiro e parse para uma instância de JSONArray
            FileReader inputFile = new FileReader(path);

            JsonParser parser = new JsonParser();
            jsonArray = parser.parse(inputFile).getAsJsonArray();

        } catch (FileNotFoundException ex)
        { // Retorna falso se o ficheiro não existir
            System.err.println("ERROR:\n"
                    + "File not found. path: [" + path + "]");
            return false;
        }

        if (jsonArray.size() == 0)
        { // Se não existirem perguntas, retorna falso
            return false;
        }

        Question[] fileQuestions
                = // Armazenamento temporário das perguntas
                new Question[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++)
        { // Iterar sobre todos os elementos do JSONArray
            // cada elemento do JSONArray será um JSONObject
            JsonObject obj = jsonArray.get(i).getAsJsonObject();

            String type = obj.get("type").getAsString(); // Obter o tipo da questão
            Question q;
            switch (type)
            { // Dependendo do tipo da Questão, instancia-se uma classe diferente
                case "MultipleChoice":
                    // desserialização do JsonObject para uma instância da classe
                    q = gson.fromJson(obj.get("question"), QuestionMultipleChoice.class);
                    break;
                case "YesNo":
                    // desserialização do JsonObject para uma instância da classe
                    q = gson.fromJson(obj.get("question"), QuestionYesNo.class);
                    break;
                case "Numeric":
                    // desserialização do JsonObject para uma instância da classe
                    q = gson.fromJson(obj.get("question"), QuestionNumeric.class);
                    break;
                default:
                    q = null;
                    break;
            }
            if (q == null)
            { // Se alguma das questões for inválida, lança uma exceção
                throw new TestException();
            }
            fileQuestions[i] = q;
            // Questão adicionada, prossegue para a próxima posição.
        }

        for (Question q : fileQuestions)
        { // Adicionar as questões à estrutura principal
            addQuestion(q);
        }
        return true;

        /*
        VERSÃO 2 - antiga
        try {
            FileReader inputFile = new FileReader(path);
            BufferedReader bufferReader = new BufferedReader(inputFile);

            JsonReader reader = new JsonReader(bufferReader);
            reader.beginArray();
            reader.beginObject();
            //Fica aqui ou depois?
            //fileQuestions = gson.fromJson(reader, Question[].class);

            while (reader.hasNext()) {
                if (reader.nextName().equals("type")) {
                    String type = reader.nextString();

                    if (type.equals("MultipleChoices")) {

                    }

                    if (type.equals("YesNo")) {

                    }

                    if (type.equals("Numeric")) {

                    }
                    /*
                    reader.beginObject();
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
                    } *
                }
            }
            reader.endObject();
            reader.endArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         *//* 
        VERSÃO 1 - antiga
        try {
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
        } 
         */
    }

    @Override
    public boolean saveTestResults(String path) throws TestException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path)))
        { // Instância para escrita do ficheiro
            System.out.println("\n\n FICHEIRO ESCRITO\n\n"
                    + "pat: " + path);
            String test = this.toString();
            if (test.isEmpty())
            { // Se não existirem questões, lança a exceção
                throw new TestException();
            }
            writer.append(test);
            writer.flush();

        } catch (IOException ex)
        { // Se não for prossível criar o ficheiro, retorna falso
            System.err.println("ERROR in file\n"
                    + "- path: [" + path + "]");
            return false;
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
        try
        { // Testar se existem questões
            String testString = "";

            Question[] data = this.organizeData(); // throws TestException()
            testString += "\n----------------";
            for (Question q : data)
            {
                testString += q.toString();
                testString += "----------------";
            }
            return testString;
        } catch (TestException ex)
        { // Se não existirem questões, retorna uma string vazia
            return "";
        }
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
     * <b>Nota:</b> Este método foi adicionado principalmente por conveniência
     * (por exemplo, na utilização da classe TestStatistics). Uma outra
     * alternativa seria utilizar como estrutura de dados desta classe um array
     * de 'tamanho variável'. No entanto, para isso teriam que ser criados, da
     * mesma forma, outros métodos que fizessem essa mesma gestão. Logo,
     * decidiu-se continuar com esta abordagem.
     *
     * @return estrutura de dados original sem elementos nulos
     * @throws interfaces.exceptions.TestException quando não existem questões
     */
    public Question[] organizeData() throws TestException {
        int i = 0, j, k = 0, questionNum = this.numberQuestions();
        if (questionNum == 0)
        { // Se não existirem questões, lança a exceção
            throw new TestException();
        }

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
}
