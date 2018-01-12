package com.comeon.automationWorld.casinoApi.factories;

import com.comeon.automationWorld.WebDriverAPI.WebDriverInstance;
import com.comeon.automationWorld.casinoApi.implementations.CasinoDesktop;
import com.comeon.automationWorld.casinoApi.implementations.CasinoMobile;
import com.comeon.automationWorld.casinoApi.interfaces.ICasinoApi;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

public class CasinoFactory {

        WebDriver driver;
        String testdata;
        ICasinoApi casino;

        public CasinoFactory(String driverType, WebDriverInstance session, String testdata) throws MalformedURLException {
            this.driver = session.getCurrentDriver();

            if (driverType.equalsIgnoreCase("DESKTOP")) {
                casino = new CasinoDesktop(this.driver, testdata);
            } else if (driverType.equalsIgnoreCase("MOBILE")) {
                casino = new CasinoMobile(this.driver, testdata);
            } else {
                casino = null;
            }
        }

        public ICasinoApi getImplementation() {
            return casino;
        }
}
