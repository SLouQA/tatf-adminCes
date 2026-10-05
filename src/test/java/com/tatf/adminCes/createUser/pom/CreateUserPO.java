package com.tatf.adminCes.createUser.pom;

import com.tatf.core.browser.IBrowser;

public class CreateUserPO {
    private final IBrowser browser;

    public CreateUserPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarRegTester(){this.browser.find().link("Crear usuario").click();}

    public void ingresarRol(String idRol){this.browser.find().id(idRol).click();}

    public void ingresarRegistrarse(){this.browser.find().link("Registrarse").click();}

    public void btnRegClick(){this.browser.find().id("btnRegister").click();}

    public void ingresarNombre(String nombre){this.browser.find().name("inputFirstName").write(nombre);}

    public void ingresarApellido(String apellido){this.browser.find().name("inputLastName").write(apellido);}

    public void ingresarEmail(String email){this.browser.find().name("inputEmail").write(email);}

    public void ingresarContrasenia(String contrasenia){this.browser.find().name("inputPassword").write(contrasenia);}

    public void ingresarContraseniaRepeat(String contrasenia){this.browser.find().name("inputRepeatPassword").write(contrasenia);}

    public void ingresarPais(String pais){this.browser.find().name("inputCountry").write(pais);}

    public void btnOlvContraseniaClick(){this.browser.find().link("Olvide mi contraseña").click();}

    public void btnInicSesClick(){this.browser.find().link("Ya tengo cuenta!").click();}

    public void btnRetClick(){this.browser.find().id("return").click();}

}
