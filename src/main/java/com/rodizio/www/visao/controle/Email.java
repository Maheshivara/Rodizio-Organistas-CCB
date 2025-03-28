package com.rodizio.www.visao.controle;

public abstract class Email {

    //private String e_mail;
    public static boolean eValido(String gEmail) {
        //ExpressÃ£o regular tirada do Site:
        //"http://umcastec.blogspot.com.br/2010/08/expressao-regular-para-validar-e-mail.html"
        return gEmail.matches("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
    }
}

