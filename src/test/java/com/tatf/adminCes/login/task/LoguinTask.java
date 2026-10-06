package com.tatf.adminCes.login.task;

import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.core.browser.IBrowser;



public class LoguinTask {
    private final LoguinPO loguinPO;

    public LoguinTask(IBrowser browser) {this.loguinPO = new LoguinPO(browser);}

    public void loguinAdmin(String email, String contrasenia){
        this.loguinPO.ingresarInSes();
        this.loguinPO.ingresarEmail(email);
        this.loguinPO.ingresarContrasenia(contrasenia);
        this.loguinPO.btnInSesClick();
    }

}
