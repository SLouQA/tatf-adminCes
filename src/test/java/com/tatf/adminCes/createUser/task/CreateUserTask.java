package com.tatf.adminCes.createUser.task;

import com.tatf.adminCes.createUser.pom.CreateUserPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import static com.tatf.adminCes.createUser.data.CreateUserData.*;

public class CreateUserTask {
    private final IBrowser browser;
    private final CreateUserPO createUserPO;

    private final IVerify verify;

    public CreateUserTask(IBrowser browser) {
        this.browser = browser;
        this.createUserPO = new CreateUserPO(this.browser);
        this.verify = IVerify.create();
    }

    public void regAdmin(String nombre, String apellido, String email, String contrasenia, String pais){
        this.createUserPO.ingresarNombre(nombre);
        this.createUserPO.ingresarApellido(apellido);
        this.createUserPO.ingresarEmail(email);
        this.createUserPO.ingresarContrasenia(contrasenia);
        this.createUserPO.ingresarContraseniaRepeat(contrasenia);
        this.createUserPO.ingresarPais(pais);
        this.createUserPO.btnRegClick();
    }

    public void regTester(String nombre, String apellido, String email, String contrasenia, String pais, String idRol){
        this.createUserPO.ingresarNombre(nombre);
        this.createUserPO.ingresarApellido(apellido);
        this.createUserPO.ingresarEmail(email);
        this.createUserPO.ingresarContrasenia(contrasenia);
        this.createUserPO.ingresarPais(pais);
        this.createUserPO.ingresarRol(idRol);
        this.createUserPO.btnRegClick();
    }
    public void verifyCreateOk(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_USUARIO_OK, "Usuario creado OK");
    }

    public void verifyCreateFormVac(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_US_FORM_VACIO, "Formulario Vacio");
    }

    public void verifyCreateErrorCont(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_US_ERROR_CONT, "Contraseñas diferentes");
    }

    public void verifyCreateExiste(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_US_EXISTE, "Usuario ya existente");
    }
}
