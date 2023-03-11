import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class MenuMoeda {
    private Moeda moeda1 = new Moeda();
    private Moeda moeda2 = new Moeda();

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

        String resposta = JOptionPane.showInputDialog("Digite o valor que deseja converter");
        moeda1.setValor(BigDecimal.valueOf(ValidaEntrada.validaValor(resposta)));

        try {
            tipoMoeda = JOptionPane.showInputDialog(null,
                    "Escolha para qual moeda deseja converter:", "Menu",
                    JOptionPane.PLAIN_MESSAGE, null, this.opcoes,
                    null).toString();
        }catch (NullPointerException ex){
            ValidaEntrada.btnCancel(tipoMoeda);
        }

        this.moeda2.setTipoMoeda(getTipoMoeda(tipoMoeda));

        double taxa = getTaxaCambio(moeda1, moeda2);
        System.out.println(taxa);
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

    private double getTaxaCambio(Moeda moedaInicial, Moeda moedaConvertida){

        double taxa = 0;
        String tx = null;
        System.out.println(moedaInicial.getTipoMoeda() +" "+ moedaConvertida.getTipoMoeda());
        try {
            int timeout = 300 * 1000;
            URL httpURL = new URL("https://economia.awesomeapi.com.br/xmk/"
                    + moedaInicial.getTipoMoeda() + "-" + moedaConvertida.getTipoMoeda());
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setReadTimeout(timeout);
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String xml = new String();

            while ((xml = reader.readLine()) != null) {
                int inicio = xml.indexOf("<bid>") + 5;
                int fim = xml.indexOf("</bid>");

                tx = xml.substring(inicio, fim);

            }
            System.out.println(tx);
            taxa = ValidaEntrada.validaValor(tx);

        } catch (Exception e) {
            e.printStackTrace();
            String resposta = JOptionPane.showInputDialog("Erro ao consultar a taxa de câmbio. " +
                    "Digite a taxa manualmente");
            taxa = ValidaEntrada.validaValor(resposta);
        }

        return taxa;
    }
}
