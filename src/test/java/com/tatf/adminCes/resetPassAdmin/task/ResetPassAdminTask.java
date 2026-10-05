package com.tatf.adminCes.resetPassAdmin.task;

import com.tatf.adminCes.resetPassAdmin.pom.ResetPassAdminPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import static com.tatf.adminCes.resetPassAdmin.data.ResetPassAdminData.*;

public class ResetPassAdminTask {
    private final IBrowser browser;
    private final ResetPassAdminPO resetPassAdminPO;
    private final IVerify verify;

    public ResetPassAdminTask(IBrowser browser) {
        this.browser = browser;
        this.resetPassAdminPO = new ResetPassAdminPO(this.browser);
        this.verify = IVerify.create();
    }

    public void resetContraseniaAdmin(String email, String nuevaCont, String nuevaContDos) {
        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminPO.ingresarEmail(email);
        resetPassAdminPO.ingresarContrasenia(nuevaCont);
        resetPassAdminPO.ingresarRepeatContrasenia(nuevaContDos);
        resetPassAdminPO.btnReset();
    }

    public void verifyResetAdminOk(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_CONTRASENIA, "Contraseña reiniciada OK.");
    }

    public void verifyResetFormVacio(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_CONT_VACIO, "Form vacio.");
    }

    public void verifyResetUsNoExiste(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_US_NOTEXIST, "Ususario no existe.");
    }

    public void verifyResetUsNoAdmin(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_US_NOADMIN, "Usuario no admin.");
    }

    public void verifyResetPassNoIgual(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_CONT_NOCOINCIDE, "Las contraseñas no coinciden.");
    }

}
