import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CodeEditorPane {
    // array of keywords to be highlighted
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
        "volatile", "while", "true", "false", "null"
    ));

    private final Timer highlight = new Timer(10, e -> applySyntaxHighlighting());

    private void applySyntaxHighlighting() {
        StyledDocument doc = getStyledDocument();
        String text = getText();
        Font baseFont = getFont();
        String fontFamily = baseFont.getFamily();
        int fontSize = baseFont.getSize();

        Style defaultStyle = doc.addStyle("default", null);
        StyleConstants.setForeground(defaultStyle, theme.getEditorForeground());
        StyleConstants.setFontFamily(defaultStyle, fontFamily);
        StyleConstants.setFontSize(defaultStyle, fontSize);

        Style keywordStyle = doc.addStyle("keyword", null);
        StyleConstants.setForeground(keywordStyle, theme.getKeywordColor());
        StyleConstants.setFontFamily(keywordStyle, fontFamily);
        StyleConstants.setFontSize(keywordStyle, fontSize);
        StyleConstants.setBold(keywordStyle, true);

        Style numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, theme.getNumberColor());
        StyleConstants.setFontFamily(numberStyle, fontFamily);
        StyleConstants.setFontSize(numberStyle, fontSize);

        Style stringStyle = doc.addStyle("string", null);
        StyleConstants.setForeground(stringStyle, theme.getStringColor());
        StyleConstants.setFontFamily(stringStyle, fontFamily);
        StyleConstants.setFontSize(stringStyle, fontSize);

        Style commentStyle = doc.addStyle("comment", null);
        StyleConstants.setForeground(commentStyle, theme.getCommentColor());
        StyleConstants.setFontFamily(commentStyle, fontFamily);
        StyleConstants.setFontSize(commentStyle, fontSize);

        doc.setCharacterAttributes(0, text.length(), defaultStyle, true);

        Matcher commentMatcher = Pattern.compile("//.*|/\\*(.|\\R)*?\\*/").matcher(text);
        while (commentMatcher.find()) {
            doc.setCharacterAttributes(
                    commentMatcher.start(),
                    commentMatcher.end() - commentMatcher.start(),
                    commentStyle,
                    true
            );
        }

        Matcher stringMatcher = Pattern.compile("\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*'").matcher(text);
        while (stringMatcher.find()) {
            doc.setCharacterAttributes(
                    stringMatcher.start(),
                    stringMatcher.end() - stringMatcher.start(),
                    stringStyle,
                    true
            );
        }

        Matcher numberMatcher = Pattern.compile("\\b\\d+(?:\\.\\d+)?\\b").matcher(text);
        while (numberMatcher.find()) {
            doc.setCharacterAttributes(
                    numberMatcher.start(),
                    numberMatcher.end() - numberMatcher.start(),
                    numberStyle,
                    true
            );
        }

        Matcher keywordMatcher = Pattern.compile("\\b[A-Za-z_][A-Za-z0-9_]*\\b").matcher(text);
        while (keywordMatcher.find()) {
            String token = keywordMatcher.group();
            if (KEYWORDS.contains(token)) {
                doc.setCharacterAttributes(
                        keywordMatcher.start(),
                        keywordMatcher.end() - keywordMatcher.start(),
                        keywordStyle,
                        true
                );
            }
        }
    }
}
