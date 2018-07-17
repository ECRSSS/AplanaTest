package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketMainPage extends BasePage {

    @FindBy(css = "ul.topmenu__list li:nth-child(1)>a:nth-child(1)")
    private WebElement electronics;

    public void moveToElectronics() {
        electronics.click();
    }

    @Override
    public String getUrl() {
        return "https://market.yandex.ru/";
    }
}
