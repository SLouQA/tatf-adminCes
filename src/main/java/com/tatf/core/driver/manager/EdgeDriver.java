package com.tatf.core.driver.manager;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriver extends DriverManager{
    public EdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("--ignore-certificate-errors");

        this.driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
        setDefaultConfig();
    }
}
