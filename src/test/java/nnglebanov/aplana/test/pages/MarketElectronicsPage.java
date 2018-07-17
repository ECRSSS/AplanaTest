package nnglebanov.aplana.test.pages;

import nnglebanov.aplana.base.BasePage;
import nnglebanov.aplana.utils.DriverWaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MarketElectronicsPage extends BasePage {


    @Override
    public String getUrl() {
        return "https://market.yandex.ru/catalog/54440?hid=198119&track=menu";
    }

    @FindBy(css = "div.catalog-menu.i-bem.catalog-menu_js_inited>div.catalog-menu__item:nth-child(2)>div.catalog-menu__list>a:nth-child(1)")
    private WebElement headphones;

    @FindBy(css = "div.catalog-menu.i-bem.catalog-menu_js_inited>div.catalog-menu__item:nth-child(3)>div.catalog-menu__list>a:nth-child(1)")
    private WebElement tv;


    public void moveToHeadphones() throws Exception {
        DriverWaitUtils.waitFor(headphones, 10);
        headphones.click();
    }

    public void moveToTelevisions() throws Exception {
        DriverWaitUtils.waitFor(tv, 10);
        tv.click();
    }
}
