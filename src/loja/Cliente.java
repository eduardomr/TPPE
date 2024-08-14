package loja;

public class Cliente {
    private int id;
    private String nome;
    public enum TipoCliente {PADRAO, ESPECIAL, PRIME};
    private Endereco endereco;
    private StatusCliente status;

    public Cliente(String nome, Endereco endereco) {
        this(nome, endereco, TipoCliente.PADRAO);
    }

    public Cliente(String nome, Endereco endereco, TipoCliente tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.status = new StatusCliente(tipo);
    }

    public double getDesconto() {
        return this.status.getDesconto();
    }

    public TipoCliente getTipo() {
        return this.status.getTipo();
    }

    public double getDescontoFrete() {
        return this.status.getDescontoFrete();
    }

    public void atualizaTotalComprasMes(double valorComprado) {
        this.status.atualizaTotalComprasMes(valorComprado);
    }

    public void atualizaSaldoCashback(double saldoCashback) {
        this.status.atualizaSaldoCashback(saldoCashback);
    }

    public Endereco getEndereco() {
        return endereco;
    }
}

/*
Benefícios da refatoração

Melhora na Coesão: A classe Cliente agora tem uma única responsabilidade: representar um cliente, enquanto a classe StatusCliente lida com o status do cliente.
Facilidade de Manutenção: Com as responsabilidades separadas, qualquer alteração na lógica de cálculo de descontos, fretes, ou saldo de cashback será feita na classe StatusCliente, tornando a manutenção mais fácil e menos propensa a erros.
Reutilização: A classe StatusCliente pode ser reutilizada em outros contextos onde a lógica de status do cliente seja necessária.
*/