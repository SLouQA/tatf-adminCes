package com.tatf.adminCes.createUser.task;

import com.tatf.adminCes.createUser.pom.CreateAdminPO;
import com.tatf.adminCes.createUser.pom.CreateTesterPO;
import com.tatf.core.browser.IBrowser;

public class CreateTesterTask {
    private final IBrowser browser;
    private final CreateTesterPO createTesterPO;
    private final CreateAdminPO createAdminPO;

    public CreateTesterTask(IBrowser browser) {
        this.browser = browser;
        this.createTesterPO = new CreateTesterPO(this.browser);
        this.createAdminPO = new CreateAdminPO(this.browser);
    }

    public void regTester(String nombre, String apellido, String email, String contrasenia, String pais, String idRol){
        this.createAdminPO.ingresarNombre(nombre);
        this.createAdminPO.ingresarApellido(apellido);
        this.createAdminPO.ingresarEmail(email);
        this.createAdminPO.ingresarContrasenia(contrasenia);
        this.createAdminPO.ingresarPais(pais);
        this.createTesterPO.ingresarRol(idRol);
        this.createAdminPO.btnRegClick();
    }

}
