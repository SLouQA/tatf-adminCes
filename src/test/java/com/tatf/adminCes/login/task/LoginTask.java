package com.tatf.adminCes.login.task;

import com.tatf.adminCes.login.pom.LoginPO;
import com.tatf.core.browser.IBrowser;

public class LoginTask {

    private final LoginPO loginPO;

    public LoginTask(IBrowser browser) {this.loginPO = new LoginPO(browser);}

    public void loginAdmin(String email, String contrasenia){
        this.loginPO.ingresarInSes();
        this.loginPO.ingresarEmail(email);
        this.loginPO.ingresarContrasenia(contrasenia);
        this.loginPO.btnInSesClick();
    }

}
