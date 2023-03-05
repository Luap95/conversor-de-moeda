import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conversor {

    public void ConversorMoeda() {

        TipoMoeda tipoMoeda = (TipoMoeda) JOptionPane.showInputDialog(null,
                "Escolha a moeda que deseja converter:", "Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoMoeda.values(),
                null);

        BigDecimal valor = BigDecimal
                .valueOf(Double.parseDouble(
                        JOptionPane.showInputDialog("Digite o valor que deseja converter")));

        Moeda moedaInicial = new Moeda(tipoMoeda, valor);

        TipoMoeda tipoMoedaConvertida = (TipoMoeda) JOptionPane.showInputDialog(null,
                "Escolha para qual moeda deseja converter:", "Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoMoeda.values(),
                null);
        Moeda moedaConvertida = new Moeda();

        moedaConvertida.setTipoMoeda(tipoMoedaConvertida);

        calculaConversao(moedaInicial, moedaConvertida);

        String mensagem = "A conversão de " + moedaInicial.getTipoMoeda() + moedaInicial.getValor() + " para "
                + moedaConvertida.getTipoMoeda() + " é de " + moedaConvertida.getValor();

        JOptionPane.showMessageDialog(null, mensagem);

    }

    public String getTaxaCambio(Moeda moedaInicial, Moeda moedaConvertida) {
        String taxa = "";
        try {
            int timeout = 300 * 1000;
            URL httpURL = new URL("https://economia.awesomeapi.com.br/xml/"
                    + moedaInicial.getTipoMoeda() + "-" + moedaConvertida.getTipoMoeda());
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setReadTimeout(timeout);
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String xml = new String();

            while ((xml = reader.readLine()) != null) {
                int inicio = xml.indexOf("<bid>") + 5;
                int fim = xml.indexOf("</bid>");

                taxa = xml.substring(inicio, fim);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taxa;
    }

    public void calculaConversao(Moeda moedaInical, Moeda moedaConvertida) {
        String taxa = getTaxaCambio(moedaInical, moedaConvertida);
        BigDecimal moeda = moedaInical.getValor();
        BigDecimal tc = new BigDecimal(taxa);
        BigDecimal resultado = moeda.multiply(tc);
        moedaConvertida.setValor(resultado);
    }

}
