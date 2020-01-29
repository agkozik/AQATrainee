package com.herokuapp.theinternet.dragAndDropTests;

import com.herokuapp.theinternet.pages.DragAndDropPage;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.herokuapp.theinternet.testEngine.TestEngine;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Listeners({com.herokuapp.theinternet.testEngine.TestListener.class})
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
    @Story("Checking drag and drop opportunity")
    @Description("Select A drag A to B Using JavaScript Executor")
    @Severity(SeverityLevel.MINOR)
    void dragAtoBUsingExecutor(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage();
        dragAndDropPage.dragAndDropUsingJavascript(dragAndDropPage.getElementA(),dragAndDropPage.getElementB());
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }

    @Test
    @Story("Checking drag and drop opportunity")
    @Description("Select B drag B to A Using JavaScript Executor")
    @Severity(SeverityLevel.MINOR)
    void dragBtoAUsingExecutor(){
        DragAndDropPage dragAndDropPage = new DragAndDropPage(getDriver(),log)
                .openPage();
        dragAndDropPage.dragAndDropUsingJavascript(dragAndDropPage.getElementB(),dragAndDropPage.getElementA());
        Assert.assertEquals(dragAndDropPage.getTextFromLeftElement(),"B");
        Assert.assertEquals(dragAndDropPage.getTextFromRightElement(),"A");
    }
}