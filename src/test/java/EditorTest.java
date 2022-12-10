import com.cajablanca.editor.Editor;
import com.cajanegra.AbstractSingleLinkedListImpl;
import com.cajanegra.EmptyCollectionException;
import com.cajanegra.SingleLinkedListImpl;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class EditorTest extends TestCase {

    private static Editor editorVacio;
    private static Editor editor;
    private static Editor editorTamano0;
    private static Editor editorTamano1;
    private static Editor editorTamano2;
    private static Editor editor1Palabra;
    private static Editor editor2Palabras;
    private static Editor editor2Palabras2;

    @BeforeEach()
    public void beforeEach() {
        editorVacio = new Editor();
        editor = new Editor();
        editor.cargarEditor("./pom.xml");
        editorTamano0 = new Editor();
        editorTamano0.cargarEditor("./src/test/resources/tamaño0.txt");
        editorTamano1 = new Editor();
        editorTamano1.cargarEditor("./src/test/resources/tamaño1.txt");
        editorTamano2 = new Editor();
        editorTamano2.cargarEditor("./src/test/resources/tamaño2.txt");
        editor1Palabra = new Editor();
        editor1Palabra.cargarEditor("./src/test/resources/Hola.txt");
        editor2Palabras = new Editor();
        editor2Palabras.cargarEditor("./src/test/resources/2Palabras.txt");
        editor2Palabras2 = new Editor();
        editor2Palabras2.cargarEditor("./src/test/resources/2Palabras2.txt");
    }

    @Test()
    @DisplayName("GetLinea-Editor Vacio")
    public void getLineaEditorVacio() {
        Assertions.assertThrows(
                EmptyCollectionException.class,
                () -> editorVacio.getLinea(2),
                "Debería dar una excepción"
        );
    }

    @Test()
    @DisplayName("GetLinea-Linea negativa")
    public void getLineaNegativa() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> editor.getLinea(-1),
                "Debería dar una excepción"
        );
    }

    @Test()
    @DisplayName("GetLinea-Linea mayor que size")
    public void getLineaMayor() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> editor.getLinea(80),
                "Debería dar una excepción"
        );
    }

    @Test()
    @DisplayName("GetLinea-Correcto")
    public void getLinea() throws EmptyCollectionException {
        AbstractSingleLinkedListImpl<String> list = new SingleLinkedListImpl<>();
        list.addFirst("project");
        list.addLast("xmlnshttpmavenapacheorgPOM400");
        Assertions.assertEquals(list.toString(), editor.getLinea(1).toString(), "La línea debería coincidir");
    }

    @Test()
    @DisplayName("NumApariciones-InicioErróneo")
    public void numAparicionesInicioErroneo() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> editor.numApariciones(0, 2, "dependency"),
                "El inicio debería ser erróneo"
        );
    }

    @Test()
    @DisplayName("NumApariciones-FinErróneo")
    public void numAparicionesFinErroneo() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> editor.numApariciones(1, 100, "dependency"),
                "El fin debería ser erróneo"
        );
    }

    @Test()
    @DisplayName("NumApariciones-NumLineas")
    public void numAparicionesNumLineas() {
        Assertions.assertEquals(
                0,
                editorTamano0.numApariciones(2, -1, "hola"),
                "Debería no entrar a los bucles"
        );
    }

    @Test()
    @DisplayName("NumApariciones-Inicio mayor que fin")
    public void numAparicionesInicioMayorFin() {
        Assertions.assertEquals(
                0,
                editor1Palabra.numApariciones(2, -1, "hola"),
                "Debería no entrar a los bucles"
        );
    }

    @Test()
    @DisplayName("NumApariciones-SegundoWhile")
    public void numAparicionesSegundoWhile() {
        Assertions.assertEquals(
                0,
                editorTamano1.numApariciones(1, 1, "hola"),
                "La primera línea debería ser una lista vacía"
        );
    }

    @Test()
    @DisplayName("NumApariciones-SinApariciones")
    public void numAparicionesSinApariciones() {
        Assertions.assertEquals(
                0,
                editor1Palabra.numApariciones(1, 1, "adios"),
                "No debería encontrar la palabra"
        );
    }

    @Test()
    @DisplayName("NumApariciones-ConApariciones")
    public void numAparicionesConApariciones() {
        Assertions.assertEquals(
                1,
                editor1Palabra.numApariciones(1, 1, "hola"),
                "No debería encontrar la palabra"
        );
    }


    @Test()
    @DisplayName("NumPalabras-Empty")
    public void numPalabrasEmpty() {
        Assertions.assertThrows(
                EmptyCollectionException.class,
                () -> editorVacio.numPalabras(),
                "El editor debería estar vacío"
        );
    }

