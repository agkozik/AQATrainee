package com.herokuapp.theinternet.dragAndDropTests;

import com.herokuapp.theinternet.pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import testEngine.TestEngine;

public class DragAndDrop extends TestEngine {
    @Ignore
    @Test
    void dragAtoB(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage()
                .dragAndDropUsingActions();
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }

    @Ignore
    @Test
    void dragAtoBUsingClickAndHold(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage()
                .clickAndHoldAAndMoveToB();
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }

    @Test
    void dragAtoBUsingExecutor(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage();
        dragAndDropPage.dragAndDropUsingJavascript(dragAndDropPage.getElementA(),dragAndDropPage.getElementB());
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }

    @Test
    void dragBtoAUsingExecutor(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage();
        dragAndDropPage.dragAndDropUsingJavascript(dragAndDropPage.getElementB(),dragAndDropPage.getElementA());
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }
}