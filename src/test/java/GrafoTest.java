import com.cajablanca.grafo.Arco;
import com.cajablanca.grafo.Grafo;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GrafoTest {

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    public static Grafo grafoVacio;
    public static Grafo grafoDosNodos;
    public static Grafo grafoUnNodo;

    @AfterEach
    public void afterEach() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void beforeEach() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        grafoVacio = new Grafo();

        grafoUnNodo = new Grafo();
        grafoUnNodo.addVertice(1);

        grafoDosNodos = new Grafo();
        grafoDosNodos.addVertice(1);
        grafoDosNodos.addVertice(2);
        grafoDosNodos.addArco(new Arco(1, 2, 1));
    }

    @Test()
    @DisplayName("PrintListaAdyacentes-Lista vacía")
    public void printListaAdyacentesVacia() {
        grafoUnNodo.printListaAdyacentes(1);
        Assertions.assertEquals(
                "Adyacentes de 1: \r\n",
                outContent.toString(),
                "La salida debería coincidir"
        );
    }

    @Test()
    @DisplayName("PrintListaAdyacentes-Lista con un arco")
    public void printListaAdyacentesUnArco() {
        grafoDosNodos.printListaAdyacentes(1);
        Assertions.assertEquals(
                "Adyacentes de 1: 2 \r\n",
                outContent.toString(),
                "La salida debería coincidir"
        );
    }

    @Test()
    @DisplayName("TodosVisitados-Lista adyacencia vacia")
    public void todosVisitadosListaVacia() {
        Assertions.assertTrue(
                grafoUnNodo.todosVisitados(),
                "Debería no haber arcos"
        );
    }

    @Test()
    @DisplayName("TodosVisitados-False")
    public void todosVisitadosFalse() {
        Assertions.assertFalse(
                grafoUnNodo.todosVisitados(),
                "Debería haber arcos y estar visitados"
        );
    }

    @Test()
    @DisplayName("TodosVisitados-True")
    public void todosVisitadosTrue() {
        Assertions.assertTrue(
                grafoUnNodo.todosVisitados(),
                "Debería haber arcos y no estar visitados"
        );
    }
}
