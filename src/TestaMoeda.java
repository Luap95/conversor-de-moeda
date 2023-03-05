import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public class TestaMoeda {
    public static void main(String[] args){
        Moeda real = new Moeda();
        real.setTipoMoeda(TipoMoeda.BRL);
        real.setValor(BigDecimal.valueOf(100.51));
        Moeda dolar = new Moeda();
        dolar.setTipoMoeda(TipoMoeda.USD);

        System.out.println(real.getValor());

        Conversor conversor = new Conversor();

        conversor.calculaConversao(real, dolar);

        System.out.println(dolar.getValor());

    }
}
