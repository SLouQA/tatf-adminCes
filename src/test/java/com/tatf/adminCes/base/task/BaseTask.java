package com.tatf.adminCes.base.task;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;

public class BaseTask {
    private final BasePO basePO;

    public BaseTask(IBrowser browser) {
        this.basePO = new BasePO(browser);
    }

    public String obtenerMsjModal() {
        return basePO.getMsjModal();
    }

    public void confirmarModal() {
        basePO.okClick();
    }
}
