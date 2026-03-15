import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class CodeEditorPane extends JTextPane {
    // array of keywords to be highlighted
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
        "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
        "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
        "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
        "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
        "volatile", "while", "true", "false", "null"
    ));

    private final Timer highlightTimer = new Timer(10, e -> applySyntaxHighlighting());
    private ThemePalette theme = ThemePalette.COOL_BLUE;

    public CodeEditorPane() {
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        setupListeners();
        applyTheme(ThemePalette.COOL_BLUE);
    }

    private void setupListeners() {
        highlightTimer.setRepeats(false);

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                highlightTimer.restart();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                highlightTimer.restart();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        Map<Character, Character> pairMap = new HashMap<>();
        pairMap.put('(', ')');
        pairMap.put('{', '}');
        pairMap.put('[', ']');
        pairMap.put('"', '"');
        pairMap.put('\'', '\'');

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (pairMap.containsKey(ch)) {
                    int start = getSelectionStart();
                    int end = getSelectionEnd();
                    String selected = getSelectedText();
                    if (selected == null) {
                        selected = "";
                    }

                    char close = pairMap.get(ch);
                    replaceSelection(ch + selected + close);
                    if (start == end) {
                        setCaretPosition(start + 1);
                    } else {
                        select(start + 1, end + 1);
                    }
                    e.consume();
                    return;
                }

                if (ch == ')' || ch == '}' || ch == ']' || ch == '"' || ch == '\'') {
                    int pos = getCaretPosition();
                    String text = getText();
                    if (pos < text.length() && text.charAt(pos) == ch) {
                        setCaretPosition(pos + 1);
                        e.consume();
                    }
                }
            }
        });
    }

    public void applyTheme(ThemePalette name) {
        this.theme = theme;
        setBackground(theme.getEditorBackground());
        setForeground(theme.getEditorForeground());
        setCaretColor(theme.getEditorCaret());

        applySyntaxHighlighting();
    }

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
