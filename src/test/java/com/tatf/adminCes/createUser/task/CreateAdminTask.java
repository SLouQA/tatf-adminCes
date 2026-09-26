package com.tatf.adminCes.createUser.task;

import com.tatf.adminCes.createUser.pom.CreateAdminPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import static com.tatf.adminCes.createUser.data.CreateUserData.*;

public class CreateAdminTask {
    private final IBrowser browser;
    private final CreateAdminPO createAdminPO;
    private final IVerify verify;

    public CreateAdminTask(IBrowser browser) {
        this.browser = browser;
        this.createAdminPO = new CreateAdminPO(this.browser);
        this.verify = IVerify.create();
    }

    public void regAdmin(String nombre, String apellido, String email, String contrasenia, String pais){
        this.createAdminPO.ingresarNombre(nombre);
        this.createAdminPO.ingresarApellido(apellido);
        this.createAdminPO.ingresarEmail(email);
        this.createAdminPO.ingresarContrasenia(contrasenia);
        this.createAdminPO.ingresarContraseniaRepeat(contrasenia);
        this.createAdminPO.ingresarPais(pais);
        this.createAdminPO.btnRegClick();
    }

    public void verifyCreateOk(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_USUARIO_OK, "Usuario creado OK");
    }

}
