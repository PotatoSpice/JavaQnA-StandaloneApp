/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import controller.Test;
import interfaces.exceptions.TestException;
import views.TestWindow;

public class Main {

    public static void main(String[] args) {
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
