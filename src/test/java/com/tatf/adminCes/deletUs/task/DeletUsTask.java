package com.tatf.adminCes.deletUs.task;

import com.tatf.adminCes.deletUs.pom.DeletUsPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;



public class DeletUsTask {
    private final DeletUsPO deletUsPO;

    public DeletUsTask(IBrowser browser) {
        this.deletUsPO = new DeletUsPO(browser);
    }

    public void eliminarUsuario(Element fila) {
        deletUsPO.deletClick(fila);
    }

}
