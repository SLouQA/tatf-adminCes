package com.tatf.adminCes.viewUs.task;

import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

import java.util.List;


public class ViewUsTask {
    private final ViewUsPO viewUsPO;


    public ViewUsTask(IBrowser browser) {this.viewUsPO = new ViewUsPO(browser);}

    public Element buscarFila(String nombre, String apellido, String email, String validRol) {
        viewUsPO.waitTable();
        List<Element> filas = viewUsPO.getFilas();

        for (Element fila : filas) {
            if (viewUsPO.getNombreCelda(fila).equals(nombre)
                    && viewUsPO.getApellidoCelda(fila).equals(apellido)
                    && viewUsPO.getEmailCelda(fila).equals(email)
                    && viewUsPO.getRolCelda(fila).equals(validRol)) {
                return fila;
            }
        }
        return null;
    }

    public void ingresarViewUs() {viewUsPO.ingresarViewUs();}
}