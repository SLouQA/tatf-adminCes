package com.tatf.adminCes.base.task;

import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.core.browser.IBrowser;

public class GeneralTask {
    private final GeneralPO generalPO;

    public GeneralTask(IBrowser browser) {
        this.generalPO = new GeneralPO(browser);
    }



    public String obtenerMsjModal() {
        return generalPO.getMsjModal();
    }

    public void confirmarModal() {
        generalPO.okClick();
    }
}
