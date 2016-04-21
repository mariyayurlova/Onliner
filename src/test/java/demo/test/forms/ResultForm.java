package demo.test.forms;

import org.openqa.selenium.By;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * Created by Mariya on 19.04.2016.
 */
public class ResultForm extends BaseForm {

    private Label lblName = new Label (By.xpath("//div/ol/li/a[contains(text(),'Samsung')]"));
    private Label lblPrice = new Label (By.xpath("//div/a"));
    private Label lbLogo = new Label(By.xpath("//div/h2[@class='catalog-masthead__title']"), "Logo");


    /**
     * Конструктор
     */
    protected ResultForm() {
        super(By.className("b-top-logo"), "Onliner.by");
    }


    /**
     * Проверка на присутствие элемента
     */
    public void assertLogo(){

        assert(lbLogo.isPresent());
    }


    /**
     * Проверяем производителя
     * @param manufacturer производитель
     */
    public boolean testName(String manufacturer){

        Label lblName = new Label (By.xpath("//div/ol/li/a[contains(text(),'Samsung')]"));
        String test = (lblName.getText());
        if (test.equals(manufacturer)){
            return true;
        }
        else{
            error("Name does not match");
            return false;}


    }


    /**
     * Проверяем дату
     * @param date дата
     */
    public boolean testDate(String date){

        Label lblDate = new Label (By.xpath("//tbody/tr[@class='product-specs__table-title']/following-sibling::tr/td/span[@class='value__text']"));
        String dateR = lblDate.getText();
        String result = String.valueOf(dateR.split(" г.")[0]);
        int dateResultFirst = Integer.parseInt(date);
        int dateResultSecond = Integer.parseInt(result);
        if (dateResultFirst<=dateResultSecond){
            return true;
        }
        else
        {
            error("Date does not match");
            return false;
        }


    }

    /**
     * Проверяем цену
     * @param price цена
     */
    public boolean testPrice(String price){

        Label lblPrice = new Label (By.xpath("//div[@class='b-offers-desc__info-price']/div/a"));
        String  priceR = lblPrice.getText();
        String result = String.valueOf(priceR.split(" –")[0]);
        String priceP = price.replaceAll(" ", "");
        String resultR = result.replaceAll(" ", "");
        int priceResultFirst = new Integer(priceP).intValue();
        int priceResultSecond = new Integer(resultR).intValue();
        if (priceResultFirst >= priceResultSecond){
            return true;
        }
        else
        {
            error("Price does not match");
            return false;
        }


    }

    /**
     * Проверяем диагональ
     * @param diagonalFrom диагональ от
     * @param diagonalTo диагональ до
     */
    public boolean testDiagonl(String diagonalFrom, String diagonalTo){

        Label lblDiagonal = new Label (By.xpath("//tbody/following-sibling::tbody/tr[@class='product-specs__table-title']/following-sibling::tr/following-sibling::tr/td/span[@class='value__text']"));

        String  diagonalR = lblDiagonal.getText();

        String result = String.valueOf(diagonalR.split("\"")[0]);
        int diagonalResultFirst = Integer.parseInt(diagonalFrom);
        int diagonalResultSecond = Integer.parseInt(result);
        int diagonalResultThree = Integer.parseInt(diagonalTo);
        if (diagonalResultFirst <= diagonalResultSecond & diagonalResultSecond <= diagonalResultThree){
            return true;
        }
        else
        {
            error("Diagonal does not match");
            return false;
        }

    }

}