//    @Test()
//    @DisplayName("NumPalabras-Tamaño0")
//    public void numPalabrasTamanio0() throws EmptyCollectionException {
//        Assertions.assertEquals(
//                0,
//                editorTamano0.numPalabras(),
//                "El editor debería tener tamaño 0"
//        );
//    }

    @Test()
    @DisplayName("NumPalabras-Tamaño1LineaVacia")
    public void numPalabrasTamano1LineaVacia() throws EmptyCollectionException {
        Assertions.assertEquals(
                0,
                editorTamano1.numPalabras(),
                "El fichero debería tener una fila y estar vacía"
        );
    }

    @Test()
    @DisplayName("NumPalabras-Completo")
    public void numPalabrasCompleto() throws EmptyCollectionException {
        Assertions.assertEquals(
                1,
                editor1Palabra.numPalabras(),
                "El editor solo debería tener una palabra"
        );
    }

    @Test()
    @DisplayName("PalabraMasLarga-EditorVacio")
    public void palabraMasLargaEditorVacio() throws EmptyCollectionException {
        Assertions.assertNull(
                editorVacio.palabraMasLarga(),
                "Debería devolver null"
        );
    }

    @Test()
    @DisplayName("PalabraMasLarga-LíneaVacia")
    public void palabraMasLargaLineaVacia() throws EmptyCollectionException {
        Assertions.assertNull(
                editorTamano1.palabraMasLarga(),
                "Debería devolver null"
        );
    }

    @Test()
    @DisplayName("PalabraMasLarga-Encuentra 1 palabra solo")
    public void palabraMasLargaInicializaMayor() throws EmptyCollectionException {
        Assertions.assertEquals(
                "hola",
                editor1Palabra.palabraMasLarga(),
                "debería encontrar la palabra \"hola\""
        );
    }

    @Test()
    @DisplayName("PalabraMasLarga-Encuentra 1 palabra y después son menores")
    public void palabraMasLargaPrimero() throws EmptyCollectionException {
        Assertions.assertEquals(
                "hola",
                editor2Palabras.palabraMasLarga(),
                "debería encontrar la palabra \"hola\""
        );
    }

    @Test()
    @DisplayName("PalabraMasLarga-Encuentra varias palabras")
    public void palabraMasLargaCompleto() throws EmptyCollectionException {
        Assertions.assertEquals(
                "buenas",
                editor2Palabras2.palabraMasLarga(),
                "Debería encontrar la palabra \"buenas\""
        );
    }

    @Test()
    @DisplayName("ExistePalabra-Editor Vacio")
    public void existePalabraEditorVacio() {
        Assertions.assertFalse(
                editorVacio.existePalabra("Hola"),
                "No debería en haber ninguna palabra en el editor"
        );
    }

    @Test()
    @DisplayName("ExistePalabra-Línea Vacia")
    public void existePalabraUnaLinea() {
        Assertions.assertFalse(
                editorTamano1.existePalabra("Hola"),
                "La única línea debería estar vacía"
        );
    }

    @Test()
    @DisplayName("ExistePalabra-Una palabra diferente")
    public void existePalabraUnaPalabra() {
        Assertions.assertFalse(
                editor1Palabra.existePalabra("Adios"),
                "No debería encontrar una palabra"
        );
    }

    @Test()
    @DisplayName("ExistePalabra-Una palabra igual")
    public void existePalabraUnaPalabraIgual() {
        Assertions.assertTrue(
                editor1Palabra.existePalabra("hola"),
                "Debería encontrar una palabra"
        );
    }

    @Test()
    @DisplayName("SustituirPalabra-Editor vacío")
    public void sustituirPalabraEditorVacio() {
        Editor expected = editorVacio;
        editorVacio.sustituirPalabra("hola", "buenas");
        Assertions.assertEquals(
                expected,
                editorVacio,
                "Los editores deberían ser iguales"
        );
    }

    @Test()
    @DisplayName("SustituirPalabra-Editor Línea Vacía")
    public void sustituirPalabraUnaLinea() {
        Editor expected = editorTamano1;
        editorTamano1.sustituirPalabra("hola", "buenas");
        Assertions.assertEquals(
                expected,
                editorTamano1,
                "Los editores deberían ser iguales"
        );
    }

    @Test()
    @DisplayName("SustituirPalabra-Editor dos líneas")
    public void sustituirPalabraDosLineas() {
        Editor expected = editorTamano2;
        editorTamano2.sustituirPalabra("hola", "buenas");
        Assertions.assertEquals(
                expected,
                editorTamano2,
                "Los editores deberían ser iguales"
        );
    }

    @Test()
    @DisplayName("SustituirPalabra-Sustituir una palabra")
    public void sustituirPalabraSust1Pal() throws EmptyCollectionException {
        editor1Palabra.sustituirPalabra("hola", "buenas");
        Assertions.assertEquals(
                "buenas",
                editor1Palabra.getLinea(1).getAtPos(1),
                "Debería haber sustituido una palabra"
        );
    }

    @Test()
    @DisplayName("SustituirPalabra-No sustituir una palabra")
    public void sustituirPalabraNoSustituir() throws EmptyCollectionException {
        Editor expected = editor1Palabra;
        editorTamano2.sustituirPalabra("buenas", "adios");
        Assertions.assertEquals(
                "hola",
                editor1Palabra.getLinea(1).getAtPos(1),
                "Los editores deberían ser iguales"
        );
    }
}
