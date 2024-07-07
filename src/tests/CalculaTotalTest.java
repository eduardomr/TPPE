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
public class CalculaTotalTest {
    private ItemVenda[] produtos;
    private Cliente cliente;
    private Venda.metodoPagamento pagamento;
    private String numeroCartao;
    private double totalEsperado;

    public CalculaTotalTest(ItemVenda[] produtos, Cliente cliente, Venda.metodoPagamento pagamento, String numeroCartao,
            double totalEsperado) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.numeroCartao = numeroCartao;
        this.totalEsperado = totalEsperado;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");

        ItemVenda item1 = new ItemVenda(produto1, 1);

        ItemVenda[] produtos1 = { item1 };

        Endereco endereco = new Endereco("Distrito Federal", true);

        Cliente clientePadrao = new Cliente("Ana", endereco, Cliente.tipoCliente.PADRAO);
        Cliente clienteEspecial = new Cliente("Carlos", endereco, Cliente.tipoCliente.ESPECIAL);
        Cliente clientePrime = new Cliente("José", endereco, Cliente.tipoCliente.PRIME);

        return Arrays.asList(new Object[][] {
            // Cliente (padrão / especial / prime) com pagamento em cartão SENDO da empresa
            { produtos1, clientePadrao, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", (10 + (5 * 1)) * 1.18 * 0.9 },
            { produtos1, clienteEspecial, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", (10 + (5 * 0.7)) * 1.18 * 0.9 * 0.9 },
            { produtos1, clientePrime, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", (10 + (5 * 0)) * 1.18 * 0.95 },

            // Cliente (padrão / especial / prime) com pagamento em cartão SEM SER da empresa
            { produtos1, clientePadrao, Venda.metodoPagamento.cartao, "1234 56XX XXXX XXXX", (10 + (5 * 1)) * 1.18 },
            { produtos1, clienteEspecial, Venda.metodoPagamento.cartao, "1234 56XX XXXX XXXX", (10 + (5 * 0.7)) * 1.18 * 0.9 },
            { produtos1, clientePrime, Venda.metodoPagamento.cartao, "1234 56XX XXXX XXXX", (10 + (5 * 0)) * 1.18 * 0.97},

            // Cliente (padrão / especial / prime) com pagamento em dinheiro
            { produtos1, clientePadrao, Venda.metodoPagamento.dinheiro, "", (10 + (5 * 1)) * 1.18 },
            { produtos1, clienteEspecial, Venda.metodoPagamento.dinheiro, "", (10 + (5 * 0.7)) * 1.18 * 0.9 },
            { produtos1, clientePrime, Venda.metodoPagamento.dinheiro, "", (10 + (5 * 0)) * 1.18 * 0.97}
        });
    }

    @Test
    public void testCalcularTotal() {
        Venda venda = new Venda(produtos, cliente, pagamento, new Date(), numeroCartao);
        assertEquals(totalEsperado, venda.calcularTotal(pagamento, numeroCartao), 0.01);
    }
}
