package util.validator;

import http.helpers.TextFinder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.general.SystemHelper;

import java.util.List;

public class UIValidator extends ResponsiveUIValidator implements Validator {

    UIValidator(WebDriver driver, WebElement element, String readableNameOfElement) {
        super(driver);
        rootElement = element;
        rootElementReadableName = readableNameOfElement;
        xRoot = rootElement.getLocation().getX();
        yRoot = rootElement.getLocation().getY();
        widthRoot = rootElement.getSize().getWidth();
        heightRoot = rootElement.getSize().getHeight();
        pageWidth = (int) getPageWidth();
        pageHeight = (int) getPageHeight();
        pageHeight = getRetinaValue(driver.manage().window().getSize().getHeight());
        startTime = System.currentTimeMillis();
    }

    @Override
    public UIValidator changeMetricsUnitsTo(Units units) {
        this.units = units;
        return this;
    }

    @Override
    public UIValidator withLeftElement(WebElement element) {
        validateLeftElement(element);

        return this;
    }

    @Override
    public UIValidator withLeftElement(WebElement element, int minMargin, int maxMargin) {
        validateLeftElement(element, getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true));

        return this;
    }

    @Override
    public UIValidator withRightElement(WebElement element) {
        validateRightElement(element);
        return this;
    }

    @Override
    public UIValidator withRightElement(WebElement element, int minMargin, int maxMargin) {
        validateRightElement(element, getConvertedInt(minMargin, true), getConvertedInt(maxMargin, true));
        return this;
    }

    @Override
    public UIValidator withTopElement(WebElement element) {
        validateAboveElement(element);
        return this;
    }

    @Override
    public UIValidator withTopElement(WebElement element, int minMargin, int maxMargin) {
        validateAboveElement(element, getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false));
        return this;
    }

    @Override
    public UIValidator withBottomElement(WebElement element) {
        validateBelowElement(element);
        return this;
    }

    @Override
    public UIValidator withBottomElement(WebElement element, int minMargin, int maxMargin) {
        validateBelowElement(element, getConvertedInt(minMargin, false), getConvertedInt(maxMargin, false));
        return this;
    }

    @Override
    public UIValidator notOverlapWith(WebElement element, String readableName) {
        validateNotOverlappingWithElements(element, readableName);
        return this;
    }

    @Override
    public UIValidator overlapWith(WebElement element, String readableName) {
        validateOverlappingWithElements(element, readableName);
        return this;
    }

    @Override
    public UIValidator notOverlapWith(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateNotOverlappingWithElements(element, getFormattedMessage(element));
        }
        return this;
    }

    @Override
    public UIValidator sameOffsetLeftAs(WebElement element, String readableName) {
        validateLeftOffsetForElements(element, readableName);
        drawLeftOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetLeftAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateLeftOffsetForElements(element, getFormattedMessage(element));
        }
        drawLeftOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetRightAs(WebElement element, String readableName) {
        validateRightOffsetForElements(element, readableName);
        drawRightOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetRightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateRightOffsetForElements(element, getFormattedMessage(element));
        }
        drawRightOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetTopAs(WebElement element, String readableName) {
        validateTopOffsetForElements(element, readableName);
        drawTopOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetTopAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateTopOffsetForElements(element, getFormattedMessage(element));
        }
        drawTopOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetBottomAs(WebElement element, String readableName) {
        validateBottomOffsetForElements(element, readableName);
        drawBottomOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameOffsetBottomAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateBottomOffsetForElements(element, getFormattedMessage(element));
        }
        drawBottomOffsetLine = true;
        return this;
    }

    @Override
    public UIValidator sameWidthAs(WebElement element, String readableName) {
        validateSameWidth(element, readableName);
        return this;
    }

    @Override
    public UIValidator sameWidthAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameWidth(element, getFormattedMessage(element));
        }
        return this;
    }

    @Override
    public UIValidator minWidth(int width) {
        validateMinWidth(getConvertedInt(width, true));
        return this;
    }

    @Override
    public UIValidator maxWidth(int width) {
        validateMaxWidth(getConvertedInt(width, true));
        return this;
    }

    @Override
    public UIValidator widthBetween(int min, int max) {
        validateMinWidth(getConvertedInt(min, true));
        validateMaxWidth(getConvertedInt(max, true));
        return this;
    }

    @Override
    public UIValidator sameHeightAs(WebElement element, String readableName) {
        validateSameHeight(element, readableName);
        return this;
    }

    @Override
    public UIValidator sameHeightAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameHeight(element, getFormattedMessage(element));
        }
        return this;
    }

    @Override
    public UIValidator minHeight(int height) {
        validateMinHeight(getConvertedInt(height, false));
        return this;
    }

    @Override
    public UIValidator maxHeight(int height) {
        validateMaxHeight(getConvertedInt(height, false));
        return this;
    }

    @Override
    public UIValidator sameSizeAs(WebElement element, String readableName) {
        validateSameSize(element, readableName);
        return this;
    }

    @Override
    public UIValidator sameSizeAs(List<WebElement> elements) {
        for (WebElement element : elements) {
            validateSameSize(element, getFormattedMessage(element));
        }
        return this;
    }

    @Override
    public UIValidator heightBetween(int min, int max) {
        validateMinHeight(getConvertedInt(min, false));
        validateMaxHeight(getConvertedInt(max, false));
        return this;
    }

    @Override
    public UIValidator minOffset(int top, int right, int bottom, int left) {
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMinOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
        }
        return this;
    }

    @Override
    public UIValidator maxOffset(int top, int right, int bottom, int left) {
        if (getConvertedInt(top, false) > MIN_OFFSET && getConvertedInt(right, true) > MIN_OFFSET && getConvertedInt(bottom, false) > MIN_OFFSET && getConvertedInt(left, true) > MIN_OFFSET) {
            validateMaxOffset(getConvertedInt(top, false), getConvertedInt(right, true), getConvertedInt(bottom, false), getConvertedInt(left, true));
        }
        return this;
    }

    @Override
    public UIValidator withCssValue(String cssProperty, String... args) {
        String cssValue = rootElement.getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (!TextFinder.textIsFound(val, cssValue)) {
                    putJsonDetailsWithoutElement(String.format("Expected value of '%s' is '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            putJsonDetailsWithoutElement(String.format("Element '%s' does not have css property '%s'", rootElementReadableName, cssProperty));
        }
        return this;
    }

    @Override
    public UIValidator withoutCssValue(String cssProperty, String... args) {
        String cssValue = rootElement.getCssValue(cssProperty);

        if (!cssValue.equals("")) {
            for (String val : args) {
                val = !val.startsWith("#") ? val : SystemHelper.hexStringToARGB(val);
                if (TextFinder.textIsFound(val, cssValue)) {
                    putJsonDetailsWithoutElement(String.format("CSS property '%s' should not contain value '%s'. Actual value is '%s'", cssProperty, val, cssValue));
                }
            }
        } else {
            putJsonDetailsWithoutElement(String.format("Element '%s' does not have css property '%s'", rootElementReadableName, cssProperty));
        }
        return this;
    }

    @Override
    public UIValidator equalLeftRightOffset() {
        validateEqualLeftRightOffset(rootElement, rootElementReadableName);
        return this;
    }

    @Override
    public UIValidator equalTopBottomOffset() {
        validateEqualTopBottomOffset(rootElement, rootElementReadableName);
        return this;
    }
}


