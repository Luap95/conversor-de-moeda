import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuMoeda {
    private Moeda moeda1 = new Moeda();
    private Moeda moeda2 = new Moeda();
    private ArrayList<String> opcoes= new ArrayList<String>();

    public MenuMoeda(){
        this.opcoes.add("Reais");
        this.opcoes.add("Dólar");
        this.opcoes.add("Euro");
        this.opcoes.add("Libras Esterlinas");
        this.opcoes.add("Peso argentino");
        this.opcoes.add("Peso chileno");
    }
    public void menuConversao(){

        String tipoMoeda = null;
        //Seleciona qual moeda quer converter
        try {
            tipoMoeda = JOptionPane.showInputDialog(null,
                    "Escolha a moeda que deseja converter:", "Menu",
                    JOptionPane.PLAIN_MESSAGE, null, this.opcoes.toArray(),
                    null).toString();
        }catch (NullPointerException ex){
            //se o usuário apertar o botão cancelar, o sistema pergunta se ele quer voltar para o inicio
            ValidaEntrada.finaliza(tipoMoeda);
        }
        //obtendo o codigo da moeda para setar no objeto
        this.moeda1.setTipoMoeda(getTipoMoeda(tipoMoeda));

        String resposta = JOptionPane.showInputDialog("Digite o valor que deseja converter");
        //verificando se valor digitado é válido para setar no objeto
        moeda1.setValor(BigDecimal.valueOf(ValidaEntrada.validaValor(resposta)));
        //removendo da lista a moeda selecionada para prevenir erro de conversão
        this.opcoes.remove(getNomeMoeda(this.moeda1.getTipoMoeda()));
        //selecionando para qual moeda será convertida
        try {
            tipoMoeda = JOptionPane.showInputDialog(null,
                    "Escolha para qual moeda deseja converter:", "Menu",
                    JOptionPane.PLAIN_MESSAGE, null, this.opcoes.toArray(),
                    null).toString();
        }catch (NullPointerException ex){
            //se o usuário apertar o botão cancelar, o sistema pergunta se ele quer voltar para o inicio
            ValidaEntrada.finaliza(tipoMoeda);
        }
        //obtendo o codigo da moeda para setar no objeto
        this.moeda2.setTipoMoeda(getTipoMoeda(tipoMoeda));
        //obtendo a taxa de câmbio na api
        double taxa = this.getTaxaCambio(moeda1, moeda2);
        //calculando a conversão
        this.calculaConversao(taxa);
        //gerando a mensagem de resultado
        String mensagem = "A conversão de " + getSimboloMoeda(this.moeda1.getTipoMoeda())
                + this.moeda1.getValor() + " para " + this.getNomeMoeda(this.moeda2.getTipoMoeda())
                + " é de " + this.getSimboloMoeda(this.moeda2.getTipoMoeda()) + this.moeda2.getValor();
        //Informando o resultado da conversão
        JOptionPane.showMessageDialog(null, mensagem );
        //finaliza programa
        ValidaEntrada.finaliza(null);
    }

    private TipoMoeda getTipoMoeda(String moeda){
        switch (moeda){
            case "Reais":
                return TipoMoeda.BRL;
            case "Dólar":
                return TipoMoeda.USD;
            case "Euro":
                return TipoMoeda.EUR;
            case "Libras Esterlinas":
                return TipoMoeda.GBP;
            case "Peso argentino":
                return TipoMoeda.ARS;
            case "Peso chileno":
                return TipoMoeda.CLP;
        }
        return null;
    }

    private String getNomeMoeda(TipoMoeda tipoMoeda){
        switch (tipoMoeda){
            case BRL:
                return "Reais";
            case USD:
                return "Dólar";
            case EUR:
                return "Euro";
            case GBP:
                return "Libras Esterlinas";
            case ARS:
                return "Peso argentino";
            case CLP:
                return "Peso chileno";
        }
        return null;
    }

    private String getSimboloMoeda(TipoMoeda tipoMoeda){
        switch (tipoMoeda){
            case BRL: return "R$";
            case USD: return "US$";
            case ARS: return "AR$";
            case CLP: return "CLP$";
            case GBP: return "£";
            case EUR: return "€";
        }
        return null;
    }

    private double getTaxaCambio(Moeda moedaInicial, Moeda moedaConvertida) {
        String taxa = "";
        try {
            int timeout = 300 * 1000;
            URL httpURL = new URL("https://economia.awesomeapi.com.br/xml/"
                    + moedaInicial.getTipoMoeda() + "-" + moedaConvertida.getTipoMoeda());
            HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
            conn.setReadTimeout(timeout);
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String xml = new String();

            while ((xml = reader.readLine()) != null) {
                int inicio = xml.indexOf("<bid>") + 5;
                int fim = xml.indexOf("</bid>");

                taxa = xml.substring(inicio, fim);

            }
        } catch (Exception e) {
            e.printStackTrace();
            taxa = JOptionPane.showInputDialog("Não foi possivel conultar a taxa na web. " +
                    "Digite a taxa manualmente");
        }
        double taxaNum = ValidaEntrada.validaValor(taxa);

        return taxaNum;
    }

    private void calculaConversao(double valor){
        BigDecimal taxa = BigDecimal.valueOf(valor);
        BigDecimal mult = taxa.multiply(this.moeda1.getValor());
        this.moeda2.setValor(mult);
    }

}
