package demo.test.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by Mariya on 18.04.2016.
 */
public class CatalogForm extends BaseForm {

    private String locCatalog = "//li/span/span[text()='%s']";
    private Label lblCatalog;
    private String locSection = "//li/span/a[text()='%s']";
    private Label lblSection;
    private String locTesting = "//h1[text()='%s']";
    private Label lblTesting;
    private Label lbLogo = new Label(By.xpath("//div/ul/li/span/span[@class='catalog-navigation-classifier__item-title-wrapper']"), "Logo");

    /**
     * Конструктор
     */
    public CatalogForm() {
        super(By.className("catalog-navigation-classifier__item-title-wrapper"), "Onliner.by");
    }

    /**
     * Проверка на присутствие элемента
     */
    public void assertLogo(){

        assert(lbLogo.isPresent());
    }



    /**
     * Находим и выбираем раздел каталога
     * @param catalog каталог
     */
    public void checkCatalog(String catalog){
        lblCatalog = new Label (By.xpath((String.format(locCatalog,catalog))), "Catalog");
        lblCatalog.click();
        browser.waitForPageToLoad();
    }

    /**
     * Находим и выбираем секцию в разделе
     * @param section каталог
     */
    public void checkSection(String section){
        lblSection = new Label (By.xpath((String.format(locSection,section))), "Section");
        lblSection.click();
        browser.waitForPageToLoad();
    }

    /**
     * Проверяем загрузилась ли страница с выбранной секцией
     * @param section каталог
     */
    public void testingSection(String section){
        lblTesting = new Label(By.xpath((String.format(locTesting,section))), "Item");
        lblTesting.waitForIsElementPresent();
        Assert.assertTrue(lblTesting.isPresent());
    }



}
