package tests;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class SocietyPageTests {
    @Test
    public void checkMainNews(){
        open("/society");
    }
}