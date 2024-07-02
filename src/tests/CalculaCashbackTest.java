package tests;

import junit.framework.TestCase;
import loja.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class CalculaCashbackTest extends TestCase {
    private ItemVenda[] produtos;
    private Cliente cliente;
    private double cashbackEsperado;

    public CalculaCashbackTest(ItemVenda[] produtos, Cliente cliente, double cashbackEsperado) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.cashbackEsperado = cashbackEsperado;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        ItemVenda item1 = new ItemVenda(produto1, 1);
        ItemVenda[] produtos = { item1 };

        Endereco endereco = new Endereco("Distrito Federal", true);
        Cliente cliente = new Cliente("José", endereco, Cliente.tipoCliente.PRIME);

        return Arrays.asList(new Object[][] {
            { produtos, cliente, 0.05 * 10.0 }
        });
    }

    @Test
    public void testCalculaCashback() {
        Venda venda = new Venda(produtos, cliente, Venda.metodoPagamento.cartao, new Date());
        double total = venda.calcularTotal(Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX");
        assertEquals(cashbackEsperado, venda.calculaCashback(total, true));
    }
}
