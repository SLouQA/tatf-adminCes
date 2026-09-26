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

    public void resetContraseniaAdmin(String email, String nuevaContrasenia) {
        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminPO.ingresarEmail(email);
        resetPassAdminPO.ingresarContrasenia(nuevaContrasenia);
        resetPassAdminPO.ingresarRepeatContrasenia(nuevaContrasenia);
        resetPassAdminPO.btnReset();
    }

    public void verifyResetOk(String tituloObtenido) {
        verify.verify(tituloObtenido, MSJ_RESET_CONTRASENIA, "Contraseña reiniciada OK");
    }
}
