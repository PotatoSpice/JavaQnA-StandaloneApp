/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package main;

import controller.CompletedTests;
import controller.Test;
import interfaces.exceptions.TestException;
import views.TestWindow;

//Repositório Git: https://github.com/JoseBaltar/TrabalhoPP

public class Main {

    public static void main(String[] args) {
        /*
        Código abaixo serve como exemplo de utilização
         */
        System.out.println("Início de Teste!");
        Test demoTest = new Test();

        /*
        Exemplo de introdução de questões manualmente:
        try {
            // Carregar o teste
            QuestionYesNo q = new QuestionYesNo();
            q.setTitle("pergunta sim e nao");
            q.setQuestion_description("descrição asdasdashdaskjdsahdsakd");
            q.setQuestion_metadata(new QuestionMetadata());
            q.setCorrect_answer("no");
            q.setUser_answer("no");
            //q.setDone(true);

            QuestionMultipleChoice q2 = new QuestionMultipleChoice();
            q2.setTitle("pergunta escolha multipla");
            q2.setQuestion_description("descrição asdasdashdaskjdsahdsakd");
            q2.setQuestion_metadata(new QuestionMetadata());
            q2.setOptions(new String[]
            {
                "a", "b", "c", "d"
            });
            q2.setCorrect_answer("a");
            q2.setUser_answer("b");
            //q2.setDone(true);

            QuestionMultipleChoice q3 = new QuestionMultipleChoice();
            q3.setTitle("pergunta escolha multipla 2");
            q3.setQuestion_description("descrição asdasdashdaskjdsahdsakd");
            q3.setQuestion_metadata(new QuestionMetadata());
            q3.setOptions(new String[]
            {
                "a", "b", "c", "d"
            });
            q3.setCorrect_answer("c");
            q3.setUser_answer("c");
            //q3.setDone(true);

            System.out.println("Question added: " + demoTest.addQuestion(q));
            System.out.println("Question added: " + demoTest.addQuestion(q2));
            System.out.println("Question added: " + demoTest.addQuestion(q3));

            System.out.println("Correct: " + demoTest.getTestStatistics().correctAnswer());

            System.out.println("Number of questions: " + demoTest.numberQuestions());
            System.out.println("teste: " + demoTest.getQuestion(0).getTitle());
            TestStatistics statistics = (TestStatistics) demoTest.getTestStatistics();
            System.out.println("Correct answers: " + statistics.correctAnswer());
            System.out.println("Incorrect answers: " + statistics.incorrectAnswer());
            System.out.println("Correct answers %: " + statistics.correctAnswerPecentage());
            System.out.println("Incorrect answers %: " + statistics.incorrectAnswerPecentage());
            System.out.println("MeanTime answer: " + statistics.meanTimePerAnswer());
            System.out.println("Standard Deviation: " + statistics.standardDeviationTimePerAnsewer());
        } catch (TestException | QuestionException ex)
        {
            System.err.println("Exceção na classe {" + ex.getClass().getName()
                    + "}\nMensagem: " + ex.getMessage());
        } */
        try
        {
            demoTest.loadFromJSONFile("content/teste_A.json");

            // Executar o teste na camada gráfica
            TestWindow t = new TestWindow();

            t.startTest(demoTest);

            // Obter os resultados do teste
            System.out.println("Teste Efetuado!");
            System.out.println(demoTest.toString());
            
            /*
            Demonstração do método de armazenamento do teste em ficheiro de texto.
            O botão 'saveResults' na janela dos resultados não está a funcionar.
            Não sei se nos falhou alguma coisa ou se é mesmo por causa do tal
            problema da interface não ter o método definido, por exemplo.
            */
            demoTest.saveTestResults("content/results/testeA.txt");
          
            /*
            Demonstração do método de armazenamento dos testes concluidos
            num ficheiro json.
            */
            CompletedTests saveTest = new CompletedTests();
            if (demoTest.isComplete()) {
                saveTest.saveCompletedTest("content/completedTests.json", demoTest);
            }
          
        } catch (TestException ex) {
            System.err.println("Problemas na classe {" + ex.getClass().getName()
                    + "}\nMensagem: " + ex.getMessage());
        }
    }

}
