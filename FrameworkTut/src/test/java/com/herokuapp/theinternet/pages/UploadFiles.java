package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.allure.annotations.Step;

public class UploadFiles extends BasePage {

    String pageUrl = "http://the-internet.herokuapp.com/upload";

    By chooseFileToUpload = By.id("file-upload");
    By submitFile = By.id("file-submit");
    By uploadedFileName = By.id("uploaded-files");

    public UploadFiles(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Step
    public UploadFiles openUploadFilesPage() {
        log.info("Opening page " + pageUrl + " ...");
        openUrl(pageUrl);
        log.info("Page " + pageUrl + " has been opened.");
        return this;
    }

    @Step
    public UploadFiles clickFileToUpload() {
        log.info("Clicking by Link File to upload...");
        click(chooseFileToUpload);
        return this;
    }

    @Step
    public UploadFiles selectFileToUpload(String fileName) {
        log.info("Selecting File [" + fileName + "] to upload...");
        String filePath = System
                .getProperty("user.dir")
                .concat("\\src\\test\\resources\\")
                .concat(fileName);
        type(filePath, chooseFileToUpload);
        log.info("File [" + fileName + "] has been uploaded...");
        return this;
    }

    @Step
    public UploadFiles clickUploadButton() {
        log.info("Push [upload] Button...");
        click(submitFile);
        return this;
    }

    @Step
    public String getMessageText() {
        log.info("Checking uploading status...");
        log.info("Uploading status is " + find(uploadedFileName).getText());
        return find(uploadedFileName).getText();
    }
}