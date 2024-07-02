import loja.*;
import java.util.Date;

public class App {
    // public static void main(String[] args) {
    //     Endereco endereco = new Endereco("Distrito Federal", true);
    //     Cliente cliente = new Cliente("João", endereco);
    //     System.out.println(cliente.getDesconto());
    //     System.out.println(cliente.getDescontoFrete());
    //     System.out.println(cliente);
    // }

    public static void main(String[] args) {

        Endereco endereco = new Endereco("Centro-oeste", true);
        Cliente cliente = new Cliente("João", endereco);
        Venda.metodoPagamento pagamento = Venda.metodoPagamento.cartao;
        Date data = new Date();
        String numeroCartao = "4296 1343 2424 1234";

        Produto produto1 = new Produto("Produto 1", 1, "Descricao 1", 10.0, "unidade");
        Produto produto2 = new Produto("Produto 2", 2, "Descricao 2", 20.0, "unidade");
        Produto produto3 = new Produto("Produto 3", 3, "Descricao 3", 30.0, "unidade");
        Produto produto4 = new Produto("Produto 4", 4, "Descricao 4", 40.0, "unidade");
        Produto produto5 = new Produto("Produto 5", 5, "Descricao 5", 50.0, "unidade");

        ItemVenda item1 = new ItemVenda(produto1, 1);
        ItemVenda item2 = new ItemVenda(produto2, 2);
        ItemVenda item3 = new ItemVenda(produto3, 3);
        ItemVenda item4 = new ItemVenda(produto4, 4);
        ItemVenda item5 = new ItemVenda(produto5, 5);

        ItemVenda[] produtos1 = {item1, item2, item3, item4, item5};
        ItemVenda[] produtos2 = {item1, item2, item3, item4};
        ItemVenda[] produtos3 = {item1, item2, item3};
        ItemVenda[] produtos4 = {item1, item2};
        ItemVenda[] produtos5 = {item1};

        Venda venda = new Venda(produtos5, cliente, pagamento, data);
        System.out.println(venda.calcularTotal(pagamento, numeroCartao));
    }



}


