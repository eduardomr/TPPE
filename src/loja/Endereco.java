package loja;
public class Endereco {
    String regiao;
    boolean capital;

    public Endereco(String regiao, boolean capital){
        this.regiao = regiao;
        this.capital = capital;
    }

    public double getFrete(){
        if (regiao == "Distrito Federal"){
            return 5.00;
        } else if (regiao == "Centro Oeste"){
            if (capital){
                return 10.00;
            } else {
                return 13.00;
            }
        } else if (regiao == "Nordeste"){
            if (capital){
                return 15.00;
            } else {
                return 18.00;
            }
        } else if (regiao == "Norte"){
            if (capital){
                return 20.00;
            } else {
                return 25.00;
            }
        } else if (regiao == "Sudeste"){
            if (capital){
                return 7.00;
            } else {
                return 10.00;
            }
        } else if (regiao == "Sul"){
            if (capital){
                return 10.00;
            } else {
                return 13.00;
            }
        } else {
            return 0.00;
        }
    }

    public String getRegiao() {
        return this.regiao;
    }

    public boolean isCapital() {
        return this.capital;
    }
}