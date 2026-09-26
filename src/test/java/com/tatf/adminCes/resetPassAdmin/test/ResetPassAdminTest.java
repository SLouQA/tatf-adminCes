package com.tatf.adminCes.resetPassAdmin.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.resetPassAdmin.pom.ResetPassAdminPO;
import com.tatf.adminCes.resetPassAdmin.task.ResetPassAdminTask;
import org.junit.jupiter.api.Test;

import static com.tatf.adminCes.login.data.LoguinData.*;
import static com.tatf.adminCes.resetPassAdmin.data.ResetPassAdminData.*;

public class ResetPassAdminTest extends BaseTest {

    @Test
    void resetPassAdmin() {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin(EMAIL, NUEVA_CONTRASENIA);

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetOk(textoObtenidoModal);

        generalPO.okClick();

        loguinTask.loguinAdmin(EMAIL, NUEVA_CONTRASENIA);

        String textoObtenidoVerify = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoVerify);

        generalPO.okClick();
    }
}

