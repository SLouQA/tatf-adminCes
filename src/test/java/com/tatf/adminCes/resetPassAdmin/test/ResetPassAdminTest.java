package com.tatf.adminCes.resetPassAdmin.test;

import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.base.pom.GeneralPO;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.resetPassAdmin.pom.ResetPassAdminPO;
import com.tatf.adminCes.resetPassAdmin.task.ResetPassAdminTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class ResetPassAdminTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassAdminOK.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void resetPassAdminOk(String email, String contrasenia, String repCont ) {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetAdminOk(textoObtenidoModal);

        generalPO.okClick();

        loguinTask.loguinAdmin(email, contrasenia);

        String textoObtenidoVerify = generalPO.getMsjModal();
        loguinTask.verifyLoguinAdminOk(textoObtenidoVerify);

        generalPO.okClick();
    }
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassTester.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void resetPassNoAdmin(String email, String contrasenia, String repCont ) {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetUsNoAdmin(textoObtenidoModal);
        generalPO.okClick();
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassNoIgual.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void resetPassNoIgual(String email, String contrasenia, String repCont ) {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetPassNoIgual(textoObtenidoModal);
        generalPO.okClick();
    }
    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetFormVacio.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void resetFormVacio(String email, String contrasenia, String repCont ) {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetFormVacio(textoObtenidoModal);
        generalPO.okClick();
    }
    @Test
    void resetUsNoExiste() {
        ResetPassAdminPO resetPassAdminPO = new ResetPassAdminPO(browser);
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralPO generalPO = new GeneralPO(browser);

        resetPassAdminPO.ingresarResetPassAdmin();
        resetPassAdminTask.resetContraseniaAdmin("prueba@yopmail.com", "213", "213");

        String textoObtenidoModal = generalPO.getMsjModal();
        resetPassAdminTask.verifyResetUsNoExiste(textoObtenidoModal);
        generalPO.okClick();
    }

}

