import javax.swing.*;

public class MenuPrincipal {
    public static void menu(){
        //seletor de tipo de conversor
        String tipoConversao = (JOptionPane.showInputDialog(null,
                "Escolha uma opção:","Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoConversor.values(),
                null)).toString();
        System.out.println(tipoConversao);
        switch (tipoConversao){
            case "Moeda":
                Conversor conversor = new Conversor();
                conversor.ConversorMoeda();
                break;
            case "Temperatura" :
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                break;
            case "" :
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                break;
        }

    }
}
