package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.createUser.pom.CreateAdminPO;
import com.tatf.adminCes.createUser.task.CreateAdminTask;
import com.tatf.adminCes.login.task.LoguinTask;
import org.junit.jupiter.api.Test;

import static com.tatf.adminCes.createUser.data.CreateUserData.*;

public class CreateAdminTest extends BaseTest {

    @Test
    void crearCuentaAdmin() {
        CreateAdminPO createAdminPO = new CreateAdminPO(browser);
        CreateAdminTask createAdminTask = new CreateAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        createAdminPO.ingresarRegistrarse();
        createAdminTask.regAdmin(PRUEBA_NOMBRE, PRUEBA_APELLIDO, PRUEBA_EMAIL, PRUEBA_CONTRASENIA, PRUEBA_PAIS);

        String textoObtenidoModal = generalPO.getMsjModal();
        createAdminTask.verifyCreateOk(textoObtenidoModal);

        generalPO.okClick();

        loguinTask.loguinAdmin(PRUEBA_EMAIL, PRUEBA_CONTRASENIA);

        String textoObtenidoVerify = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoVerify);
    }

}
