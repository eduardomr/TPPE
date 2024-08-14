// Classe que encapsula o cálculo do total da venda

/*
Esta classe encapsula a lógica específica para o cálculo 
do total da venda, tornando o código mais modular e 
facilitando a manutenção.
*/

package loja;
import loja.Cliente.TipoCliente;

public class CalculadoraTotal {
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

        if (venda.cliente.getTipo() == TipoCliente.PRIME) {
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

        // Usando a nova classe CalculadoraTaxa
        CalculadoraTaxa calculadoraTaxa = new CalculadoraTaxa(venda);
        total += calculadoraTaxa.calculaTaxa(total);

        venda.cliente.atualizaSaldoCashback(venda.calculaCashback(total, isCartaoLoja));
        venda.cliente.atualizaTotalComprasMes(total);

        return total - (total * venda.cliente.getDesconto());
    }
}
