package loja;

public class Produto {
    String nome;
    int codigo;
    String descricao;
    double valorVenda;
    String unidade;

    public Produto(String nome, int codigo, String descricao, double valorVenda, String unidade) {
        this.nome = nome;
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.unidade = unidade;
    }

    public String getNome() {
        return this.nome;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public double getValorVenda() {
        return this.valorVenda;
    }

    public String getUnidade() {
        return this.unidade;
    }
}