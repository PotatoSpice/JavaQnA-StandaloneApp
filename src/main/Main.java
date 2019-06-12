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

import controller.Test;
import interfaces.exceptions.TestException;
import views.TestWindow;

/**
 *
 * @author Asus
 */
public class Main {

    public static void main(String[] args) {
        /* 
        Código abaixo serve como exemplo de utilização
         */
        System.out.println("Início de Teste!");

        // Carregar o teste
        Test demoTest = new Test();

        // Executar o teste na camada gráfica
        TestWindow t = new TestWindow();
        try {
            t.startTest(demoTest);

            // Obter os resultados do teste
            System.out.println("Teste Efetuado!");
            System.out.println(demoTest.toString());
        } catch (TestException ex) {
            //tratar exceção
        }
    }

}
