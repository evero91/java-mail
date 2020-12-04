/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mailsender;

import java.util.Arrays;

/**
 *
 * @author ever
 */
public class Run {

    public static void main(String[] args) {
        EmailTLS email = new EmailTLS(
                "from@mail.com",
                "password",
                "smtp.gmail.com",
                "587",
                "Correo de prueba",
                "Este es un envio automatizado.\n\nNo responder por favor.",
                Arrays.asList("to1@mail.com"));

        if (email.sendMail()) {
            System.out.println("Exito!");
        } else {
            System.out.println("Error al enviar correo");
        }
    }
}
