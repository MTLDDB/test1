package textJsoup;

import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.swing.text.Document;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Pjs {
    public static void main(String[] args) throws InterruptedException {
       /* System.setProperty("phantomjs.binary.path", "C:\\Users\\PC\\Desktop\\我的\\phantomjs.exe");

        WebDriver driver = new PhantomJSDriver();
        driver.get("https://www.digikey.com/product-detail/en/vishay-foil-resistors-division-of-vishay-precision-group/Y09261R00000G0L/Y09261R00000G0L-ND/4225380");
        WebElement element = driver.findElement(By.id("newSubsTable"));
        System.out.println(element.getText());
        try {
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();*/


        System.setProperty("webdriver.chrome.driver","C:\\Users\\PC\\Desktop\\我的\\chromedriver.exe");//chromedriver服务地址
        /*String proxyStr = new HttpHost(proxyIp.getIp(), proxyIp.getPort());
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(proxyStr);*/

        ChromeOptions options = new ChromeOptions();

      //  String ip = "58.242.19.5:4270";
        //options.addArguments("--proxy-server=http://" + ip);
        options.addArguments("disable-infobars");//关闭顶部提示
        options.addArguments("--disable-plugins","--disable-images","--start-maximized","--disable-javascript");//禁用插件
        Map<String, Object> prefs = new HashMap<>();
       // prefs.put("permissions.default.stylesheet",2);//不加载css
        prefs.put("profile.managed_default_content_settings.images", 2);//不加载图片
       // prefs.put("profile.default_content_setting_values.notifications", 2);//不加载弹窗
       // options.setExperimentalOption("prefs", prefs);
       // options.addArguments("headless");//无界面参数
       // options.addArguments("no-sandbox");
        WebDriver driver =new ChromeDriver(options); //新建一个WebDriver 的对象，但是new 的是FirefoxDriver的驱动
        driver.get("https://www.verical.com/products/electromechanical-692/power-supplies-704/ac-to-dc-power-supply-768/");
        //查找元素
//        WebElement element = driver.findElement(By.id("newSubsTable"));
//        System.out.println(element.findElements(By.tagName("table")).get(0).getText().toCharArray());
        String document=driver.getPageSource();
       // WebElement element = driver.findElement(By.xpath("html"));
        System.out.println(document);
        try {
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //driver.quit();//退出浏览器

    }
}
