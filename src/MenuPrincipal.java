import javax.swing.*;

public class MenuPrincipal {
    public static void menu(){
        //seletor de tipo de conversor
        String tipoConversao = (JOptionPane.showInputDialog(null,
                "Escolha uma opção:","Menu",
                JOptionPane.PLAIN_MESSAGE, null,
                TipoConversor.values(),
                null)).toString();

        switch (tipoConversao){
            case "Moeda":
                MenuMoeda menuMoeda = new MenuMoeda();
                menuMoeda.menuConversao();
                break;
            case "Temperatura" :
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                break;
            case "":
                JOptionPane.showMessageDialog(null, "Programa finalizado");
                break;
        }

    }
}
