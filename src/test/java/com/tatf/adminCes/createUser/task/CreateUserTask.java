package com.tatf.adminCes.createUser.task;

import com.tatf.adminCes.createUser.pom.CreateUserPO;
import com.tatf.core.browser.IBrowser;

public class CreateUserTask {
    private final CreateUserPO createUserPO;

    public CreateUserTask(IBrowser browser) {
        this.createUserPO = new CreateUserPO(browser);
    }



    public void regAdmin(String nombre, String apellido, String email, String contrasenia, String pais){
        this.createUserPO.ingresarRegistrarse();
        this.createUserPO.ingresarNombre(nombre);
        this.createUserPO.ingresarApellido(apellido);
        this.createUserPO.ingresarEmail(email);
        this.createUserPO.ingresarContrasenia(contrasenia);
        this.createUserPO.ingresarContraseniaRepeat(contrasenia);
        this.createUserPO.ingresarPais(pais);
        this.createUserPO.btnRegClick();
    }

    public void regTester(String nombre, String apellido, String email, String contrasenia, String pais, String idRol){
        this.createUserPO.ingresarRegTester();
        this.createUserPO.ingresarNombre(nombre);
        this.createUserPO.ingresarApellido(apellido);
        this.createUserPO.ingresarEmail(email);
        this.createUserPO.ingresarContrasenia(contrasenia);
        this.createUserPO.ingresarPais(pais);
        this.createUserPO.ingresarRol(idRol);
        this.createUserPO.btnRegClick();
    }
}
