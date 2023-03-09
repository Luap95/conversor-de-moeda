import javax.swing.*;

public class MenuMoeda {
    private Moeda moeda1 = new Moeda();
    private Moeda moeda2 = new Moeda();
    private double valor;

    private Object[] opcoes = new Object[] {"Reais", "Dólar", "Euro", "Libras Esterlinas"
            , "Peso argentino", "Peso Chileno"};
    public void menuConversao(){
        String tipoMoeda = null;
        try {
            tipoMoeda = JOptionPane.showInputDialog(null,
                    "Escolha a moeda que deseja converter:", "Menu",
                    JOptionPane.PLAIN_MESSAGE, null, this.opcoes,
                    null).toString();
        }catch (NullPointerException ex){
            ValidaEntrada.btnCancel(tipoMoeda);
        }

        this.moeda1.setTipoMoeda(getTipoMoeda(tipoMoeda));






    }

    private TipoMoeda getTipoMoeda(String moeda){
        switch (moeda){
            case "Reais":
                return TipoMoeda.BRL;
            case "Dólar":
                return TipoMoeda.USD;
            case "Euro":
                return TipoMoeda.EUR;
            case "Libras Esterlinas":
                return TipoMoeda.GBP;
            case "Peso argentino":
                return TipoMoeda.ARS;
            case "Peso Chileno":
                return TipoMoeda.CLP;
        }
        return null;
    }

}
