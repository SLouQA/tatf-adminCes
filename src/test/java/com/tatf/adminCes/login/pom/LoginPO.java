package com.tatf.adminCes.login.pom;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;

public class LoginPO extends BasePO {

    private static final String LINK_INICIAR_SESION="Iniciar sesión";
    private static final String BTN_INICIAR_SESION_FORM="//*[@id=\'formLogin\']/div[3]/div[2]/button";

    public LoginPO(IBrowser browser) {
        super(browser);
    }

    public void ingresarInSes(){this.browser.find().link(LINK_INICIAR_SESION).click();}

    public void btnInSesClick(){this.browser.find().xpath(BTN_INICIAR_SESION_FORM).click();}
}
