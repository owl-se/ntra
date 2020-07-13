package utils;

import static java.lang.Math.sqrt;

public class Calculation {

    public static float getPerimeter(float firstLine, float secondLine, float thirdLine) {
        return firstLine + secondLine + thirdLine;
    }

    public static float getArea(float firstLine, float secondLine, float thirdLine, boolean equalSided) {
        float h, s;

        if (equalSided) {
            h = (float)sqrt(3) * firstLine / 2;
            s = firstLine * h / 2;
        } else {
            s = 1;
        }

        return s;
    }
}
