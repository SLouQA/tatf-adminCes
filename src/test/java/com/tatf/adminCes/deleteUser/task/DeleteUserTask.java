package com.tatf.adminCes.deleteUser.task;

import com.tatf.adminCes.deleteUser.pom.DeleteUserPO;
import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;

public class DeleteUserTask {

    private final DeleteUserPO deleteUsPO;
    private final ViewUsPO viewUsPO;

    public DeleteUserTask(IBrowser browser) {
        this.deleteUsPO = new DeleteUserPO(browser);
        this.viewUsPO = new ViewUsPO(browser);
    }

    public void eliminarUsuario(String nombre, String apellido, String email, String rol) {
        Element fila = viewUsPO.buscarFila(nombre, apellido, email, rol);
        deleteUsPO.deleteClick(fila);
    }
}
