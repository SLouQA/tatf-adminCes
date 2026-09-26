package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.createUser.pom.CreateTesterPO;
import com.tatf.adminCes.createUser.task.CreateAdminTask;
import com.tatf.adminCes.createUser.task.CreateTesterTask;
import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.adminCes.viewUs.task.ViewUsTask;
import com.tatf.core.element.Element;
import org.junit.jupiter.api.Test;

import static com.tatf.adminCes.createUser.data.CreateTesterData.*;
import static com.tatf.adminCes.login.data.LoguinData.*;

public class CreateTesterTest extends BaseTest {
    @Test
    void crearCuentaTester (){
        LoguinPO loguinPO = new LoguinPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        CreateTesterPO createTesterPO = new CreateTesterPO(browser);
        CreateTesterTask createTesterTask = new CreateTesterTask(browser);
        CreateAdminTask createAdminTask = new CreateAdminTask(browser);
        ViewUsPO viewUsPO = new ViewUsPO(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);

        loguinPO.ingresarInSes();
        loguinTask.loguinAdmin(EMAIL,CONTRASENIA);

        String textoObtenidoLogin = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoLogin);

        generalPO.okClick();

        createTesterPO.ingresarRegTester();

        createTesterTask.regTester(PRUEBA_NOMBRE,PRUEBA_APELLIDO, PRUEBA_EMAIL,PRUEBA_CONTRASENIA,PRUEBA_PAIS,ID_ROL_SR);

        String textoObtenidoModal = generalPO.getMsjModal();
        createAdminTask.verifyCreateOk(textoObtenidoModal);

        generalPO.okClick();

        viewUsPO.ingresarViewUs();

        Element fila = viewUsTask.buscarFila(PRUEBA_NOMBRE, PRUEBA_APELLIDO, PRUEBA_EMAIL, VALID_ROL_SR);
        viewUsTask.verifyUsExist(fila);

    }
}
