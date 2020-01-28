package com.herokuapp.theinternet.wysiwygEditorTests;

import com.herokuapp.theinternet.pages.WelcomePage;
import com.herokuapp.theinternet.pages.WysiwygEditorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.testEngine.TestEngine;

public class EditorTests extends TestEngine {

    @Test
    public void sendTextIntoEditor() {
        WysiwygEditorPage wysiwygEditorPage = new WelcomePage(getDriver(), log)
                .openPage()
                .clickOnWysiwygEditorLink()
                .clickOnTextFieldAndTypeText("TEXT WITCH SOLVES ALL PROBLEMS");
        Assert.assertEquals(wysiwygEditorPage.find(By.xpath("//body[@id='tinymce']")).getText(),
                "TEXT WITCH SOLVES ALL PROBLEMS");
    }

    @Test
    public void sendTextUsingActions(){
        new WelcomePage(getDriver(), log)
                .openPage()
                .clickOnWysiwygEditorLink()
                .pressKeysWithActions(Keys.SPACE);
    }
}