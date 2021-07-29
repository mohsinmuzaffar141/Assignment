package workbench.pages;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import runners.CucumberRunner;

import java.util.List;

public class assignmentPage extends CucumberRunner {
    private int invalidImageCount;
    String VIN_Number=null;

    public void validateBrokenImages() {
        try {
            invalidImageCount = 0;
            List<WebElement> imagesList = driver.findElements(By.tagName("img"));
            System.out.println("Total no. of images are " + imagesList.size());
            for (WebElement imgElement : imagesList) {
                if (imgElement != null) {
                    verifyBrokenImage(imgElement);
                }
            }
            System.out.println("Total no. of invalid images are "	+ invalidImageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyBrokenImage(WebElement imgElement) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imgElement.getAttribute("src"));
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != 200)
                invalidImageCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyBlankImage() {
        WebElement sliderCount=driver.findElement(By.xpath("(//button[@type='button'])[2]/following::div"));
        WebElement sliderButton=driver.findElement(By.xpath("(//*[local-name() = 'svg'])[2]"));
        WebElement verifyImage=driver.findElement(By.xpath("((//button[@type='button'])[1]/following::div//div)[1]"));
        String[] arrOfStr = sliderCount.getText().split("/ ");
        for (int i=0;i<=Integer.parseInt(arrOfStr[1]);i++){
            String[] arrOfStrTwo = verifyImage.getAttribute("style").split("[\\(,]");
            String[] finalAns=arrOfStrTwo[1].split("px");
            if(Integer.parseInt(finalAns[0])>-2240){
                validateBrokenImages();
                sliderButton.click();
            }else
                Assert.fail();

        }
    }
}