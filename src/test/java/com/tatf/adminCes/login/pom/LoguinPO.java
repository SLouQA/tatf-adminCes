package com.tatf.adminCes.login.pom;

import com.tatf.core.browser.IBrowser;

public class LoguinPO {
    private final IBrowser browser;

    public LoguinPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarEmail(String email){this.browser.find().name("inputEmail").write(email);}

    public void ingresarContrasenia(String contrasenia){this.browser.find().name("inputPassword").write(contrasenia);}

    public void ingresarInSes(){this.browser.find().link("Iniciar sesión").click();}

    public void btnRetClick(){this.browser.find().id("return").click();}

    public void btnInSesClick(){this.browser.find().xpath("//*[@id=\'formLogin\']/div[3]/div[2]/button").click();}

    public void btnOlvContraseniaClick(){this.browser.find().link("Olvide mi contraseña").click();}

    public void btnCreateAdminClick(){this.browser.find().link("Crear cuenta administrador").click();}

}
