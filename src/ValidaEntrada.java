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

    public static double validaValor(String valor){
        btnCancel(valor);
        boolean converteu = false;
        double valorConvertido = 0;

        while (converteu == false){
            try {
                valorConvertido = Double.parseDouble(valor);
                converteu = true;
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Valor invalido");
                valor = JOptionPane.showInputDialog("Digite o valor válido");

            }
        }
        return valorConvertido;
    }
}
