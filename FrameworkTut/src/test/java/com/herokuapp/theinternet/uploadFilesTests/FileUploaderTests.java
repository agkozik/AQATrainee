package com.herokuapp.theinternet.uploadFilesTests;

import com.herokuapp.theinternet.pages.UploadFiles;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.testEngine.DataToTest;
@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
public class FileUploaderTests extends DataToTest {

    @Test(dataProvider = "files")
    void checkUploadedFileStatus(String testNumber, String fileName){
        log.info("Preparing data for "+testNumber+" uploading "+fileName);
    String messageText = new UploadFiles(getDriver(), log)
            .openUploadFilesPage()
            .selectFileToUpload(fileName)
            .clickUploadButton()
            .getMessageText();
        Assert.assertEquals(messageText,fileName);
}
}