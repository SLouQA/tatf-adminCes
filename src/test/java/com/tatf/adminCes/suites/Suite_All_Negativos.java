package com.tatf.adminCes.suites;

import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Pruebas negativas")
@SelectPackages("com.tatf.adminCes")
@IncludeTags("negativo")
public class Suite_All_Negativos {
}
