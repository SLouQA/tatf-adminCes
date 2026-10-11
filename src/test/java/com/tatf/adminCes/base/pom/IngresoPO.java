package com.tatf.adminCes.base.pom;

import com.tatf.core.browser.IBrowser;

public class IngresoPO extends BasePO {

    private static final String INPUT_PASS = "pass";
    private static final String BTN_INGRESAR = "btn-primary";
    private static final String TITULO_PAGINA = "p";

    public IngresoPO(IBrowser browser) {
        super (browser);
    }

    public void ingresarUrl(String url){this.browser.interaction().navigateTo(url);}

    public void ingresarPass(String pass){this.browser.find().id(INPUT_PASS).write(pass);}

    public void ingresarClick() {this.browser.find().className(BTN_INGRESAR).click();}

    public String getTitle(){
        return this.browser.find().tag(TITULO_PAGINA).getText();}
}
