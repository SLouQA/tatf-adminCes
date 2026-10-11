package com.tatf.adminCes.createUser.test;

import com.tatf.adminCes.base.task.GeneralTask;
import com.tatf.adminCes.base.test.BaseTest;
import com.tatf.adminCes.createUser.task.CreateUserTask;
import com.tatf.adminCes.login.task.LoguinTask;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.createUser.data.CreateUserData.*;
import static com.tatf.adminCes.login.data.LoguinData.MSJ_SESION_OK;


public class CreateAdminTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearAdmin.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("positivo")
    void crearCuentaAdmin(String nombre, String apellido, String email, String contrasenia, String pais) {
        CreateUserTask createUserTask = new CreateUserTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);
        LoguinTask loguinTask = new LoguinTask(browser);

        createUserTask.regAdmin(nombre, apellido, email, contrasenia, pais);
        verify.verify(MSJ_USUARIO_OK, generalTask.obtenerMsjModal(), "Usuario creado OK");
        generalTask.confirmarModal();


        loguinTask.loguinAdmin(email, contrasenia);
        verify.verify(MSJ_SESION_OK, generalTask.obtenerMsjModal(), "Inicio de sesión con el admin creado");

    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/crearAdminEmailR.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    @Tag("negativo")
    void crearCuentaTestereEmailRep(String nombre, String apellido, String email, String contrasenia, String pais,
                                    String nombreD, String apellidoD, String emailR, String contraseniaD, String paisR) {

        CreateUserTask createUserTask = new CreateUserTask(browser);
        GeneralTask generalTask = new GeneralTask(browser);


        createUserTask.regAdmin(nombre, apellido, email, contrasenia, pais);
        verify.verify(MSJ_USUARIO_OK, generalTask.obtenerMsjModal(), "Usuario creado OK");
        generalTask.confirmarModal();

        createUserTask.regAdmin(nombreD, apellidoD, emailR, contraseniaD, paisR);
        verify.verify(MSJ_US_EXISTE, generalTask.obtenerMsjModal(), "Usuario ya existente");
        generalTask.confirmarModal();

// Agregar el resto de las validaciónes

    }
}
