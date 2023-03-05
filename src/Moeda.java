import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Moeda {

    private TipoMoeda tipoMoeda;
    private BigDecimal valor;

    public Moeda(TipoMoeda tm, BigDecimal valor){
        setTipoMoeda(tm);
        this.valor = valor.setScale(4,RoundingMode.HALF_EVEN);
    }
    public Moeda(){

    }

    public void setTipoMoeda(TipoMoeda tipoMoeda) {
        this.tipoMoeda = tipoMoeda;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor.setScale(4,RoundingMode.HALF_EVEN);

    }

    public TipoMoeda getTipoMoeda() {
        return tipoMoeda;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
