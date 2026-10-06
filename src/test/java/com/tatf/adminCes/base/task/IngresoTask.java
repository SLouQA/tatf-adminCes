package com.tatf.adminCes.base.task;

import com.tatf.adminCes.base.pom.IngresoPO;
import com.tatf.core.browser.IBrowser;

public class IngresoTask {
    private final IngresoPO ingresoPO;

    public IngresoTask(IBrowser browser) {this.ingresoPO = new IngresoPO(browser);}



    public String enterToSystem(String url, String pass, String titulo) {
        ingresoPO.ingresarUrl(url);
        ingresoPO.ingresarPass(pass);
        ingresoPO.ingresarClick();
        return ingresoPO.getTitle(titulo);
    }

}