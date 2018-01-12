package com.comeon.automationWorld.casinoApi.implementations;

import com.comeon.automationWorld.casinoApi.interfaces.ICasinoApi;
import com.comeon.automationWorld.dataObjects.Results;
import org.openqa.selenium.*;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class CasinoMobile implements ICasinoApi {

    WebDriver driver;
    String testdata;
    Results r = new Results();
    Screen screen = new Screen();

    public CasinoMobile(WebDriver driver, String testdata) {
        this.driver = driver;
        this.testdata = testdata;
    }

    /**
     * Click a Casino game element by image
     *
     * @return Results
     */

    public Results clickByImage() {
        try {
            Thread.sleep(4000);
            String path = System.getProperty("user.dir") + "/src/main/resources/images/";
            System.out.println(path + testdata);
            Match m = screen.exists(new Pattern(path + testdata).similar((float) 0.75));
            if (m != null) {
                m.click();
                r.setStatus("PASSED");
            } else {
                r.setStatus("FAILED");
                r.setMessage("MSG:  Unable to locate image:  " + testdata);
            }
        } catch (Exception e) {
            r.setStatus("BLOCKED");
            r.setMessage(e.getMessage());
        }
        return r;
    }

    /**
     * ClicByRigion are calculating a region on the screen and highlight it. The rigion are gona
     * be clicked afterthat if it find the image.
     *
     * @return Results
     */

    public Results clickByRegion() {
        try {
            Rectangle rect = getGamePortalRegion();
            Region region = new Region(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());

            region.highlight(3);

            if (screen.exists(new Pattern(testdata).similar((float) 0.75)) != null) {
                screen.aTap(screen.exists(new Pattern(testdata).similar((float) 0.75)));
                r.setStatus("PASSED");
            }else{
                r.setStatus("FAILED");
                r.setMessage("MSG: Uuable to locate image: " + testdata);
            }
        } catch (Exception e) {
            {
                r.setStatus("BLOCKED");
                r.setMessage(e.getMessage());
            }
        }
        return r;
    }


    //This is a supporting utility method for the ClickByRegion method

    /**
     * This is a methed to calculate how big or small the region is gona be when the test starts.
     *
     * @return Results
     */

    public Rectangle getGamePortalRegion() {
        Rectangle rect = null;
        try {
            Thread.sleep(4000);

            WebElement portal = driver.findElement(By.cssSelector("#viewport"));

            Point p = portal.getLocation();
            Dimension d = portal.getSize();
            rect = new Rectangle(p.getX() + 10, p.getY() + 10, d.getHeight() + 10, d.getWidth() + 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rect;
    }

}
