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
    private Venda venda;
    private double totalEsperado;

    public CalculaTotalTest(ItemVenda[] produtos, Cliente cliente, Venda.metodoPagamento pagamento, String numeroCartao,
            double totalEsperado) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.numeroCartao = numeroCartao;
        this.venda = new Venda(produtos, cliente, pagamento, new Date());
        this.totalEsperado = totalEsperado;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        Produto produto2 = new Produto("Produto 2", 2, "Descricao 2", 20.0, "unidade");
        Produto produto3 = new Produto("Produto 3", 3, "Descricao 3", 30.0, "unidade");
        Produto produto4 = new Produto("Produto 4", 4, "Descricao 4", 40.0, "unidade");
        Produto produto5 = new Produto("Produto 5", 5, "Descricao 5", 50.0, "unidade");

        ItemVenda item1 = new ItemVenda(produto1, 1);
        ItemVenda item2 = new ItemVenda(produto2, 2);
        ItemVenda item3 = new ItemVenda(produto3, 3);
        ItemVenda item4 = new ItemVenda(produto4, 4);
        ItemVenda item5 = new ItemVenda(produto5, 5);

        ItemVenda[] produtos1 = { item1 };
        ItemVenda[] produtos2 = { item1, item2 };
        ItemVenda[] produtos3 = { item1, item2, item3 };
        ItemVenda[] produtos4 = { item1, item2, item3, item4 };
        ItemVenda[] produtos5 = { item1, item2, item3, item4, item5 };

        Endereco endereco = new Endereco("Distrito Federal", true);
        Endereco enderecoOutros = new Endereco("São Paulo", false);
        
        Cliente cliente1 = new Cliente("João", endereco, Cliente.tipoCliente.PADRAO);
        Cliente cliente2 = new Cliente("Lanna", endereco, Cliente.tipoCliente.ESPECIAL);
        Cliente cliente3 = new Cliente("José", endereco, Cliente.tipoCliente.PRIME);
        Cliente cliente4 = new Cliente("Maria", enderecoOutros, Cliente.tipoCliente.PADRAO);
        Cliente cliente5 = new Cliente("Carlos", enderecoOutros, Cliente.tipoCliente.ESPECIAL);
        Cliente cliente6 = new Cliente("Ana", enderecoOutros, Cliente.tipoCliente.PRIME);

        return Arrays.asList(new Object[][] {

            // Cliente padrão no DF com pagamento em dinheiro
            { produtos1, cliente1, Venda.metodoPagamento.dinheiro, "", 17.7 },
            { produtos2, cliente1, Venda.metodoPagamento.dinheiro, "", 64.9 },
            
            // Cliente especial no DF com pagamento em dinheiro
            { produtos1, cliente2, Venda.metodoPagamento.dinheiro, "", 14.33 },
            { produtos3, cliente2, Venda.metodoPagamento.dinheiro, "", 152.4 },

            // Cliente prime no DF com pagamento em cartão da empresa
            { produtos1, cliente3, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", 11.8 },
            { produtos4, cliente3, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", 354.0 },

            // Cliente padrão fora do DF com pagamento em dinheiro
            { produtos1, cliente4, Venda.metodoPagamento.dinheiro, "", 11.6 },
            { produtos5, cliente4, Venda.metodoPagamento.dinheiro, "", 638.0 },

            // Cliente especial fora do DF com pagamento em cartão da empresa
            { produtos1, cliente5, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", 10.44 },
            { produtos3, cliente5, Venda.metodoPagamento.cartao, "4296 13XX XXXX XXXX", 146.16 },

            // Cliente prime fora do DF com pagamento em dinheiro
            { produtos1, cliente6, Venda.metodoPagamento.dinheiro, "", 11.6 },
            { produtos4, cliente6, Venda.metodoPagamento.dinheiro, "", 348.0 },

            // Cliente especial no DF com pagamento em cartão diferente da empresa
            { produtos2, cliente2, Venda.metodoPagamento.cartao, "1234 5678 9101 1121", 56.81 },
            
            // Cliente prime fora do DF com pagamento em cartão diferente da empresa
            { produtos3, cliente6, Venda.metodoPagamento.cartao, "1234 5678 9101 1121", 146.16 }
        });
    }

    @Test
    public void testCalcularTotal() {
        assertEquals(totalEsperado, venda.calcularTotal(pagamento, numeroCartao), 0.01);
    }
}
