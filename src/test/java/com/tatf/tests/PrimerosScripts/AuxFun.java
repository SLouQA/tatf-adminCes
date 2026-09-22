package com.tatf.tests.PrimerosScripts;

import com.tatf.core.element.Element;

import java.util.List;

import static com.tatf.tests.PrimerosScripts.PrimerosScripts1.*;

public class AuxFun {

    public static void LoginAdmin(String usuario, String contrasenia) {

        browser.find().xpath("//*[@id='wrapper']/ul/li/div[1]/a").click();
        browser.find().name("inputEmail").write(usuario);
        browser.find().name("inputPassword").write(contrasenia);
        browser.find().xpath("//*[@id=\'formLogin\']/div[3]/div[2]/button").click();
    }

    public static void registrarAdmin(String nombre, String apellido, String email, String contrasenia, String pais) {
        browser.find().name("inputFirstName").write(nombre);
        browser.find().name("inputLastName").write(apellido);
        browser.find().name("inputEmail").write(email);
        browser.find().name("inputPassword").write(contrasenia);
        browser.find().name("inputRepeatPassword").write(contrasenia);
        browser.find().name("inputCountry").write(pais);
        browser.find().id("btnRegister").click();
    }

    public static void resetContraseniaAdmin(String usuario, String nuevaContrasenia) {
        browser.find().name("inputEmail").write(usuario);
        browser.find().name("inputPassword").write(nuevaContrasenia);
        browser.find().name("inputRepeatPassword").write(nuevaContrasenia);
        browser.find().id("btnReset").click();
    }

    public static void registrarTest(String nombre, String apellido, String email, String contrasenia, String pais, String idRol) {
        browser.find().name("inputFirstName").write(nombre);
        browser.find().name("inputLastName").write(apellido);
        browser.find().name("inputEmail").write(email);
        browser.find().name("inputCountry").write(pais);
        browser.find().name("inputPassword").write(contrasenia);
        browser.find().id(idRol).click();

        browser.find().id("btnRegister").click();
    }

    public static Element buscarFila(String nombre, String apellido, String email, String validRol){
        browser.wait("#bodyTable tr").css();

        List<Element> filas = browser.find().cssSelectorList("#bodyTable tr");

        for (Element fila : filas) {
            String nombreCelda = fila.xpath("./td[1]").getText().trim();
            String apellidoCelda = fila.xpath("./td[2]").getText().trim();
            String emailCelda = fila.xpath("./td[3]").getText().trim();
            String rolCelda = fila.xpath("./td[5]").getText().trim();

            if (nombreCelda.equals(nombre)
                    && apellidoCelda.equals(apellido)
                    && emailCelda.equals(email)
                    && rolCelda.equals(validRol)) {
                return fila;
            }
        }
        return null;
    }

}