package com.tatf.adminCes.base.pom;
import com.tatf.core.browser.IBrowser;


public class IngresoPO {
    private final IBrowser browser;

    public IngresoPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarUrl(String url){this.browser.interaction().navigateTo(url);}

    public void ingresarPass(String pass){this.browser.find().id("pass").write(pass);}

    public void ingresarClick() {this.browser.find().className("btn-primary").click();}

    public String getTitle(String titulo){
        return this.browser.find().xpath("//p[text()='" + titulo +"']").getText();}
}
