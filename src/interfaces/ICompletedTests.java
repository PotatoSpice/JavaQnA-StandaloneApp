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

import com.google.gson.JsonElement;
import interfaces.controller.ITest;

/**
 * "Contrato" que especifica quais os métodos que devem estar disponíveis para
 * possibilitar o armazenamento de {@link interfaces.controller.ITest}
 * completados, num ficheiro JSON.
 * <b>Nota:</b> Dependência da library gson-2.8.5.
 */
public interface ICompletedTests {

    /**
     * Carrega todos os {@link interfaces.controller.ITest} armazenados num
     * ficheiro JSON presente no path disponibilizado.
     *
     * @param path caminho para o ficheiro
     * @return Instância de JsonElement com os dados do ficheiro, null se não
     * existirem dados.
     */
    public JsonElement loadTestsFromJSONFile(String path);

    /**
     * Guarda o {@link interfaces.controller.ITest} disponibilizado no ficheiro
     * JSON especificado. Se já existirem dados válidos no ficheiro JSON
     * especificado, ou seja se já existirem testes armazenados, adiciona o
     * teste disponibilizado a esse conjunto de dados.<p>
     * <b>ATENÇÃO:
     * Este método irá substituir o ficheiro apontado pelo 'path' se o conteúdo
     * não estiver no formato especificado!</b>
     *
     * @param path caminho para o ficheiro
     * @param test teste a ser guardado
     * @return true se o {@link interfaces.controller.ITest} foi guardado, false
     * no contrário.
     */
    public boolean saveCompletedTest(String path, ITest test);

    /**
     * Ordena todos os testes realizados pelo número de respostas corretas e
     * armazena-os no ficheiro.
     *
     * @param path caminho para o ficheiro
     * @return Intância de JSONElement com os testes ordenados.
     */
    public JsonElement orderTestsByCorrectAnswers(String path);
}
