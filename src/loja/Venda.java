package loja;

import java.util.Date;

import loja.Cliente.TipoCliente;


// Classe Venda modificada
public class Venda {

    ItemVenda[] produtos;
    Cliente cliente;

    public enum metodoPagamento {
        cartao, dinheiro
    };

    metodoPagamento pagamento;
    Date data;
    double frete;
    String numeroCartao;

    public Venda(ItemVenda[] produtos, Cliente cliente, metodoPagamento pagamento, Date data) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.data = data;
        this.frete = cliente.getEndereco().getFrete();
    }

    public Venda(ItemVenda[] produtos, Cliente cliente, metodoPagamento pagamento, Date data, String numeroCartao) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.data = data;
        this.frete = cliente.getEndereco().getFrete();
        this.numeroCartao = numeroCartao;
    }

    public double calcularTotalSemTaxa(metodoPagamento pagamento) {
        double total = 0.0;
        for (ItemVenda item : this.produtos) {
            total += item.getProduto().getValorVenda() * item.getQuantidade();
        }
        total += calculaFrete();

        return total;
    }

  /*   O método calcularTotal na classe Venda agora apenas cria uma instância de CalculadoraTotal e 
    delega o cálculo para ela. Isso mantém a interface da classe inalterada e minimiza a necessidade de 
    modificações no código que usa a classe Venda. */
    public double calcularTotal(metodoPagamento pagamento, String numeroCartao) {
        CalculadoraTotal calculadora = new CalculadoraTotal(this, numeroCartao);
        return calculadora.calcularTotal();
    }

    public double calculaFrete() {
        return this.cliente.getEndereco().getFrete() - this.cliente.getDescontoFrete() * this.cliente.getEndereco().getFrete();
    }

    public boolean isCartaoLoja(String numeroCartao) {
        return numeroCartao.startsWith("4296 13");
    }

    public double calculaCashback(double total, boolean cartaoLoja) {
        if (this.cliente.getTipo() != TipoCliente.PRIME) {
            return 0.0;
        }
        if (cartaoLoja) {
            return 0.05 * total;
        }
        return 0.03 * total;
    }

}
