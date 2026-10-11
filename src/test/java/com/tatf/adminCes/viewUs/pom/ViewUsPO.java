package com.tatf.adminCes.viewUs.pom;

import com.tatf.adminCes.base.pom.BasePO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;
import java.util.List;

public class ViewUsPO extends BasePO {

    private static final String LINK_VER_USUARIOS = "Ver usuarios";
    private static final String TABLA_FILAS = "#bodyTable tr";
    private static final String CELL_NOMBRE = "./td[1]";
    private static final String CELL_APELLIDO = "./td[2]";
    private static final String CELL_EMAIL = "./td[3]";
    private static final String CELL_ROL = "./td[5]";

    public ViewUsPO(IBrowser browser) {
        super(browser);
    }

    public void ingresarVerUs(){this.browser.find().link(LINK_VER_USUARIOS).click();}

    public void waitTable() {this.browser.wait(TABLA_FILAS).css();}

    private List<Element> getFilas() {
        return browser.find().cssSelectorList(TABLA_FILAS);
    }

    private String getNombreCelda(Element fila) {
        return fila.xpath(CELL_NOMBRE).getText().trim();
    }

    private String getApellidoCelda(Element fila) {
        return fila.xpath(CELL_APELLIDO).getText().trim();
    }

    private String getEmailCelda(Element fila) {return fila.xpath(CELL_EMAIL).getText().trim();}

    private String getRolCelda(Element fila) {
        return fila.xpath(CELL_ROL).getText().trim();
    }

    public Element buscarFila(String nombre, String apellido, String email, String validRol) {
        waitTable();
        List<Element> filas = getFilas();

        for (Element fila : filas) {
            if (getNombreCelda(fila).equals(nombre)
                    && getApellidoCelda(fila).equals(apellido)
                    && getEmailCelda(fila).equals(email)
                    && getRolCelda(fila).equals(validRol)) {
                return fila;
            }
        }
        return null;
    }
}
