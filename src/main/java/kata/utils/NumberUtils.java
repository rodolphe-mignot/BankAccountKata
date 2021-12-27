package kata.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    private NumberUtils() {
    }

    public static BigDecimal wrapAndRound(float bgToRound) {
        return BigDecimal.valueOf(bgToRound).setScale(2, RoundingMode.HALF_DOWN);
    }

    public static float roundAndUnwrap(BigDecimal bgToRound) {
        return bgToRound.setScale(2, RoundingMode.HALF_DOWN).floatValue();
    }
}
