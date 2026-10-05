package com.tatf.adminCes.base.task;

import com.tatf.adminCes.base.pom.IngresoPO;
import com.tatf.core.browser.IBrowser;

import static com.tatf.adminCes.base.data.IngresoData.TITULO;
import com.tatf.core.verification.IVerify;

public class IngresoTask {
    private final IBrowser browser;
    private final IngresoPO ingresoPO;
    private final IVerify verify;

    public IngresoTask(IBrowser browser) {
        this.browser = browser;
        this.ingresoPO = new IngresoPO(this.browser);
        this.verify = IVerify.create();
    }

    public String enterToSystem() {
        ingresoPO.ingresarUrl();
        ingresoPO.ingresarPass();
        ingresoPO.ingresarClick();
        return ingresoPO.getTitle();
    }

    public void verifyTitle(String tituloObtenido) {
        verify.verify(tituloObtenido, TITULO, "Se puedo acceder correctamente.");
    }
}