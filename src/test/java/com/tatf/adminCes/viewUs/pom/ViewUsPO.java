package com.tatf.adminCes.viewUs.pom;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

import java.util.List;

public class ViewUsPO {
    private final IBrowser browser;

    public ViewUsPO(IBrowser browser) {
        this.browser = browser;
    }

    public void ingresarViewUs(){this.browser.find().link("Ver usuarios").click();}

    public void waitTable() {this.browser.wait("#bodyTable tr").css();}

    public List<Element> getFilas() {
        return browser.find().cssSelectorList("#bodyTable tr");
    }

    public String getNombreCelda(Element fila) {
        return fila.xpath("./td[1]").getText().trim();
    }

    public String getApellidoCelda(Element fila) {
        return fila.xpath("./td[2]").getText().trim();
    }

    public String getEmailCelda(Element fila) {
        return fila.xpath("./td[3]").getText().trim();
    }

    public String getRolCelda(Element fila) {
        return fila.xpath("./td[5]").getText().trim();
    }
}
