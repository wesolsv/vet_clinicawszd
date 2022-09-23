package br.com.clinicawszd.clinicavet.util;

import javax.validation.constraints.Size;

public final class FormatNumber {

    public FormatNumber() {
    }

    public String formatarNumero(String tipo, String numero) {

        numero=numero.replaceAll("\\D", ""); //Removo todos os caracteres não-numéricos
        String mascara="";

        if (tipo.equalsIgnoreCase("telefone")) {
            //Neste ponto o telefone ou tem 11 digitos (celulares) ou 10 digitos (fixos)
            mascara= "###########"; //Celulares
            if (numero.length()==10) mascara="##########"; //Telefones fixos
        }
        if (tipo.equalsIgnoreCase("cpf")) {
            mascara="###########";
        }
        try {
            javax.swing.text.MaskFormatter formatadorNumero = new javax.swing.text.MaskFormatter(mascara);
            javax.swing.JFormattedTextField txtNumero = new javax.swing.JFormattedTextField(formatadorNumero);
            txtNumero.setText(numero);
            return txtNumero.getText();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return "";
        }

    }
}
