import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conversor {
    public void ConverteMoeda(Moeda moedaInicial, Moeda moedaConvertida)  {
        try {
            int timeout = 300 * 1000;
            URL httpURL = new URL("https://economia.awesomeapi.com.br/xml/"
                    +moedaConvertida.getTipoMoeda()+"-"+moedaInicial.getTipoMoeda());
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setReadTimeout(timeout);
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String xml = new String();
            int i = 0;
            while ((xml = reader.readLine()) != null) {
                i++;
                int inicio= xml.indexOf("<bid>") + 5;
                int fim = xml.indexOf("</bid>");
                System.out.println(xml.substring(inicio, fim));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
