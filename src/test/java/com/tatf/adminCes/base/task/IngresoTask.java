package com.tatf.adminCes.base.task;

import com.tatf.adminCes.base.pom.IngresoPO;
import com.tatf.core.browser.IBrowser;
import static com.tatf.adminCes.base.data.IngresoData.*;

public class IngresoTask {
    private final IngresoPO ingresoPO;

    public IngresoTask(IBrowser browser) {this.ingresoPO = new IngresoPO(browser);}

    public String enterToSystem() {
        ingresoPO.ingresarUrl(URL);
        ingresoPO.ingresarPass(PASS_URL);
        ingresoPO.ingresarClick();
        return ingresoPO.getTitle();
    }

}