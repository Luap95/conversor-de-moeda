import javax.swing.*;

public class ValidaEntrada {
    public static void btnCancel(Object obj){
        if(obj == null){
            int acao = JOptionPane.showConfirmDialog(null
                    , "Deseja retornar ao menu principal?",
                    "Selecione uma opção", JOptionPane.YES_NO_CANCEL_OPTION);

            switch (acao){
                case 0: MenuPrincipal.menu();
                    break;
                case 1: JOptionPane.showMessageDialog(null, "Programa finalizado");
                    break;
                case 2: JOptionPane.showMessageDialog(null, "Programa concluido");
                    break;
            }
        }
    }
}
