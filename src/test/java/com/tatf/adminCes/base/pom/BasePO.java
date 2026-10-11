package com.tatf.adminCes.base.pom;

import com.tatf.core.browser.IBrowser;

public class BasePO {

    private static final String MODAL_MENSAJE = "swal2-html-container";
    private static final String BTN_MODAL_OK = "swal2-confirm";
    private static final String INPUT_EMAIL="inputEmail";
    private static final String INPUT_PASSWORD="inputPassword";
    private static final String INPUT_REPEAT_PASSWORD="inputRepeatPassword";

    protected final IBrowser browser;

    public BasePO(IBrowser browser) {
        this.browser = browser;
    }

    public String getMsjModal(){
        return this.browser.find().id(MODAL_MENSAJE).getText();}

    public void okClick(){this.browser.find().className(BTN_MODAL_OK).click();}

    public void ingresarEmail(String email) {
        this.browser.find().name(INPUT_EMAIL).write(email);
    }

    public void ingresarContrasenia(String contrasenia) {
        this.browser.find().name(INPUT_PASSWORD).write(contrasenia);
    }

    public void ingresarRepeatContrasenia(String contrasenia) {
        this.browser.find().name(INPUT_REPEAT_PASSWORD).write(contrasenia);
    }
}