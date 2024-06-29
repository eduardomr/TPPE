package tests;

import junit.framework.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import loja.Endereco;

// Testes Parametrizados
@RunWith(Parameterized.class)
public class EnderecoTest extends TestCase {
    private String regiao;
    private boolean capital;
    private double freteEsperado;

    public EnderecoTest(String regiao, boolean capital, double freteEsperado){
        this.regiao = regiao;
        this.capital = capital;
        this.freteEsperado = freteEsperado;
    }

    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
            {"Distrito Federal", true, 5.00},
            {"Centro Oeste", true, 10.00},
            {"Centro Oeste", false, 13.00},
            {"Nordeste", true, 15.00},
            {"Nordeste", false, 18.00},
            {"Norte", true, 20.00},
            {"Norte", false, 25.00},
            {"Sudeste", true, 7.00},
            {"Sudeste", false, 10.00},
            {"Sul", true, 10.00},
            {"Sul", false, 13.00},
            {"", true, 0.00}
        });
    }

    @Test
    public void testEndereco() {
        Endereco endereco = new Endereco(regiao, capital);
        assertEquals(freteEsperado, endereco.getFrete());
    }
}



// Testes unitários realizados com TDD
/* public class EnderecoTest extends TestCase {

    @Test
    public void testEndereco() {
        Endereco endereco = new Endereco("Distrito Federal", true);
        assertEquals(5.00, endereco.getFrete());

        endereco = new Endereco("Centro Oeste", true);
        assertEquals(10.00, endereco.getFrete());

        endereco = new Endereco("Centro Oeste", false);
        assertEquals(13.00, endereco.getFrete());

        endereco = new Endereco("Nordeste", true);
        assertEquals(15.00, endereco.getFrete());

        endereco = new Endereco("Nordeste", false);
        assertEquals(18.00, endereco.getFrete());

        endereco = new Endereco("Norte", true);
        assertEquals(20.00, endereco.getFrete());




    }
} */

