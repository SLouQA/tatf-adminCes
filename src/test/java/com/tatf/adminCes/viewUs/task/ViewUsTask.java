package com.tatf.adminCes.viewUs.task;

import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;
import com.tatf.core.verification.IVerify;

import java.util.List;


public class ViewUsTask {
    private final ViewUsPO viewUsPO;
    private final IVerify verify;


    public ViewUsTask(IBrowser browser) {
        this.viewUsPO = new ViewUsPO(browser);
        this.verify = IVerify.create();

    }

    public Element buscarFila(String nombre, String apellido, String email, String validRol) {
        viewUsPO.waitTable();
        List<Element> filas = viewUsPO.getFilas();

        for (Element fila : filas) {
            String nombreCelda = viewUsPO.getNombreCelda(fila);
            String apellidoCelda = viewUsPO.getApellidoCelda(fila);
            String emailCelda = viewUsPO.getEmailCelda(fila);
            String rolCelda = viewUsPO.getRolCelda(fila);

            if (nombreCelda.equals(nombre)
                    && apellidoCelda.equals(apellido)
                    && emailCelda.equals(email)
                    && rolCelda.equals(validRol)) {
                return fila;
            }
        }
        return null;
    }

    public void verifyUsExist(Element fila) {
        verify.verifyNotNull(fila, "El usuario creado aparece en la tabla con nombre, apellido, email y rol correctos");
    }

    public void verifyUsNotExist(Element fila) {
        verify.verifyNull(fila, "El usuario no existe");
    }
}