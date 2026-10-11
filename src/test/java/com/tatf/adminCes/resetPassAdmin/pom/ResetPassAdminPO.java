package com.tatf.adminCes.resetPassAdmin.pom;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;

public class ResetPassAdminPO extends BasePO {

    private static final String LINK_REINICIAR_CONTRASENIA="Reiniciar contraseña";
    private static final String BTN_RESET="btnReset";

    public ResetPassAdminPO(IBrowser browser) {
        super(browser);
    }

    public void ingresarResetPassAdmin(){this.browser.find().link(LINK_REINICIAR_CONTRASENIA).click();}

    public void btnReset(){this.browser.find().id(BTN_RESET).click();}
}
