import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {

        String tipoConversao = (JOptionPane.showInputDialog(null,
                "Escolha uma opção:","Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoConversor.values(),
                null)).toString();

        BigDecimal valor = new BigDecimal(JOptionPane.showInputDialog("Digite o valor que deseja converter"))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}