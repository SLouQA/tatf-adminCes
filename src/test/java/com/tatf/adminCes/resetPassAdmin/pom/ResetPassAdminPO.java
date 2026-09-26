package com.tatf.adminCes.resetPassAdmin.pom;

import com.tatf.core.browser.IBrowser;

public class ResetPassAdminPO {
    private final IBrowser browser;

    public ResetPassAdminPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarResetPassAdmin(){this.browser.find().link("Reiniciar contraseña").click();}

    public void ingresarEmail(String email){this.browser.find().name("inputEmail").write(email);}

    public void ingresarContrasenia(String contrasenia){this.browser.find().name("inputPassword").write(contrasenia);}

    public void ingresarRepeatContrasenia(String contrasenia){this.browser.find().name("inputRepeatPassword").write(contrasenia);}

    public void btnReset(){this.browser.find().id("btnReset").click();}

    public void btnInicSesClick(){this.browser.find().link("Ya tengo cuenta!").click();}

    public void btnCreateAdminClick(){this.browser.find().link("Crear cuenta!").click();}

    public void btnRetClick(){this.browser.find().id("return").click();}
}
