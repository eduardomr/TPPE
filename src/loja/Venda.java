package loja;

import java.util.Date;

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
    
    public double calcularTotal(metodoPagamento pagamento, String numeroCartao) {
        double total = 0.0;
        for (ItemVenda item : produtos) {
            total += item.getProduto().getValorVenda() * item.getQuantidade();
        }
        total += calculaFrete();
        total -= (total * cliente.getDesconto());

        total += calculaTaxa(total);

        this.cliente.atualizaSaldoCashback(calculaCashback(total, isCartaoLoja(numeroCartao)));
        this.cliente.atualizaTotalComprasMes(total);

        return total;

    }

    public double calcularTotal(metodoPagamento pagamento) {
        double total = 0.0;
        for (ItemVenda item : produtos) {
            total += item.getProduto().getValorVenda() * item.getQuantidade();
        }
        
        total += calculaFrete();
        total -= (total * cliente.getDesconto());

        total += calculaTaxa(total);

        this.cliente.atualizaSaldoCashback(calculaCashback(total,false));
        this.cliente.atualizaTotalComprasMes(total);

        return total - (total * cliente.getDesconto());
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
        if (numeroCartao.startsWith("4296 13")) {
            return true;
        }
        return false;
    }

    public double calculaCashback(double total, boolean cartaoLoja) {
        if (cliente.getTipo() == Cliente.tipoCliente.PRIME) {
            if (cartaoLoja) {
                return total * 0.05;
            } else {
                return total * 0.03;
            }
        }
        return 0.0;
    }
};
