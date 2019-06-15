/*
 * Nome: José Ribeiro Baltar
 * Número: 8170212
 * Turma: Turma 1
 * 
 * Nome: Rodrigo Alexandre Ferreira Coelho
 * Número: 8170282
 * Turma: Turma 1
 */
package interfaces;

import interfaces.controller.ITest;

/**
 * "Contrato" que especifica quais os métodos que devem estar disponíveis para
 * possibilitar o armazenamento de {@link interfaces.controller.ITest}
 * completados, numa estrutura de dados compatível.
 */
public interface ICompletedTests {

    /**
     * Carrega todos os {@link interfaces.controller.ITest} até então
     * concluidos, armazenados num ficheiro JSON.
     *
     * @param path caminho para o ficheiro
     * @return true se o ficheiro foi lido com sucesso e todas os
     * {@link interfaces.controller.ITest} foram carregados, false no contrário
     */
    public boolean loadTestsFromJSONFile(String path);

    /**
     * Guarda todos os {@link interfaces.controller.ITest} contidos na estrutura
     * de dados.
     *
     * @param path caminho para o ficheiro
     * @param test 
     * @return true se foi possível realizar a escrita para o ficheiro de todos
     * os {@link interfaces.controller.ITest}, falso no contrário
     */
    public boolean saveTestsToJSONFile(String path, ITest test);

}
