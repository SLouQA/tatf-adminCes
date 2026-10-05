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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.tatf.adminCes.login.data.LoguinData.*;

public class DeletUsTest extends BaseTest {

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(
            resources = "/deletUs.csv",
            useHeadersInDisplayName = true,
            delimiter = ';'
    )
    void eliminarCuentaTester(String nombre, String apellido, String email,String rol,String validEmail, String validRol) {
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
        Element fila = viewUsTask.buscarFila(nombre, apellido, email, rol);
        viewUsTask.verifyUsExist(fila);

        assert fila != null;
        deletUsPO.deletClick(fila);

        String textoConfDelet = generalPO.getMsjModal();
        deletUsTask.verifyConfDelet(textoConfDelet, email);

        generalPO.okClick();

        String textoObtenidoDelet = generalPO.getMsjModal();
        deletUsTask.verifyDeletOk(textoObtenidoDelet);

        generalPO.okClick();

        Element filaEliminada = viewUsTask.buscarFila(nombre, apellido, validEmail, validRol);
        viewUsTask.verifyUsNotExist(filaEliminada);
    }

}
