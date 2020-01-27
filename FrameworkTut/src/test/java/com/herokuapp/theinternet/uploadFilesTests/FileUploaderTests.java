package com.herokuapp.theinternet.uploadFilesTests;

import com.herokuapp.theinternet.pages.UploadFiles;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.TestEngine;

public class FileUploaderTests extends TestEngine {

    @Test
    void checkUploadedFileStatus(){
    String messageText = new UploadFiles(getDriver(), log)
            .openUploadFilesPage()
            .selectFileToUpload("1.txt")
            .clickUploadButton()
            .getMessageText();
        Assert.assertEquals(messageText,"1.txt");
}
}