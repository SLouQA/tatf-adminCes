package com.tatf.adminCes.deletUs.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import static com.tatf.adminCes.deletUs.data.DeletUsData.*;

public class DeletUsTask {
    private final IBrowser browser;
    private final IVerify verify;

    public DeletUsTask(IBrowser browser) {
        this.browser = browser;
        this.verify = IVerify.create();
    }

    public void verifyConfDelet(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_CONF_ELIM_USUARIO, "Conf al eliminar");
    }

    public void verifyDeletOk(String textoObtenido) {
        verify.verify(textoObtenido, MSJ_ELIM_USUARIO, "Usuario eliminado");
    }
}
