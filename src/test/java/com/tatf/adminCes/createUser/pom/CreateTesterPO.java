package com.tatf.adminCes.createUser.pom;

import com.tatf.core.browser.IBrowser;

public class CreateTesterPO {
    private final IBrowser browser;

    public CreateTesterPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarRegTester(){this.browser.find().link("Crear usuario").click();}

    public void ingresarRol(String idRol){this.browser.find().id(idRol).click();}


}
