import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {

        String tipoConversao = (JOptionPane.showInputDialog(null,
                "Escolha uma opção:","Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoConversor.values(),
                null)).toString();

        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor que deseja converter"));

        
        /*
        JFrame jFrame = new JFrame();
        jFrame.setSize(300,300);

        JPanel jPanel = new JPanel();
        jPanel.setSize(300, 300);

        GridBagLayout gb = new GridBagLayout();

        jPanel.setLayout(new GridLayout(10,3,0,5));

        JLabel labelSelecionaConversor = new JLabel();
        labelSelecionaConversor.setText("Selecione o tipo de conversor");

        JComboBox selecionaConversor = new JComboBox();
        selecionaConversor.setSize(5,6);
        selecionaConversor.addItem("Conversor de moeda");
        selecionaConversor.addItem("Conversor de temperatura");

        jPanel.add(labelSelecionaConversor);
        jPanel.add(selecionaConversor);

        jFrame.add(jPanel);

        jFrame.setVisible(false);
        */

    }
}