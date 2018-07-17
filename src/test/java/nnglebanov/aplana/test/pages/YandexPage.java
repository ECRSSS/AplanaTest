package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.base.BasePage;
import nnglebanov.aplana.utils.DriverWaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YandexPage extends BasePage {

    @Override
    public String getUrl() {
        return "https://www.yandex.ru/";
    }

    @FindBy(css = "a[data-id='market']")
    private WebElement marketLink;


    public void moveToMarket() throws Exception {
        DriverWaitUtils.waitFor(marketLink, 10);
        marketLink.click();
    }
}
