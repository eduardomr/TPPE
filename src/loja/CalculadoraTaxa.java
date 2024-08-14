// Classe que encapsula o cálculo das taxas
package loja;

/*
Esta classe encapsula a lógica específica para o cálculo 
da taxa, tornando o código mais modular e 
facilitando a manutenção.
*/

public class CalculadoraTaxa {
    private Venda venda;

    public CalculadoraTaxa(Venda venda) {
        this.venda = venda;
    }

    public double calculaTaxa(double total) {
        double taxaICMS = 0.0;
        double taxaMunicipal = 0.0;

        if (venda.cliente.getEndereco().getRegiao().equals("Distrito Federal")) {
            taxaICMS = 0.18;
        } else {
            taxaICMS = 0.12;
            taxaMunicipal = 0.04;
        }
        double totalICMS = total * taxaICMS;
        double totalMunicipal = total * taxaMunicipal;

        return (totalICMS + totalMunicipal);
    }
}
