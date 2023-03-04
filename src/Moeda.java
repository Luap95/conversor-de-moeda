import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Moeda {

    private TipoMoeda tipoMoeda;
    private BigDecimal valor;

    public Moeda(TipoMoeda tm, double valor){
        setTipoMoeda(tm);
        this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setTipoMoeda(TipoMoeda tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;

    }

    public TipoMoeda getTipoMoeda() {
        return tipoMoeda;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
