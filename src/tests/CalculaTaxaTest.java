package tests;

import java.util.Date;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import loja.*;

@RunWith(Parameterized.class)
public class CalculaTaxaTest {
    private double valor;
    private Cliente cliente;
    private double taxaEsperada;

    public CalculaTaxaTest(double valor, Cliente cliente, double taxaEsperada) {
        this.valor = valor;
        this.cliente = cliente;
        this.taxaEsperada = taxaEsperada;
    };

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 100, new Cliente("João", new Endereco("Distrito Federal", true)), 18.9 },
                { 100, new Cliente("João", new Endereco("Centro Oeste", false)), 18.08 },
                { 100, new Cliente("João", new Endereco("Centro Oeste", true)), 17.6 },
                { 100, new Cliente("João", new Endereco("Nordeste", false)), 18.88 },
                { 100, new Cliente("João", new Endereco("Nordeste", true)), 18.4 },
                { 100, new Cliente("João", new Endereco("Norte", false)), 20.0 },
                { 100, new Cliente("João", new Endereco("Norte", true)), 19.2 },
                { 100, new Cliente("João", new Endereco("Sudeste", false)), 17.6 },
                { 100, new Cliente("João", new Endereco("Sudeste", true)), 17.12 },
                { 100, new Cliente("João", new Endereco("Sul", false)), 18.08 },
                { 100, new Cliente("João", new Endereco("Sul", true)), 17.6 }
        });

    }

    @Test
    public void testCalculaTaxa() {

        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        ItemVenda item1 = new ItemVenda(produto1, 10);
        ItemVenda[] itens = { item1 };

        Venda venda = new Venda(itens, this.cliente, Venda.metodoPagamento.dinheiro, new Date());
        double valor = venda.calcularTotalSemTaxa(Venda.metodoPagamento.dinheiro);
        double taxa = venda.calculaTaxa(valor);
        assertEquals(taxaEsperada, taxa, 0.1);
    }

}