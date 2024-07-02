package loja;

public class Cliente {
    int id;
    String nome;
    public enum tipoCliente {PADRAO, ESPECIAL, PRIME};
    tipoCliente tipo;
    Endereco endereco;
    double saldoCashback;
    double totalComprasMensal;

    public Cliente(String nome, Endereco endereco) {
        this(nome, endereco, tipoCliente.PADRAO);
    }

    public Cliente(String nome, Endereco endereco, tipoCliente tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.saldoCashback = 0;
        this.totalComprasMensal = 0;
        this.tipo = tipo;
    }

   public double getDesconto(){
        switch (this.tipo) {
            case PADRAO:
                return 0.0;
            case ESPECIAL:
                return 0.1;
            case PRIME:
                return 0.0;
            default:
                return 0.0;
        }
    };

    public tipoCliente getTipo() {
        return this.tipo;
    };

    public double getDescontoFrete(){
        switch (this.tipo) {
            case PADRAO:
                return 0.0;
            case ESPECIAL:
                return 0.3;
            case PRIME:
                return 1;
            default:
                return 0.0;
        }
    };

    public void atualizaTotalComprasMes(double valorComprado ){
        this.totalComprasMensal = this.totalComprasMensal + valorComprado;
        if (this.totalComprasMensal > 100){
            this.tipo = tipoCliente.ESPECIAL;
        }
    };

    public void atualizaSaldoCashback(double saldoCashback){
        this.saldoCashback = this.saldoCashback + saldoCashback;
    };

}



