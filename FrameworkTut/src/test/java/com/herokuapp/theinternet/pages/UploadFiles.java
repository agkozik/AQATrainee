package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadFiles extends BasePage {

    String pageUrl = "http://the-internet.herokuapp.com/upload";

    By chooseFileToUpload = By.id("file-upload");
    By submitFile = By.id("file-submit");
    By uploadedFileName = By.id("uploaded-files");

    public UploadFiles(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public UploadFiles openUploadFilesPage(){
        log.info("Opening page "+pageUrl+" ...");
        openUrl(pageUrl);
        log.info("Page "+pageUrl+" has been opened.");
        return this;
    }

    public UploadFiles clickFileToUpload() {
        log.info("Clicking by Link File to upload...");
        click(chooseFileToUpload);
        return this;
    }

    public UploadFiles selectFileToUpload(String fileName){
        log.info("Selecting File ["+fileName+"] to upload...");
        String filePath = System
                .getProperty("user.dir")
                .concat("\\src\\test\\resources\\")
                .concat(fileName);
        type(filePath,chooseFileToUpload);
        log.info("File ["+fileName+"] has been uploaded...");
        return this;
    }

    public UploadFiles clickUploadButton(){
        log.info("Push [upload] Button...");
        click(submitFile);
        return this;
    }

    public String getMessageText(){
        log.info("Checking uploading status...");
        log.info("Uploading status is "+find(uploadedFileName).getText());
        return find(uploadedFileName).getText();
    }
}