package com.tatf.adminCes.deleteUser.test;

import com.tatf.adminCes.base.task.BaseTask;
import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.deleteUser.task.DeleteUserTask;
import com.tatf.adminCes.login.task.LoginTask;
import com.tatf.adminCes.viewUs.task.ViewUsTask;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.deleteUser.data.DeleteUserData.*;
import static com.tatf.adminCes.login.data.LoginData.*;

public class DeleteUserTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/deletUs.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("positivo")
    void eliminarCuentaTester(String nombre, String apellido, String email,String rol) {
        LoginTask loginTask = new LoginTask(browser);
        ViewUsTask viewUsTask = new ViewUsTask(browser);
        DeleteUserTask deleteUserTask = new DeleteUserTask(browser);
        BaseTask generalTask = new BaseTask(browser);

        loginTask.loginAdmin(EMAIL, CONTRASENIA);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Sesión de admin iniciada");
        generalTask.confirmarModal();

        viewUsTask.ingresarVerUs();
        boolean existe = viewUsTask.existeUsuario(nombre, apellido, email, rol);
        verify.verifyTrue(existe, "El usuario a eliminar existe en la tabla");

        deleteUserTask.eliminarUsuario(nombre, apellido, email, rol);

        verify.verify(MSJ_CONF_ELIM_US_PREF + email + MSJ_CONF_ELIM_US_FIN, generalTask.obtenerMsjModal(), "Mensaje de confirmación al eliminar");
        generalTask.confirmarModal();

        verify.verify(MSJ_ELIM_USUARIO, generalTask.obtenerMsjModal(), "Usuario eliminado");
        generalTask.confirmarModal();

        boolean continuaExistiendo = viewUsTask.existeUsuario(nombre, apellido, email, rol);
        verify.verifyFalse(continuaExistiendo, "El usuario ya no aparece en la tabla");
    }

}
