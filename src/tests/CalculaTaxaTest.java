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

        @Parameters
        public static Collection<Object[]> data() {
            Object[][] resposta = new Object[][] {
                { 100, new Cliente ( "João", new Endereco ("Distrito Federal", true)), 23.0 },
                { 100, new Cliente ( "João", new Endereco ("Regiao Centro-oeste", false)),29.0 },
                { 100, new Cliente ( "João", new Endereco ("Regiao Centro-oeste", true)), 26.0},
                { 100, new Cliente ( "João", new Endereco ("Regiao Nordeste", false)),  34.0},
                { 100, new Cliente ( "João", new Endereco ("Regiao Nordeste", true)),  31.0},
                { 100, new Cliente ( "João", new Endereco ("Regiao Norte", false)),   41.0 },
                { 100, new Cliente ( "João", new Endereco ("Regiao Norte", true)),    36.0},
                { 100, new Cliente ( "João", new Endereco ("Regiao Sudeste", false)), 26.0  },
                { 100, new Cliente ( "João", new Endereco ("Regiao Sudeste", true)),  23.0 },
                { 100, new Cliente ( "João", new Endereco ("Regiao Sul", false)),   29.0  },
                { 100, new Cliente ( "João", new Endereco ("Regiao Sul", true)),    26.0 }
            };
            return Arrays.asList(resposta);
        }
        public void TesteCalculaTaxa(double valor, Cliente cliente, double taxaEsperada) {
            this.valor = valor;
            this.cliente = cliente;
            this.taxaEsperada = taxaEsperada;
    };

    @Test
    public void testCalculaTaxa() {
        
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        ItemVenda item1 = new ItemVenda(produto1, 10);
        ItemVenda[] itens = {item1};

        Venda venda = new Venda(itens, this.cliente, Venda.metodoPagamento.dinheiro, new Date());

        double taxa = venda.calculaTaxa(this.valor);
        assertEquals(taxaEsperada, taxa, 0.1);
    }

}