package com.tatf.adminCes.resetPassAdmin.test;

import com.tatf.adminCes.base.task.GeneralTask;
import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.login.task.LoguinTask;
import com.tatf.adminCes.resetPassAdmin.task.ResetPassAdminTask;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.login.data.LoguinData.MSJ_SESION_OK;
import static com.tatf.adminCes.resetPassAdmin.data.ResetPassAdminData.*;


public class ResetPassAdminTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassAdminOK.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("positivo")
    void resetPassAdminOk(String email, String contrasenia, String repCont ) {
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);
        verify.verify(MSJ_RESET_CONTRASENIA, generalTask.obtenerMsjModal(), "Contraseña reiniciada OK");
        generalTask.confirmarModal();

        loguinTask.loguinAdmin(email, contrasenia);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Inicio de sesión con la nueva contraseña");
        generalTask.confirmarModal();
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassTester.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("negativo")
    void resetPassNoAdmin(String email, String contrasenia, String repCont ) {
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);

        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);

        verify.verify(MSJ_RESET_US_NOADMIN, generalTask.obtenerMsjModal(), "Usuario no admin");
        generalTask.confirmarModal();
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetPassNoIgual.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("negativo")
    void resetPassNoIgual(String email, String contrasenia, String repCont ) {
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);

        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);
        verify.verify(MSJ_RESET_CONT_NOCOINCIDE, generalTask.obtenerMsjModal(), "Las contraseñas no coinciden");
        generalTask.confirmarModal();
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/resetFormVacio.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("negativo")
    void resetFormVacio(String email, String contrasenia, String repCont ) {
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);

        resetPassAdminTask.resetContraseniaAdmin(email, contrasenia, repCont);
        verify.verify(MSJ_RESET_CONT_VACIO, generalTask.obtenerMsjModal(), "Formulario vacío");
        generalTask.confirmarModal();
    }

    @Test
    @Tag("negativo")
    void resetUsNoExiste() {
        ResetPassAdminTask resetPassAdminTask = new ResetPassAdminTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);

        resetPassAdminTask.resetContraseniaAdmin(EMAIL_NO_EXISTE, CONT_NO_EXISTE, CONT_NO_EXISTE);
        verify.verify(MSJ_RESET_US_NOTEXIST, generalTask.obtenerMsjModal(), "Usuario no existe");
        generalTask.confirmarModal();
    }

}

