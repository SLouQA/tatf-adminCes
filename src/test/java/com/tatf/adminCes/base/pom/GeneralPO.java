package com.tatf.adminCes.base.pom;

import com.tatf.core.browser.IBrowser;

public class GeneralPO {

    private final IBrowser browser;

    public GeneralPO(IBrowser browser) {
        this.browser = browser;
    }

    public String getMsjModal(){
        return this.browser.find().id("swal2-html-container").getText();}

    public void okClick(){this.browser.find().className("swal2-confirm").click();}


}
