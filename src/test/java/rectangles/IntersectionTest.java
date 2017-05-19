package rectangles;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.validator.ResponsiveUIValidator;
import util.validator.UIValidator;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static rectangles.DummyWebElement.createElement;

@RunWith(Parameterized.class)
public class IntersectionTest {

    private boolean debug = false;

    private final WebElement other;

    private static final int originX = RectangleFixture.originX;
    private static final int cornerX = RectangleFixture.cornerX;
    private static final int originY = RectangleFixture.originY;
    private static final int cornerY = RectangleFixture.cornerY;

    private final WebElement root;
    private boolean intersects;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(paras);
    }

    public IntersectionTest(int otherOriginX, int otherOriginY, int otherCornerX, int otherCornerY, boolean intersects) {
        this.intersects = intersects;
        root = createElement(originX, originY, cornerX, cornerY);
        other = createElement(otherOriginX, otherOriginY, otherCornerX, otherCornerY);
    }

    @Test
    public void shouldOverlap() {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        validator.overlapWith(other, "Bla");
        assertThat(validator.validate())
                .withFailMessage(failMessage())
                .isEqualTo(intersects);
    }

    @Test
    public void shouldNotOverlap() {
        WebDriver driver = new DummyWebDriver();
        ResponsiveUIValidator temporary = new ResponsiveUIValidator(driver).init();

        UIValidator validator = temporary.findElement(root, "Bla");

        validator.notOverlapWith(other, "Bla");
        assertThat(validator.validate())
                .withFailMessage(failMessage())
                .isEqualTo(!intersects);
    }

    private String failMessage() {
        return String.format("%s and %s should %soverlap%s",
                toString(root),
                toString(other),
                intersects ? "": "not ",
                debug ? asSvg() : "");
    }

    private String toString(WebElement webElement) {
        return String.format("[%s@%s-%s@%s]",
                webElement.getLocation().getX(),
                webElement.getLocation().getY(),
                webElement.getLocation().getX() + webElement.getSize().getWidth(),
                webElement.getLocation().getY() + webElement.getSize().getHeight());

    }

    private String asSvg() {
        return String.format(
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<svg width=\"2000\" height=\"2000\">\n" +
                "    <rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"\n" +
                "          style=\"fill:red;;stroke-width:0;fill-opacity:0.5\" />\n" +
                "    <rect x=\"%s\" y=\"%s\" width=\"%s\" height=\"%s\"\n" +
                "          style=\"fill:cyan;;stroke-width:0;fill-opacity:0.5\" />\n" +
                "</svg>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n",
                root.getLocation().getX(),
                root.getLocation().getY(),
                root.getSize().getWidth(),
                root.getSize().getHeight(),
                other.getLocation().getX(),
                other.getLocation().getY(),
                other.getSize().getWidth(),
                other.getSize().getHeight()
        );
    }


    private static Object[][] paras = {
            {100, 300, 598, 998, false},
            {100, 300, 598, 1000, false},
            {100, 300, 598, 1002, false},
            {100, 300, 598, 1698, false},
            {100, 300, 598, 1700, false},
            {100, 300, 598, 1702, false},
            {100, 300, 598, 2400, false},
            {100, 998, 598, 1000, false},
            {100, 998, 598, 1002, false},
            {100, 998, 598, 1698, false},
            {100, 998, 598, 1700, false},
            {100, 998, 598, 1702, false},
            {100, 998, 598, 2400, false},
            {100, 1000, 598, 1002, false},
            {100, 1000, 598, 1698, false},
            {100, 1000, 598, 1700, false},
            {100, 1000, 598, 1702, false},
            {100, 1000, 598, 2400, false},
            {100, 1002, 598, 1698, false},
            {100, 1002, 598, 1700, false},
            {100, 1002, 598, 1702, false},
            {100, 1002, 598, 2400, false},
            {100, 1698, 598, 1700, false},
            {100, 1698, 598, 1702, false},
            {100, 1698, 598, 2400, false},
            {100, 1700, 598, 1702, false},
            {100, 1700, 598, 2400, false},
            {100, 1702, 598, 2400, false},
            {100, 300, 600, 998, false},
            {100, 300, 600, 1000, false},
            {100, 300, 600, 1002, false},
            {100, 300, 600, 1698, false},
            {100, 300, 600, 1700, false},
            {100, 300, 600, 1702, false},
            {100, 300, 600, 2400, false},
            {100, 998, 600, 1000, false},
            {100, 998, 600, 1002, false},
            {100, 998, 600, 1698, false},
            {100, 998, 600, 1700, false},
            {100, 998, 600, 1702, false},
            {100, 998, 600, 2400, false},
            {100, 1000, 600, 1002, false},
            {100, 1000, 600, 1698, false},
            {100, 1000, 600, 1700, false},
            {100, 1000, 600, 1702, false},
            {100, 1000, 600, 2400, false},
            {100, 1002, 600, 1698, false},
            {100, 1002, 600, 1700, false},
            {100, 1002, 600, 1702, false},
            {100, 1002, 600, 2400, false},
            {100, 1698, 600, 1700, false},
            {100, 1698, 600, 1702, false},
            {100, 1698, 600, 2400, false},
            {100, 1700, 600, 1702, false},
            {100, 1700, 600, 2400, false},
            {100, 1702, 600, 2400, false},
            {100, 300, 602, 998, false},
            {100, 300, 602, 1000, false},
            {100, 300, 602, 1002, true},
            {100, 300, 602, 1698, true},
            {100, 300, 602, 1700, true},
            {100, 300, 602, 1702, true},
            {100, 300, 602, 2400, true},
            {100, 998, 602, 1000, false},
            {100, 998, 602, 1002, true},
            {100, 998, 602, 1698, true},
            {100, 998, 602, 1700, true},
            {100, 998, 602, 1702, true},
            {100, 998, 602, 2400, true},
            {100, 1000, 602, 1002, true},
            {100, 1000, 602, 1698, true},
            {100, 1000, 602, 1700, true},
            {100, 1000, 602, 1702, true},
            {100, 1000, 602, 2400, true},
            {100, 1002, 602, 1698, true},
            {100, 1002, 602, 1700, true},
            {100, 1002, 602, 1702, true},
            {100, 1002, 602, 2400, true},
            {100, 1698, 602, 1700, true},
            {100, 1698, 602, 1702, true},
            {100, 1698, 602, 2400, true},
            {100, 1700, 602, 1702, false},
            {100, 1700, 602, 2400, false},
            {100, 1702, 602, 2400, false},
            {100, 300, 1098, 998, false},
            {100, 300, 1098, 1000, false},
            {100, 300, 1098, 1002, true},
            {100, 300, 1098, 1698, true},
            {100, 300, 1098, 1700, true},
            {100, 300, 1098, 1702, true},
            {100, 300, 1098, 2400, true},
            {100, 998, 1098, 1000, false},
            {100, 998, 1098, 1002, true},
            {100, 998, 1098, 1698, true},
            {100, 998, 1098, 1700, true},
            {100, 998, 1098, 1702, true},
            {100, 998, 1098, 2400, true},
            {100, 1000, 1098, 1002, true},
            {100, 1000, 1098, 1698, true},
            {100, 1000, 1098, 1700, true},
            {100, 1000, 1098, 1702, true},
            {100, 1000, 1098, 2400, true},
            {100, 1002, 1098, 1698, true},
            {100, 1002, 1098, 1700, true},
            {100, 1002, 1098, 1702, true},
            {100, 1002, 1098, 2400, true},
            {100, 1698, 1098, 1700, true},
            {100, 1698, 1098, 1702, true},
            {100, 1698, 1098, 2400, true},
            {100, 1700, 1098, 1702, false},
            {100, 1700, 1098, 2400, false},
            {100, 1702, 1098, 2400, false},
            {100, 300, 1100, 998, false},
            {100, 300, 1100, 1000, false},
            {100, 300, 1100, 1002, true},
            {100, 300, 1100, 1698, true},
            {100, 300, 1100, 1700, true},
            {100, 300, 1100, 1702, true},
            {100, 300, 1100, 2400, true},
            {100, 998, 1100, 1000, false},
            {100, 998, 1100, 1002, true},
            {100, 998, 1100, 1698, true},
            {100, 998, 1100, 1700, true},
            {100, 998, 1100, 1702, true},
            {100, 998, 1100, 2400, true},
            {100, 1000, 1100, 1002, true},
            {100, 1000, 1100, 1698, true},
            {100, 1000, 1100, 1700, true},
            {100, 1000, 1100, 1702, true},
            {100, 1000, 1100, 2400, true},
            {100, 1002, 1100, 1698, true},
            {100, 1002, 1100, 1700, true},
            {100, 1002, 1100, 1702, true},
            {100, 1002, 1100, 2400, true},
            {100, 1698, 1100, 1700, true},
            {100, 1698, 1100, 1702, true},
            {100, 1698, 1100, 2400, true},
            {100, 1700, 1100, 1702, false},
            {100, 1700, 1100, 2400, false},
            {100, 1702, 1100, 2400, false},
            {100, 300, 1102, 998, false},
            {100, 300, 1102, 1000, false},
            {100, 300, 1102, 1002, true},
            {100, 300, 1102, 1698, true},
            {100, 300, 1102, 1700, true},
            {100, 300, 1102, 1702, true},
            {100, 300, 1102, 2400, true},
            {100, 998, 1102, 1000, false},
            {100, 998, 1102, 1002, true},
            {100, 998, 1102, 1698, true},
            {100, 998, 1102, 1700, true},
            {100, 998, 1102, 1702, true},
            {100, 998, 1102, 2400, true},
            {100, 1000, 1102, 1002, true},
            {100, 1000, 1102, 1698, true},
            {100, 1000, 1102, 1700, true},
            {100, 1000, 1102, 1702, true},
            {100, 1000, 1102, 2400, true},
            {100, 1002, 1102, 1698, true},
            {100, 1002, 1102, 1700, true},
            {100, 1002, 1102, 1702, true},
            {100, 1002, 1102, 2400, true},
            {100, 1698, 1102, 1700, true},
            {100, 1698, 1102, 1702, true},
            {100, 1698, 1102, 2400, true},
            {100, 1700, 1102, 1702, false},
            {100, 1700, 1102, 2400, false},
            {100, 1702, 1102, 2400, false},
            {100, 300, 1600, 998, false},
            {100, 300, 1600, 1000, false},
            {100, 300, 1600, 1002, true},
            {100, 300, 1600, 1698, true},
            {100, 300, 1600, 1700, true},
            {100, 300, 1600, 1702, true},
            {100, 300, 1600, 2400, true},
            {100, 998, 1600, 1000, false},
            {100, 998, 1600, 1002, true},
            {100, 998, 1600, 1698, true},
            {100, 998, 1600, 1700, true},
            {100, 998, 1600, 1702, true},
            {100, 998, 1600, 2400, true},
            {100, 1000, 1600, 1002, true},
            {100, 1000, 1600, 1698, true},
            {100, 1000, 1600, 1700, true},
            {100, 1000, 1600, 1702, true},
            {100, 1000, 1600, 2400, true},
            {100, 1002, 1600, 1698, true},
            {100, 1002, 1600, 1700, true},
            {100, 1002, 1600, 1702, true},
            {100, 1002, 1600, 2400, true},
            {100, 1698, 1600, 1700, true},
            {100, 1698, 1600, 1702, true},
            {100, 1698, 1600, 2400, true},
            {100, 1700, 1600, 1702, false},
            {100, 1700, 1600, 2400, false},
            {100, 1702, 1600, 2400, false},
            {598, 300, 600, 998, false},
            {598, 300, 600, 1000, false},
            {598, 300, 600, 1002, false},
            {598, 300, 600, 1698, false},
            {598, 300, 600, 1700, false},
            {598, 300, 600, 1702, false},
            {598, 300, 600, 2400, false},
            {598, 998, 600, 1000, false},
            {598, 998, 600, 1002, false},
            {598, 998, 600, 1698, false},
            {598, 998, 600, 1700, false},
            {598, 998, 600, 1702, false},
            {598, 998, 600, 2400, false},
            {598, 1000, 600, 1002, false},
            {598, 1000, 600, 1698, false},
            {598, 1000, 600, 1700, false},
            {598, 1000, 600, 1702, false},
            {598, 1000, 600, 2400, false},
            {598, 1002, 600, 1698, false},
            {598, 1002, 600, 1700, false},
            {598, 1002, 600, 1702, false},
            {598, 1002, 600, 2400, false},
            {598, 1698, 600, 1700, false},
            {598, 1698, 600, 1702, false},
            {598, 1698, 600, 2400, false},
            {598, 1700, 600, 1702, false},
            {598, 1700, 600, 2400, false},
            {598, 1702, 600, 2400, false},
            {598, 300, 602, 998, false},
            {598, 300, 602, 1000, false},
            {598, 300, 602, 1002, true},
            {598, 300, 602, 1698, true},
            {598, 300, 602, 1700, true},
            {598, 300, 602, 1702, true},
            {598, 300, 602, 2400, true},
            {598, 998, 602, 1000, false},
            {598, 998, 602, 1002, true},
            {598, 998, 602, 1698, true},
            {598, 998, 602, 1700, true},
            {598, 998, 602, 1702, true},
            {598, 998, 602, 2400, true},
            {598, 1000, 602, 1002, true},
            {598, 1000, 602, 1698, true},
            {598, 1000, 602, 1700, true},
            {598, 1000, 602, 1702, true},
            {598, 1000, 602, 2400, true},
            {598, 1002, 602, 1698, true},
            {598, 1002, 602, 1700, true},
            {598, 1002, 602, 1702, true},
            {598, 1002, 602, 2400, true},
            {598, 1698, 602, 1700, true},
            {598, 1698, 602, 1702, true},
            {598, 1698, 602, 2400, true},
            {598, 1700, 602, 1702, false},
            {598, 1700, 602, 2400, false},
            {598, 1702, 602, 2400, false},
            {598, 300, 1098, 998, false},
            {598, 300, 1098, 1000, false},
            {598, 300, 1098, 1002, true},
            {598, 300, 1098, 1698, true},
            {598, 300, 1098, 1700, true},
            {598, 300, 1098, 1702, true},
            {598, 300, 1098, 2400, true},
            {598, 998, 1098, 1000, false},
            {598, 998, 1098, 1002, true},
            {598, 998, 1098, 1698, true},
            {598, 998, 1098, 1700, true},
            {598, 998, 1098, 1702, true},
            {598, 998, 1098, 2400, true},
            {598, 1000, 1098, 1002, true},
            {598, 1000, 1098, 1698, true},
            {598, 1000, 1098, 1700, true},
            {598, 1000, 1098, 1702, true},
            {598, 1000, 1098, 2400, true},
            {598, 1002, 1098, 1698, true},
            {598, 1002, 1098, 1700, true},
            {598, 1002, 1098, 1702, true},
            {598, 1002, 1098, 2400, true},
            {598, 1698, 1098, 1700, true},
            {598, 1698, 1098, 1702, true},
            {598, 1698, 1098, 2400, true},
            {598, 1700, 1098, 1702, false},
            {598, 1700, 1098, 2400, false},
            {598, 1702, 1098, 2400, false},
            {598, 300, 1100, 998, false},
            {598, 300, 1100, 1000, false},
            {598, 300, 1100, 1002, true},
            {598, 300, 1100, 1698, true},
            {598, 300, 1100, 1700, true},
            {598, 300, 1100, 1702, true},
            {598, 300, 1100, 2400, true},
            {598, 998, 1100, 1000, false},
            {598, 998, 1100, 1002, true},
            {598, 998, 1100, 1698, true},
            {598, 998, 1100, 1700, true},
            {598, 998, 1100, 1702, true},
            {598, 998, 1100, 2400, true},
            {598, 1000, 1100, 1002, true},
            {598, 1000, 1100, 1698, true},
            {598, 1000, 1100, 1700, true},
            {598, 1000, 1100, 1702, true},
            {598, 1000, 1100, 2400, true},
            {598, 1002, 1100, 1698, true},
            {598, 1002, 1100, 1700, true},
            {598, 1002, 1100, 1702, true},
            {598, 1002, 1100, 2400, true},
            {598, 1698, 1100, 1700, true},
            {598, 1698, 1100, 1702, true},
            {598, 1698, 1100, 2400, true},
            {598, 1700, 1100, 1702, false},
            {598, 1700, 1100, 2400, false},
            {598, 1702, 1100, 2400, false},
            {598, 300, 1102, 998, false},
            {598, 300, 1102, 1000, false},
            {598, 300, 1102, 1002, true},
            {598, 300, 1102, 1698, true},
            {598, 300, 1102, 1700, true},
            {598, 300, 1102, 1702, true},
            {598, 300, 1102, 2400, true},
            {598, 998, 1102, 1000, false},
            {598, 998, 1102, 1002, true},
            {598, 998, 1102, 1698, true},
            {598, 998, 1102, 1700, true},
            {598, 998, 1102, 1702, true},
            {598, 998, 1102, 2400, true},
            {598, 1000, 1102, 1002, true},
            {598, 1000, 1102, 1698, true},
            {598, 1000, 1102, 1700, true},
            {598, 1000, 1102, 1702, true},
            {598, 1000, 1102, 2400, true},
            {598, 1002, 1102, 1698, true},
            {598, 1002, 1102, 1700, true},
            {598, 1002, 1102, 1702, true},
            {598, 1002, 1102, 2400, true},
            {598, 1698, 1102, 1700, true},
            {598, 1698, 1102, 1702, true},
            {598, 1698, 1102, 2400, true},
            {598, 1700, 1102, 1702, false},
            {598, 1700, 1102, 2400, false},
            {598, 1702, 1102, 2400, false},
            {598, 300, 1600, 998, false},
            {598, 300, 1600, 1000, false},
            {598, 300, 1600, 1002, true},
            {598, 300, 1600, 1698, true},
            {598, 300, 1600, 1700, true},
            {598, 300, 1600, 1702, true},
            {598, 300, 1600, 2400, true},
            {598, 998, 1600, 1000, false},
            {598, 998, 1600, 1002, true},
            {598, 998, 1600, 1698, true},
            {598, 998, 1600, 1700, true},
            {598, 998, 1600, 1702, true},
            {598, 998, 1600, 2400, true},
            {598, 1000, 1600, 1002, true},
            {598, 1000, 1600, 1698, true},
            {598, 1000, 1600, 1700, true},
            {598, 1000, 1600, 1702, true},
            {598, 1000, 1600, 2400, true},
            {598, 1002, 1600, 1698, true},
            {598, 1002, 1600, 1700, true},
            {598, 1002, 1600, 1702, true},
            {598, 1002, 1600, 2400, true},
            {598, 1698, 1600, 1700, true},
            {598, 1698, 1600, 1702, true},
            {598, 1698, 1600, 2400, true},
            {598, 1700, 1600, 1702, false},
            {598, 1700, 1600, 2400, false},
            {598, 1702, 1600, 2400, false},
            {600, 300, 602, 998, false},
            {600, 300, 602, 1000, false},
            {600, 300, 602, 1002, true},
            {600, 300, 602, 1698, true},
            {600, 300, 602, 1700, true},
            {600, 300, 602, 1702, true},
            {600, 300, 602, 2400, true},
            {600, 998, 602, 1000, false},
            {600, 998, 602, 1002, true},
            {600, 998, 602, 1698, true},
            {600, 998, 602, 1700, true},
            {600, 998, 602, 1702, true},
            {600, 998, 602, 2400, true},
            {600, 1000, 602, 1002, true},
            {600, 1000, 602, 1698, true},
            {600, 1000, 602, 1700, true},
            {600, 1000, 602, 1702, true},
            {600, 1000, 602, 2400, true},
            {600, 1002, 602, 1698, true},
            {600, 1002, 602, 1700, true},
            {600, 1002, 602, 1702, true},
            {600, 1002, 602, 2400, true},
            {600, 1698, 602, 1700, true},
            {600, 1698, 602, 1702, true},
            {600, 1698, 602, 2400, true},
            {600, 1700, 602, 1702, false},
            {600, 1700, 602, 2400, false},
            {600, 1702, 602, 2400, false},
            {600, 300, 1098, 998, false},
            {600, 300, 1098, 1000, false},
            {600, 300, 1098, 1002, true},
            {600, 300, 1098, 1698, true},
            {600, 300, 1098, 1700, true},
            {600, 300, 1098, 1702, true},
            {600, 300, 1098, 2400, true},
            {600, 998, 1098, 1000, false},
            {600, 998, 1098, 1002, true},
            {600, 998, 1098, 1698, true},
            {600, 998, 1098, 1700, true},
            {600, 998, 1098, 1702, true},
            {600, 998, 1098, 2400, true},
            {600, 1000, 1098, 1002, true},
            {600, 1000, 1098, 1698, true},
            {600, 1000, 1098, 1700, true},
            {600, 1000, 1098, 1702, true},
            {600, 1000, 1098, 2400, true},
            {600, 1002, 1098, 1698, true},
            {600, 1002, 1098, 1700, true},
            {600, 1002, 1098, 1702, true},
            {600, 1002, 1098, 2400, true},
            {600, 1698, 1098, 1700, true},
            {600, 1698, 1098, 1702, true},
            {600, 1698, 1098, 2400, true},
            {600, 1700, 1098, 1702, false},
            {600, 1700, 1098, 2400, false},
            {600, 1702, 1098, 2400, false},
            {600, 300, 1100, 998, false},
            {600, 300, 1100, 1000, false},
            {600, 300, 1100, 1002, true},
            {600, 300, 1100, 1698, true},
            {600, 300, 1100, 1700, true},
            {600, 300, 1100, 1702, true},
            {600, 300, 1100, 2400, true},
            {600, 998, 1100, 1000, false},
            {600, 998, 1100, 1002, true},
            {600, 998, 1100, 1698, true},
            {600, 998, 1100, 1700, true},
            {600, 998, 1100, 1702, true},
            {600, 998, 1100, 2400, true},
            {600, 1000, 1100, 1002, true},
            {600, 1000, 1100, 1698, true},
            {600, 1000, 1100, 1700, true},
            {600, 1000, 1100, 1702, true},
            {600, 1000, 1100, 2400, true},
            {600, 1002, 1100, 1698, true},
            {600, 1002, 1100, 1700, true},
            {600, 1002, 1100, 1702, true},
            {600, 1002, 1100, 2400, true},
            {600, 1698, 1100, 1700, true},
            {600, 1698, 1100, 1702, true},
            {600, 1698, 1100, 2400, true},
            {600, 1700, 1100, 1702, false},
            {600, 1700, 1100, 2400, false},
            {600, 1702, 1100, 2400, false},
            {600, 300, 1102, 998, false},
            {600, 300, 1102, 1000, false},
            {600, 300, 1102, 1002, true},
            {600, 300, 1102, 1698, true},
            {600, 300, 1102, 1700, true},
            {600, 300, 1102, 1702, true},
            {600, 300, 1102, 2400, true},
            {600, 998, 1102, 1000, false},
            {600, 998, 1102, 1002, true},
            {600, 998, 1102, 1698, true},
            {600, 998, 1102, 1700, true},
            {600, 998, 1102, 1702, true},
            {600, 998, 1102, 2400, true},
            {600, 1000, 1102, 1002, true},
            {600, 1000, 1102, 1698, true},
            {600, 1000, 1102, 1700, true},
            {600, 1000, 1102, 1702, true},
            {600, 1000, 1102, 2400, true},
            {600, 1002, 1102, 1698, true},
            {600, 1002, 1102, 1700, true},
            {600, 1002, 1102, 1702, true},
            {600, 1002, 1102, 2400, true},
            {600, 1698, 1102, 1700, true},
            {600, 1698, 1102, 1702, true},
            {600, 1698, 1102, 2400, true},
            {600, 1700, 1102, 1702, false},
            {600, 1700, 1102, 2400, false},
            {600, 1702, 1102, 2400, false},
            {600, 300, 1600, 998, false},
            {600, 300, 1600, 1000, false},
            {600, 300, 1600, 1002, true},
            {600, 300, 1600, 1698, true},
            {600, 300, 1600, 1700, true},
            {600, 300, 1600, 1702, true},
            {600, 300, 1600, 2400, true},
            {600, 998, 1600, 1000, false},
            {600, 998, 1600, 1002, true},
            {600, 998, 1600, 1698, true},
            {600, 998, 1600, 1700, true},
            {600, 998, 1600, 1702, true},
            {600, 998, 1600, 2400, true},
            {600, 1000, 1600, 1002, true},
            {600, 1000, 1600, 1698, true},
            {600, 1000, 1600, 1700, true},
            {600, 1000, 1600, 1702, true},
            {600, 1000, 1600, 2400, true},
            {600, 1002, 1600, 1698, true},
            {600, 1002, 1600, 1700, true},
            {600, 1002, 1600, 1702, true},
            {600, 1002, 1600, 2400, true},
            {600, 1698, 1600, 1700, true},
            {600, 1698, 1600, 1702, true},
            {600, 1698, 1600, 2400, true},
            {600, 1700, 1600, 1702, false},
            {600, 1700, 1600, 2400, false},
            {600, 1702, 1600, 2400, false},
            {602, 300, 1098, 998, false},
            {602, 300, 1098, 1000, false},
            {602, 300, 1098, 1002, true},
            {602, 300, 1098, 1698, true},
            {602, 300, 1098, 1700, true},
            {602, 300, 1098, 1702, true},
            {602, 300, 1098, 2400, true},
            {602, 998, 1098, 1000, false},
            {602, 998, 1098, 1002, true},
            {602, 998, 1098, 1698, true},
            {602, 998, 1098, 1700, true},
            {602, 998, 1098, 1702, true},
            {602, 998, 1098, 2400, true},
            {602, 1000, 1098, 1002, true},
            {602, 1000, 1098, 1698, true},
            {602, 1000, 1098, 1700, true},
            {602, 1000, 1098, 1702, true},
            {602, 1000, 1098, 2400, true},
            {602, 1002, 1098, 1698, true},
            {602, 1002, 1098, 1700, true},
            {602, 1002, 1098, 1702, true},
            {602, 1002, 1098, 2400, true},
            {602, 1698, 1098, 1700, true},
            {602, 1698, 1098, 1702, true},
            {602, 1698, 1098, 2400, true},
            {602, 1700, 1098, 1702, false},
            {602, 1700, 1098, 2400, false},
            {602, 1702, 1098, 2400, false},
            {602, 300, 1100, 998, false},
            {602, 300, 1100, 1000, false},
            {602, 300, 1100, 1002, true},
            {602, 300, 1100, 1698, true},
            {602, 300, 1100, 1700, true},
            {602, 300, 1100, 1702, true},
            {602, 300, 1100, 2400, true},
            {602, 998, 1100, 1000, false},
            {602, 998, 1100, 1002, true},
            {602, 998, 1100, 1698, true},
            {602, 998, 1100, 1700, true},
            {602, 998, 1100, 1702, true},
            {602, 998, 1100, 2400, true},
            {602, 1000, 1100, 1002, true},
            {602, 1000, 1100, 1698, true},
            {602, 1000, 1100, 1700, true},
            {602, 1000, 1100, 1702, true},
            {602, 1000, 1100, 2400, true},
            {602, 1002, 1100, 1698, true},
            {602, 1002, 1100, 1700, true},
            {602, 1002, 1100, 1702, true},
            {602, 1002, 1100, 2400, true},
            {602, 1698, 1100, 1700, true},
            {602, 1698, 1100, 1702, true},
            {602, 1698, 1100, 2400, true},
            {602, 1700, 1100, 1702, false},
            {602, 1700, 1100, 2400, false},
            {602, 1702, 1100, 2400, false},
            {602, 300, 1102, 998, false},
            {602, 300, 1102, 1000, false},
            {602, 300, 1102, 1002, true},
            {602, 300, 1102, 1698, true},
            {602, 300, 1102, 1700, true},
            {602, 300, 1102, 1702, true},
            {602, 300, 1102, 2400, true},
            {602, 998, 1102, 1000, false},
            {602, 998, 1102, 1002, true},
            {602, 998, 1102, 1698, true},
            {602, 998, 1102, 1700, true},
            {602, 998, 1102, 1702, true},
            {602, 998, 1102, 2400, true},
            {602, 1000, 1102, 1002, true},
            {602, 1000, 1102, 1698, true},
            {602, 1000, 1102, 1700, true},
            {602, 1000, 1102, 1702, true},
            {602, 1000, 1102, 2400, true},
            {602, 1002, 1102, 1698, true},
            {602, 1002, 1102, 1700, true},
            {602, 1002, 1102, 1702, true},
            {602, 1002, 1102, 2400, true},
            {602, 1698, 1102, 1700, true},
            {602, 1698, 1102, 1702, true},
            {602, 1698, 1102, 2400, true},
            {602, 1700, 1102, 1702, false},
            {602, 1700, 1102, 2400, false},
            {602, 1702, 1102, 2400, false},
            {602, 300, 1600, 998, false},
            {602, 300, 1600, 1000, false},
            {602, 300, 1600, 1002, true},
            {602, 300, 1600, 1698, true},
            {602, 300, 1600, 1700, true},
            {602, 300, 1600, 1702, true},
            {602, 300, 1600, 2400, true},
            {602, 998, 1600, 1000, false},
            {602, 998, 1600, 1002, true},
            {602, 998, 1600, 1698, true},
            {602, 998, 1600, 1700, true},
            {602, 998, 1600, 1702, true},
            {602, 998, 1600, 2400, true},
            {602, 1000, 1600, 1002, true},
            {602, 1000, 1600, 1698, true},
            {602, 1000, 1600, 1700, true},
            {602, 1000, 1600, 1702, true},
            {602, 1000, 1600, 2400, true},
            {602, 1002, 1600, 1698, true},
            {602, 1002, 1600, 1700, true},
            {602, 1002, 1600, 1702, true},
            {602, 1002, 1600, 2400, true},
            {602, 1698, 1600, 1700, true},
            {602, 1698, 1600, 1702, true},
            {602, 1698, 1600, 2400, true},
            {602, 1700, 1600, 1702, false},
            {602, 1700, 1600, 2400, false},
            {602, 1702, 1600, 2400, false},
            {1098, 300, 1100, 998, false},
            {1098, 300, 1100, 1000, false},
            {1098, 300, 1100, 1002, true},
            {1098, 300, 1100, 1698, true},
            {1098, 300, 1100, 1700, true},
            {1098, 300, 1100, 1702, true},
            {1098, 300, 1100, 2400, true},
            {1098, 998, 1100, 1000, false},
            {1098, 998, 1100, 1002, true},
            {1098, 998, 1100, 1698, true},
            {1098, 998, 1100, 1700, true},
            {1098, 998, 1100, 1702, true},
            {1098, 998, 1100, 2400, true},
            {1098, 1000, 1100, 1002, true},
            {1098, 1000, 1100, 1698, true},
            {1098, 1000, 1100, 1700, true},
            {1098, 1000, 1100, 1702, true},
            {1098, 1000, 1100, 2400, true},
            {1098, 1002, 1100, 1698, true},
            {1098, 1002, 1100, 1700, true},
            {1098, 1002, 1100, 1702, true},
            {1098, 1002, 1100, 2400, true},
            {1098, 1698, 1100, 1700, true},
            {1098, 1698, 1100, 1702, true},
            {1098, 1698, 1100, 2400, true},
            {1098, 1700, 1100, 1702, false},
            {1098, 1700, 1100, 2400, false},
            {1098, 1702, 1100, 2400, false},
            {1098, 300, 1102, 998, false},
            {1098, 300, 1102, 1000, false},
            {1098, 300, 1102, 1002, true},
            {1098, 300, 1102, 1698, true},
            {1098, 300, 1102, 1700, true},
            {1098, 300, 1102, 1702, true},
            {1098, 300, 1102, 2400, true},
            {1098, 998, 1102, 1000, false},
            {1098, 998, 1102, 1002, true},
            {1098, 998, 1102, 1698, true},
            {1098, 998, 1102, 1700, true},
            {1098, 998, 1102, 1702, true},
            {1098, 998, 1102, 2400, true},
            {1098, 1000, 1102, 1002, true},
            {1098, 1000, 1102, 1698, true},
            {1098, 1000, 1102, 1700, true},
            {1098, 1000, 1102, 1702, true},
            {1098, 1000, 1102, 2400, true},
            {1098, 1002, 1102, 1698, true},
            {1098, 1002, 1102, 1700, true},
            {1098, 1002, 1102, 1702, true},
            {1098, 1002, 1102, 2400, true},
            {1098, 1698, 1102, 1700, true},
            {1098, 1698, 1102, 1702, true},
            {1098, 1698, 1102, 2400, true},
            {1098, 1700, 1102, 1702, false},
            {1098, 1700, 1102, 2400, false},
            {1098, 1702, 1102, 2400, false},
            {1098, 300, 1600, 998, false},
            {1098, 300, 1600, 1000, false},
            {1098, 300, 1600, 1002, true},
            {1098, 300, 1600, 1698, true},
            {1098, 300, 1600, 1700, true},
            {1098, 300, 1600, 1702, true},
            {1098, 300, 1600, 2400, true},
            {1098, 998, 1600, 1000, false},
            {1098, 998, 1600, 1002, true},
            {1098, 998, 1600, 1698, true},
            {1098, 998, 1600, 1700, true},
            {1098, 998, 1600, 1702, true},
            {1098, 998, 1600, 2400, true},
            {1098, 1000, 1600, 1002, true},
            {1098, 1000, 1600, 1698, true},
            {1098, 1000, 1600, 1700, true},
            {1098, 1000, 1600, 1702, true},
            {1098, 1000, 1600, 2400, true},
            {1098, 1002, 1600, 1698, true},
            {1098, 1002, 1600, 1700, true},
            {1098, 1002, 1600, 1702, true},
            {1098, 1002, 1600, 2400, true},
            {1098, 1698, 1600, 1700, true},
            {1098, 1698, 1600, 1702, true},
            {1098, 1698, 1600, 2400, true},
            {1098, 1700, 1600, 1702, false},
            {1098, 1700, 1600, 2400, false},
            {1098, 1702, 1600, 2400, false},
            {1100, 300, 1102, 998, false},
            {1100, 300, 1102, 1000, false},
            {1100, 300, 1102, 1002, false},
            {1100, 300, 1102, 1698, false},
            {1100, 300, 1102, 1700, false},
            {1100, 300, 1102, 1702, false},
            {1100, 300, 1102, 2400, false},
            {1100, 998, 1102, 1000, false},
            {1100, 998, 1102, 1002, false},
            {1100, 998, 1102, 1698, false},
            {1100, 998, 1102, 1700, false},
            {1100, 998, 1102, 1702, false},
            {1100, 998, 1102, 2400, false},
            {1100, 1000, 1102, 1002, false},
            {1100, 1000, 1102, 1698, false},
            {1100, 1000, 1102, 1700, false},
            {1100, 1000, 1102, 1702, false},
            {1100, 1000, 1102, 2400, false},
            {1100, 1002, 1102, 1698, false},
            {1100, 1002, 1102, 1700, false},
            {1100, 1002, 1102, 1702, false},
            {1100, 1002, 1102, 2400, false},
            {1100, 1698, 1102, 1700, false},
            {1100, 1698, 1102, 1702, false},
            {1100, 1698, 1102, 2400, false},
            {1100, 1700, 1102, 1702, false},
            {1100, 1700, 1102, 2400, false},
            {1100, 1702, 1102, 2400, false},
            {1100, 300, 1600, 998, false},
            {1100, 300, 1600, 1000, false},
            {1100, 300, 1600, 1002, false},
            {1100, 300, 1600, 1698, false},
            {1100, 300, 1600, 1700, false},
            {1100, 300, 1600, 1702, false},
            {1100, 300, 1600, 2400, false},
            {1100, 998, 1600, 1000, false},
            {1100, 998, 1600, 1002, false},
            {1100, 998, 1600, 1698, false},
            {1100, 998, 1600, 1700, false},
            {1100, 998, 1600, 1702, false},
            {1100, 998, 1600, 2400, false},
            {1100, 1000, 1600, 1002, false},
            {1100, 1000, 1600, 1698, false},
            {1100, 1000, 1600, 1700, false},
            {1100, 1000, 1600, 1702, false},
            {1100, 1000, 1600, 2400, false},
            {1100, 1002, 1600, 1698, false},
            {1100, 1002, 1600, 1700, false},
            {1100, 1002, 1600, 1702, false},
            {1100, 1002, 1600, 2400, false},
            {1100, 1698, 1600, 1700, false},
            {1100, 1698, 1600, 1702, false},
            {1100, 1698, 1600, 2400, false},
            {1100, 1700, 1600, 1702, false},
            {1100, 1700, 1600, 2400, false},
            {1100, 1702, 1600, 2400, false},
            {1102, 300, 1600, 998, false},
            {1102, 300, 1600, 1000, false},
            {1102, 300, 1600, 1002, false},
            {1102, 300, 1600, 1698, false},
            {1102, 300, 1600, 1700, false},
            {1102, 300, 1600, 1702, false},
            {1102, 300, 1600, 2400, false},
            {1102, 998, 1600, 1000, false},
            {1102, 998, 1600, 1002, false},
            {1102, 998, 1600, 1698, false},
            {1102, 998, 1600, 1700, false},
            {1102, 998, 1600, 1702, false},
            {1102, 998, 1600, 2400, false},
            {1102, 1000, 1600, 1002, false},
            {1102, 1000, 1600, 1698, false},
            {1102, 1000, 1600, 1700, false},
            {1102, 1000, 1600, 1702, false},
            {1102, 1000, 1600, 2400, false},
            {1102, 1002, 1600, 1698, false},
            {1102, 1002, 1600, 1700, false},
            {1102, 1002, 1600, 1702, false},
            {1102, 1002, 1600, 2400, false},
            {1102, 1698, 1600, 1700, false},
            {1102, 1698, 1600, 1702, false},
            {1102, 1698, 1600, 2400, false},
            {1102, 1700, 1600, 1702, false},
            {1102, 1700, 1600, 2400, false},
            {1102, 1702, 1600, 2400, false},
    };

}
