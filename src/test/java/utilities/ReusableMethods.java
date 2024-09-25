package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReusableMethods {

    public static void wait(int seconds){

        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> ConvertToStringList(List<WebElement> webElementList){

        List<String> stringList = new ArrayList<>();

        for (WebElement eachElement : webElementList
        ) {

            stringList.add(eachElement.getText());
        }

        return stringList;
    }

    public static void ChangeWindowWithTitle(String targetTitle, WebDriver driver){

        Set<String> whdSet = driver.getWindowHandles();


        for (String eachWhd : whdSet
        ) {
            driver.switchTo().window(eachWhd);

            String currentTitle = driver.getTitle();

            if (currentTitle.equals(targetTitle)){

                break;
            }
        }

    }

    public static void PageSreenshot(WebDriver driver,String ssName)  {
        // 1- bir TakesScreenShot objesi olusturun ve deger olarak driver'i atayin

        TakesScreenshot tss = (TakesScreenshot) driver;

        // 2- screenshot'i kaydedecegimiz bir dosya olusturalim

        File tumSayfaScreenshot = new File("target/tumSayfaScreenshot/"+ssName+".jpeg");

        // 3- tss objesini kullanarak screenshot alin ve bir File olarak kaydedin

        File geciciDosya = tss.getScreenshotAs(OutputType.FILE);

        // 4- gecici dosyayi deger olarak asil kaydedilecek File'a kopyalayin

        try {
            FileUtils.copyFile(geciciDosya,tumSayfaScreenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void webElementScreenshot(WebElement webElement,String ssName){
        // 1- screenshot alacaginiz webelementi locate edip kaydedin
        // 2- screenshot'i kaydedecegimiz dosyayi olusturun
        //    screenshot ismini unique yapabilmek icin, timestamp ekleyelim
        LocalDateTime ldt = LocalDateTime.now(); // 2024-01-24T19:01:05.777116
        DateTimeFormatter zamanFormati = DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String timeStamp = ldt.format(zamanFormati); // 240124190341

        File webelementSS = new File("target/webelementScreenshots/"+ssName+timeStamp+".jpg");
        // 3- webelementi kullanarak screeshot alin ve gecici dosyaya kaydedin
        File geciciScreenshot = webElement.getScreenshotAs(OutputType.FILE);
        // 4- gecici dosyayi asil dosyaya kopyalayalim
        try {
            FileUtils.copyFile(geciciScreenshot,webelementSS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}