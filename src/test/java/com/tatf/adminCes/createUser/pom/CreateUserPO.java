package com.tatf.adminCes.createUser.pom;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;

public class CreateUserPO extends BasePO {

    private static final String LINK_CREAR_USUARIO = "Crear usuario";
    private static final String LINK_REGISTRARSE = "Registrarse";
    private static final String BTN_REGISTRAR = "btnRegister";
    private static final String INPUT_NOMBRE = "inputFirstName";
    private static final String INPUT_APELLIDO = "inputLastName";
    private static final String INPUT_PAIS = "inputCountry";

    public CreateUserPO(IBrowser browser) {
        super(browser);
    }

    public void ingresarRegTester(){this.browser.find().link(LINK_CREAR_USUARIO).click();}

    public void ingresarRol(String idRol){this.browser.find().id(idRol).click();}

    public void ingresarRegistrarse(){this.browser.find().link(LINK_REGISTRARSE).click();}

    public void btnRegClick(){this.browser.find().id(BTN_REGISTRAR).click();}

    public void ingresarNombre(String nombre){this.browser.find().name(INPUT_NOMBRE).write(nombre);}

    public void ingresarApellido(String apellido){this.browser.find().name(INPUT_APELLIDO).write(apellido);}

    public void ingresarPais(String pais){this.browser.find().name(INPUT_PAIS).write(pais);}
}