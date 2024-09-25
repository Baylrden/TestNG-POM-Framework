package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class ExampleTest {

    @Test
    public void testCase01(){
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        //

        ReusableMethods.wait(2);

        String expectedUrl = "https://www.youtube.com/";
        String actualUrl = Driver.getDriver().getCurrentUrl();

        Assert.assertEquals(actualUrl,expectedUrl);
    }
}
