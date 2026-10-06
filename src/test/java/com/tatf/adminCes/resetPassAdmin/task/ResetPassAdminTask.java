package com.tatf.adminCes.resetPassAdmin.task;

import com.tatf.adminCes.resetPassAdmin.pom.ResetPassAdminPO;
import com.tatf.core.browser.IBrowser;


public class ResetPassAdminTask {
    private final ResetPassAdminPO resetPassAdminPO;

    public ResetPassAdminTask(IBrowser browser) {
        this.resetPassAdminPO = new ResetPassAdminPO(browser);
    }

    public void resetContraseniaAdmin(String email, String nuevaCont, String nuevaContDos) {
        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminPO.ingresarEmail(email);
        resetPassAdminPO.ingresarContrasenia(nuevaCont);
        resetPassAdminPO.ingresarRepeatContrasenia(nuevaContDos);
        resetPassAdminPO.btnReset();
    }
}
