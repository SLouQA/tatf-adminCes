package com.tatf.adminCes.base.pom;
import com.tatf.core.browser.IBrowser;

import static com.tatf.adminCes.base.data.IngresoData.*;

public class IngresoPO {
    private final IBrowser browser;

    public IngresoPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarUrl(){this.browser.interaction().navigateTo(URL);}

    public void ingresarPass(){this.browser.find().id("pass").write(URL_CONTRASENIA);}

    public String getTitle(){
        return this.browser.find().xpath("//p[text()='" + TITULO +"']").getText();}

    public void ingresarClick() {this.browser.find().className("btn-primary").click();}
}
