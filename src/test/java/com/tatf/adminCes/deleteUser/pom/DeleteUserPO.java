package com.tatf.adminCes.deleteUser.pom;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

public class DeleteUserPO extends BasePO {

    private static final String BTN_ELIMINAR=".//button";

    public DeleteUserPO(IBrowser browser) {
        super(browser);
    }
    public void deleteClick(Element fila){fila.xpath(BTN_ELIMINAR).click();}

}
