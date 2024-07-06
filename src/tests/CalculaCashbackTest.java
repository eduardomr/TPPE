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
    private Venda.metodoPagamento pagamento;
    private double cashbackEsperado;

    public CalculaCashbackTest(ItemVenda[] produtos, Cliente cliente, Venda.metodoPagamento pagamento, double cashbackEsperado) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.cashbackEsperado = cashbackEsperado;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        Produto produto2 = new Produto("Produto 2", 2, "Descricao 2", 20.0, "unidade");

        ItemVenda item1 = new ItemVenda(produto1, 1);
        ItemVenda item2 = new ItemVenda(produto2, 2);

        ItemVenda[] produtos1 = { item1 };
        ItemVenda[] produtos2 = { item1, item2 };

        Endereco endereco = new Endereco("Distrito Federal", true);

        Cliente clientePrime = new Cliente("José", endereco, Cliente.tipoCliente.PRIME);
        Cliente clienteNaoPrime = new Cliente("Carlos", endereco, Cliente.tipoCliente.ESPECIAL);

        return Arrays.asList(new Object[][] {
            // Cliente prime com pagamento em cartão da empresa
            { produtos1, clientePrime, Venda.metodoPagamento.cartao, 0.05 * 10.0 },
            { produtos2, clientePrime, Venda.metodoPagamento.cartao, 0.05 * (10.0 + 2 * 20.0) },

            // Cliente prime com pagamento em dinheiro
            { produtos1, clientePrime, Venda.metodoPagamento.dinheiro, 0.03 * 10.0 },
            { produtos2, clientePrime, Venda.metodoPagamento.dinheiro, 0.03 * (10.0 + 2 * 20.0) },

            // Cliente (não prime) com pagamento em cartão da empresa
            { produtos1, clienteNaoPrime, Venda.metodoPagamento.cartao, 0.0 },
            { produtos2, clienteNaoPrime, Venda.metodoPagamento.cartao, 0.0 },

            // Cliente (não prime) com pagamento em dinheiro
            { produtos1, clienteNaoPrime, Venda.metodoPagamento.dinheiro, 0.0 },
            { produtos2, clienteNaoPrime, Venda.metodoPagamento.dinheiro, 0.0 }
        });
    }

    @Test
    public void testCalculaCashback() {
        Venda venda = new Venda(produtos, cliente, pagamento, new Date());
        double total = venda.calcularTotal(pagamento, pagamento == Venda.metodoPagamento.cartao ? "4296 13XX XXXX XXXX" : "");
        boolean isCartaoEmpresa = pagamento == Venda.metodoPagamento.cartao;
        assertEquals(cashbackEsperado, venda.calculaCashback(total, isCartaoEmpresa), 0.01);
    }
}