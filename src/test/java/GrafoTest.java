import com.cajablanca.grafo.Arco;
import com.cajablanca.grafo.Grafo;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class GrafoTest {

    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    public static Grafo grafoVacio;
    public static Grafo grafoUnNodo;
    public static Grafo grafoUnNodoReflexivo;
    public static Grafo grafoDosNodos;
    public static Grafo grafoTresNodos;
    public static Grafo grafoTresNodosB;
    public static Grafo grafoCuatroNodos;
    public static Grafo grafoCuatroNodosB;

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

        grafoUnNodoReflexivo = new Grafo();
        grafoUnNodoReflexivo.addVertice(1);
        grafoUnNodoReflexivo.addArco(new Arco(1, 1, 1));

        grafoDosNodos = new Grafo();
        grafoDosNodos.addVertice(1);
        grafoDosNodos.addVertice(2);
        grafoDosNodos.addArco(new Arco(1, 2, 1));

        grafoTresNodos = new Grafo();
        grafoTresNodos.addVertice(1);
        grafoTresNodos.addVertice(2);
        grafoTresNodos.addVertice(3);
        grafoTresNodos.addArco(new Arco(1, 2, 1));

        grafoTresNodosB = new Grafo();
        grafoTresNodosB.addVertice(1);
        grafoTresNodosB.addVertice(2);
        grafoTresNodosB.addVertice(3);
        grafoTresNodosB.addArco(new Arco(2, 3, 1));

        grafoCuatroNodos = new Grafo();
        grafoCuatroNodos.addVertice(1);
        grafoCuatroNodos.addVertice(2);
        grafoCuatroNodos.addVertice(3);
        grafoCuatroNodos.addVertice(4);
        grafoCuatroNodos.addArco(new Arco(1, 2, 3));
        grafoCuatroNodos.addArco(new Arco(1, 3, 1));
        grafoCuatroNodos.addArco(new Arco(3, 2, 1));
        grafoCuatroNodos.addArco(new Arco(2, 4, 1));

        grafoCuatroNodosB = new Grafo();
        grafoCuatroNodosB.addVertice(1);
        grafoCuatroNodosB.addVertice(2);
        grafoCuatroNodosB.addVertice(3);
        grafoCuatroNodosB.addVertice(4);
        grafoCuatroNodosB.addArco(new Arco(1, 2, 3));
        grafoCuatroNodosB.addArco(new Arco(1, 3, 1));
        grafoCuatroNodosB.addArco(new Arco(3, 2, 1));

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

    @Test()
    @DisplayName("ComponentsRelated-Grafo vacío")
    public void componentsRelatedGrafoVacio() {
        Assertions.assertEquals(
                "",
                grafoVacio.componentsRelated(),
                "El grafo no debería tener componentes relacionados"
        );
    }

    @Test()
    @DisplayName("ComponentsRelated-Grafo un Nodo")
    public void componentsRelatedUnNodo() {
        Assertions.assertEquals(
                "[1]",
                grafoUnNodo.componentsRelated(),
                "Debería haber dos componentes relacionados"
        );
    }

    @Test()
    @DisplayName("ComponentsRelated-Grafo Reflexivo")
    public void componentsRelatedGrafoReflexivo() {
        Assertions.assertEquals(
                "[1]",
                grafoUnNodoReflexivo.componentsRelated(),
                "Debería haber dos componentes relacionados"
        );
    }

    @Test()
    @DisplayName("ComponentsRelated-Grafo conexo")
    public void componentsRelatedGrafoConexo() {
        Assertions.assertEquals(
                "[1 2]",
                grafoDosNodos.componentsRelated(),
                "Debería haber dos componentes relacionados"
        );
    }

    @Test()
    @DisplayName("BFS-Vertice que no existe")
    public void BFSVerticeErroneo() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> grafoDosNodos.BFS(3),
                "Debería saltar un excepción"
        );
    }

    @Test()
    @DisplayName("BFS-Vertice sin arcos")
    public void BFSVerticeSinArcos() {
        Assertions.assertEquals(
                "[3]",
                grafoTresNodos.BFS(3),
                "No debería de haber arcos"
        );
    }

    @Test()
    @DisplayName("BFS-Vertice con arcos")
    public void BFSVerticeConArcos() {
        Assertions.assertEquals(
                "[1 2]",
                grafoTresNodos.BFS(1),
                "Debería encontrar dos vertices"
        );
    }

    @Test()
    @DisplayName("Kruskal-Un vertice")
    public void kruskalUnVertice() {
        grafoUnNodo.kruskal().printGraph();
        Assertions.assertEquals(
                "",
                outContent.toString(),
                "Debería ser un grafo vacío"
        );
    }

    @Test()
    @DisplayName("Kruskal-Varios arcos segunda condición")
    public void kruskalVariosArcosB() {
        grafoDosNodos.kruskal().printGraph();
        Assertions.assertEquals(
                "1 ——>(1,2,1) \r\n" +
                        "2 ——>(2,1,1) \r\n",
                outContent.toString(),
                "Debería ser un grafo con un arco y dos vertices"
        );
    }

    @Test()
    @DisplayName("Kruskal-BFS Vertice no visitado")
    public void kruskalBFSNoVisitado() {
        grafoCuatroNodos.kruskal().printGraph();
        Assertions.assertEquals(
                "1 ——>(1,3,1) \r\n" +
                        "2 ——>(2,3,1) (2,4,1) \r\n" +
                        "3 ——>(3,1,1) (3,2,1) \r\n" +
                        "4 ——>(4,2,1) \r\n",
                outContent.toString(),
                "Debería ser un grafo con un arco y dos vertices"
        );
    }

    @Test()
    @DisplayName("Kruskal-BFS Vertice visitado")
    // Hay un bucle infinito cuando hay un vertice que no está conectado
    public void kruskalBFSVisitado() {
        grafoCuatroNodosB.kruskal().printGraph();
        Assertions.assertEquals(
                "1 ——>(1,3,1) \r\n" +
                        "2 ——>(2,3,1) (2,4,1) \r\n" +
                        "3 ——>(3,1,1) (3,2,1) \r\n" +
                        "4 ——>\r\n",
                outContent.toString(),
                "Debería ser un grafo con un arco y dos vertices"
        );
    }
}
