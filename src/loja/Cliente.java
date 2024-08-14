package loja;

public class Cliente {
    private int id;
    private String nome;
    public enum TipoCliente {PADRAO, ESPECIAL, PRIME};
    private TipoCliente tipo;
    private Endereco endereco;
    private double saldoCashback;
    private double totalComprasMensal;

    public Cliente(String nome, Endereco endereco) {
        this(nome, endereco, TipoCliente.PADRAO);
    }

    public Cliente(String nome, Endereco endereco, TipoCliente tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.saldoCashback = 0;
        this.totalComprasMensal = 0;
        this.tipo = tipo;
    }

    public double getDesconto() {
        switch (this.tipo) {
            case ESPECIAL:
                return 0.1;
            case PRIME:
                return 0.0;
            default:
                return 0.0;
        }
    }

    public TipoCliente getTipo() {
        return this.tipo;
    }

    public double getDescontoFrete() {
        switch (this.tipo) {
            case ESPECIAL:
                return 0.3;
            case PRIME:
                return 1.0;
            default:
                return 0.0;
        }
    }

    public void atualizaTotalComprasMes(double valorComprado) {
        this.totalComprasMensal += valorComprado;
        if (this.totalComprasMensal > 100) {
            this.tipo = TipoCliente.ESPECIAL;
        }
    }

    public void atualizaSaldoCashback(double saldoCashback) {
        this.saldoCashback += saldoCashback;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }
}