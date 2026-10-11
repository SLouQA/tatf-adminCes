package com.tatf.adminCes.viewUs.task;

import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.core.browser.IBrowser;

public class ViewUsTask {

    private final ViewUsPO viewUsPO;

    public ViewUsTask(IBrowser browser) {this.viewUsPO = new ViewUsPO(browser);}

    public void ingresarVerUs() {viewUsPO.ingresarVerUs();}

    public boolean existeUsuario(String nombre, String apellido, String email, String rol) {
        return viewUsPO.buscarFila(nombre, apellido, email, rol) != null;
    }
}