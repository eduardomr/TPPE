package tests;

import junit.framework.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import loja.Cliente;
import loja.Endereco;
import loja.Cliente.TipoCliente;


//Testes Parametrizados
@RunWith(Parameterized.class)
public class ClienteTest extends TestCase {
    private String nome;
    private Endereco endereco;
    private TipoCliente tipo;
    private double descontoEsperado;
    private double descontoFreteEsperado;

    public ClienteTest(String nome, Endereco endereco, TipoCliente tipo, double descontoEsperado, double descontoFreteEsperado){
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.descontoEsperado = descontoEsperado;
        this.descontoFreteEsperado = descontoFreteEsperado;
    }

    @Parameters
    public static Collection<Object[]> data(){
        Endereco endereco = new Endereco("Distrito Federal", true);
        return Arrays.asList(new Object[][]{
            {"João", endereco, TipoCliente.PADRAO, 0.0, 0.0},
            {"Maria", endereco, TipoCliente.ESPECIAL, 0.1, 0.3},
            {"José", endereco, TipoCliente.PRIME, 0.0, 1.0}
        });
    }

    @Test
    public void testCliente() {
        Cliente cliente = new Cliente(nome, endereco, tipo);
        assertEquals( cliente.getDesconto(), descontoEsperado);
        assertEquals(cliente.getDescontoFrete(), descontoFreteEsperado);
    }
}
