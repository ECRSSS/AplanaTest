package nnglebanov.aplana.base;


import nnglebanov.aplana.driverfactory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage
{
    protected WebDriver driver;

    public BasePage()
    {
        driver=DriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver,this);
    }

    public void open() {
        driver.get(getUrl());
    }

    protected abstract String getUrl();

}