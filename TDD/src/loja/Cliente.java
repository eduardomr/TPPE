package loja;
public class Cliente {
    int id;
    String nome;
    enum tipoCliente {PADRAO, ESPECIAL, PRIME};
    Endereco endereco;
    double saldoCashback;
    double totalComprasMensal;
}
