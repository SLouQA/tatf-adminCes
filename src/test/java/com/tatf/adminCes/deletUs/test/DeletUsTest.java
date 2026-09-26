package com.tatf.adminCes.deletUs.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.deletUs.pom.DeletUsPO;
import com.tatf.adminCes.deletUs.task.DeletUsTask;
import com.tatf.adminCes.login.pom.LoguinPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.viewUs.pom.ViewUsPO;
import com.tatf.adminCes.viewUs.task.ViewUsTask;
import com.tatf.core.element.Element;
import org.junit.jupiter.api.Test;

import static com.tatf.adminCes.login.data.LoguinData.*;
import static com.tatf.adminCes.viewUs.data.ViewUsData.*;

public class DeletUsTest extends BaseTest {

    @Test
    void eliminarCuentaTester () {
        LoguinPO loguinPO = new LoguinPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        ViewUsPO viewUsPO = new ViewUsPO(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);
        DeletUsPO deletUsPO = new DeletUsPO(browser);
        DeletUsTask deletUsTask = new DeletUsTask(browser);



        loguinPO.ingresarInSes();
        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);

        String textoObtenidoLogin = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoLogin);

        generalPO.okClick();

        viewUsPO.ingresarViewUs();
        Element fila = viewUsTask.buscarFila(EXIST_NOMBRE, EXIST_APELLIDO, EXIST_EMAIL, EXIST_ROL);
        viewUsTask.verifyUsExist(fila);

        assert fila != null;
        deletUsPO.deletClick(fila);

        String textoConfDelet = generalPO.getMsjModal();
        deletUsTask.verifyConfDelet(textoConfDelet);

        generalPO.okClick();

        String textoObtenidoDelet = generalPO.getMsjModal();
        deletUsTask.verifyDeletOk(textoObtenidoDelet);

        generalPO.okClick();

        Element filaEliminada = viewUsTask.buscarFila(EXIST_NOMBRE, EXIST_APELLIDO, EXIST_EMAIL, EXIST_ROL);
        viewUsTask.verifyUsNotExist(filaEliminada);

    }
}
