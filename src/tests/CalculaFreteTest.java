package tests;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import loja.*;

@RunWith(Parameterized.class)

public class CalculaFreteTest{
    private Venda venda;
    private double freteFinal;

    public CalculaFreteTest(Cliente.TipoCliente tipo, double freteFinal) {
    
        Cliente cliente = new Cliente("João", new Endereco("Distrito Federal", true), tipo);
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        Produto produto2 = new Produto("Produto 2", 2, "Descricao 2", 20.0, "unidade");
        Produto produto3 = new Produto("Produto 3", 3, "Descricao 3", 30.0, "unidade");

        ItemVenda item1 = new ItemVenda(produto1, 1);
        ItemVenda item2 = new ItemVenda(produto2, 2);
        ItemVenda item3 = new ItemVenda(produto3, 3);

        ItemVenda[] produtos = { item1, item2, item3 };

        this.venda = new Venda(produtos, cliente, Venda.metodoPagamento.cartao, new Date());
        this.freteFinal = freteFinal;
    }



    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            { Cliente.TipoCliente.ESPECIAL, 3.5 },
            { Cliente.TipoCliente.PRIME, 0.0 },
            { Cliente.TipoCliente.PADRAO, 5.0 }

        });

    }

    @Test
    public void test() {
        assertEquals(freteFinal, venda.calculaFrete(), 0.01);
    }

}
