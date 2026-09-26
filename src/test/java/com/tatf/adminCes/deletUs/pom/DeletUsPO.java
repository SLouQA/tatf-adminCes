package com.tatf.adminCes.deletUs.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

public class DeletUsPO {
    private final IBrowser browser;

    public DeletUsPO(IBrowser browser) {
        this.browser = browser;
    }

    public void deletClick(Element fila){fila.xpath(".//button").click();}


}
