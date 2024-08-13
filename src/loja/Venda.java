package loja;

import java.util.Date;

// Classe que encapsula o cálculo do total da venda
/* Esta classe encapsula a lógica específica para o cálculo 
do total da venda, tornando o código mais modular e 
facilitando a manutenção. */
class CalculadoraTotal {
    private Venda venda;
    private String numeroCartao;
    private boolean isCartaoLoja;

    public CalculadoraTotal(Venda venda, String numeroCartao) {
        this.venda = venda;
        this.numeroCartao = numeroCartao;
        this.isCartaoLoja = venda.isCartaoLoja(numeroCartao);
    }

    public double calcularTotal() {
        double total = venda.calcularTotalSemTaxa(venda.pagamento);

        if (venda.cliente.getTipo() == Cliente.tipoCliente.PRIME) {
            if (isCartaoLoja) {
                total -= total * 0.05;  
            } else {
                total -= total * 0.03;  
            }
        } else {
            if (isCartaoLoja) {
                total -= total * 0.1;  
            }
        }

        total += venda.calculaTaxa(total);
        
      /*   A lógica para aplicar descontos e calcular cashback foi movida para a nova classe, 
        o que reduz a complexidade do método Venda::calcularTotal e melhora a coesão da classe Venda. */
        
        venda.cliente.atualizaSaldoCashback(venda.calculaCashback(total, isCartaoLoja));
        venda.cliente.atualizaTotalComprasMes(total);

        return total - (total * venda.cliente.getDesconto());
    }
}

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
        this.frete = cliente.endereco.getFrete();
    }

    public Venda(ItemVenda[] produtos, Cliente cliente, metodoPagamento pagamento, Date data, String numeroCartao) {
        this.produtos = produtos;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.data = data;
        this.frete = cliente.endereco.getFrete();
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

    public double calculaTaxa(double total) {
        double taxaICMS = 0.0;
        double taxaMunicipal = 0.0;

        if (cliente.endereco.getRegiao().equals("Distrito Federal")) {
            taxaICMS = 0.18;
        } else {
            taxaICMS = 0.12;
            taxaMunicipal = 0.04;
        }
        double totalICMS = total * taxaICMS;
        double totalMunicipal = total * taxaMunicipal;

        return (totalICMS + totalMunicipal);
    }

    public double calculaFrete() {
        return this.cliente.endereco.getFrete() - this.cliente.getDescontoFrete() * this.cliente.endereco.getFrete();
    }

    public boolean isCartaoLoja(String numeroCartao) {
        return numeroCartao.startsWith("4296 13");
    }

    public double calculaCashback(double total, boolean cartaoLoja) {
        if (this.cliente.tipo != Cliente.tipoCliente.PRIME) {
            return 0.0;
        }
        if (cartaoLoja) {
            return 0.05 * total;
        }
        return 0.03 * total;
    }
}
