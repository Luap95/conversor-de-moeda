import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public class TestaMoeda {
    public static void main(String[] args){
        Moeda real = new Moeda(TipoMoeda.BRL,100.50);
        Moeda dolar = new Moeda(TipoMoeda.ARS, 0);

        System.out.println(real.getValor());

        Conversor conversor = new Conversor();

        conversor.ConverteMoeda(real, dolar);

    }
}
