package com.herokuapp.theinternet.horizontalSliderTests;

import com.herokuapp.theinternet.pages.HorizontalSliderPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testEngine.TestEngine;

public class HorizontalSliderTests extends TestEngine {
    @Test
    void setSliderToCurrentValue(){
        HorizontalSliderPage horizontalSliderPage=new HorizontalSliderPage(getDriver(),log)
                .openPage()
                .moveSliderToCurrentValue(1.0);
        Assert.assertEquals(horizontalSliderPage.getCurrentValue(),1.0);
    }

    @Test
    void setSliderToCurrentValueUsingSteps(){
        HorizontalSliderPage horizontalSliderPage=new HorizontalSliderPage(getDriver(),log)
                .openPage()
                .moveSliderToCurrentValueUsingSteps(3.5);
        Assert.assertEquals(horizontalSliderPage.getCurrentValue(),3.5);
    }
}