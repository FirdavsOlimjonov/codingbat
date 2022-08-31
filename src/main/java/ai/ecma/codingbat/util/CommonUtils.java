package ai.ecma.codingbat.util;

import java.text.Normalizer;
import java.util.Objects;
import java.util.regex.Pattern;

public class CommonUtils {

    private static final Pattern NON_LATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGES_DASHES = Pattern.compile("(^-|-$)");

    /**
     *
     * @param input String
     * @return
     */
    public static String makeUrl(String input) {
        if (Objects.isNull(input))
            throw new IllegalArgumentException("For make url given input must not be null");

        String nonWhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nonWhitespace, Normalizer.Form.NFD);
        String slug = NON_LATIN.matcher(normalized).replaceAll("");
        slug = EDGES_DASHES.matcher(slug).replaceAll("");
        return slug.toLowerCase();
    }
}
