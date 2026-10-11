package com.tatf.adminCes.deletUs.test;

import com.tatf.adminCes.base.task.GeneralTask;
import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.deletUs.task.DeletUsTask;
import com.tatf.adminCes.login.task.LoguinTask;

import com.tatf.adminCes.viewUs.task.ViewUsTask;
import com.tatf.core.element.Element;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.deletUs.data.DeletUsData.*;
import static com.tatf.adminCes.login.data.LoguinData.*;

public class DeletUsTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/deletUs.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("positivo")
    void eliminarCuentaTester(String nombre, String apellido, String email,String rol,String validEmail, String validRol) {
        LoguinTask loguinTask = new LoguinTask(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);
        DeletUsTask deletUsTask = new DeletUsTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);


        loguinTask.loguinAdmin(EMAIL, CONTRASENIA);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Sesión de admin iniciada");
        generalTask.confirmarModal();

        viewUsTask.ingresarViewUs();
        Element fila = viewUsTask.buscarFila(nombre, apellido, email, rol);
        verify.verifyNotNull(fila, "El usuario a eliminar existe en la tabla");


        deletUsTask.eliminarUsuario(fila);

        verify.verify(MSJ_CONF_ELIM_US_PREF + email + MSJ_CONF_ELIM_US_FIN, generalTask.obtenerMsjModal(), "Mensaje de confirmación al eliminar");
        generalTask.confirmarModal();


        verify.verify(MSJ_ELIM_USUARIO, generalTask.obtenerMsjModal(), "Usuario eliminado");
        generalTask.confirmarModal();

        Element filaEliminada = viewUsTask.buscarFila(nombre, apellido, validEmail, validRol);
        verify.verifyNull(filaEliminada, "El usuario ya no aparece en la tabla");
    }

}
