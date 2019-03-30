/**
 * Created by vasilev on 28/03/2019.
 */
package driverprovider;

import org.openqa.selenium.support.ui.WebDriverWait;

import static driverprovider.DriverInstance.getDriver;

public class WaitProvider {

    private WaitProvider() {
    }

    public static WebDriverWait waitProvider() {
        return new WebDriverWait(getDriver(), 20);
    }
}