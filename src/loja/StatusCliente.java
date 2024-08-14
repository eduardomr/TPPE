package loja;

public class StatusCliente {
    private double saldoCashback;
    private double totalComprasMensal;
    private Cliente.TipoCliente tipo;

    public StatusCliente(Cliente.TipoCliente tipo) {
        this.tipo = tipo;
        this.saldoCashback = 0;
        this.totalComprasMensal = 0;
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
            this.tipo = Cliente.TipoCliente.ESPECIAL;
        }
    }

    public void atualizaSaldoCashback(double saldoCashback) {
        this.saldoCashback += saldoCashback;
    }

    public Cliente.TipoCliente getTipo() {
        return this.tipo;
    }

    public double getSaldoCashback() {
        return saldoCashback;
    }

    public double getTotalComprasMensal() {
        return totalComprasMensal;
    }
}
