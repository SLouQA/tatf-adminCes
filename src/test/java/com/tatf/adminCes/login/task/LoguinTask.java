package com.tatf.adminCes.login.task;

import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import static com.tatf.adminCes.login.data.LoguinData.*;


public class LoguinTask {
    private final IBrowser browser;
    private final LoguinPO loguinPO;
    private final IVerify verify;

    public LoguinTask(IBrowser browser) {
        this.browser = browser;
        this.loguinPO = new LoguinPO(this.browser);
        this.verify = IVerify.create();
    }

    public void loguinAdmin(String email, String contrasenia){

        this.loguinPO.ingresarInSes();
        this.loguinPO.ingresarEmail(email);
        this.loguinPO.ingresarContrasenia(contrasenia);
        this.loguinPO.btnInSesClick();
    }
    public void verifyLoguinAdminOk(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_SESION_OK, "Usuario creado OK");
    }

}
