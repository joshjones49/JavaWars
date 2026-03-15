import java.awt.Color;
import java.util.List;

public class ThemePalette {
    // class fields
    private final String name;
    private final Color panelBackground;
    private final Color panelForeground;
    private final Color surfaceBackground;
    private final Color buttonBackground;
    private final Color editorBackground;
    private final Color editorForeground;
    private final Color editorCaret;
    private final Color keywordColor;
    private final Color numberColor;
    private final Color stringColor;
    private final Color commentColor;

    // constructor
    public ThemePalette (
            String name,
            Color panelBackground,
            Color panelForeground,
            Color surfaceBackground,
            Color buttonBackground,
            Color editorBackground,
            Color editorForeground,
            Color editorCaret,
            Color keywordColor,
            Color numberColor,
            Color stringColor,
            Color commentColor
    ) {
        this.name = name;
        this.panelBackground = panelBackground;
        this.panelForeground = panelForeground;
        this.surfaceBackground = surfaceBackground;
        this.buttonBackground = buttonBackground;
        this.editorBackground = editorBackground;
        this.editorForeground = editorForeground;
        this.editorCaret = editorCaret;
        this.keywordColor = keywordColor;
        this.numberColor = numberColor;
        this.stringColor = stringColor;
        this.commentColor = commentColor;
    }

    // list of valid colors for theme selection
    public static List<ThemePalette> defaults() {
        return List.of(COOL_BLUE, DARK, LIGHT, MATRIX);
    }

    // getters
    public Color getPanelBackground() {
        return panelBackground;
    }

    public Color getPanelForeground() {
        return panelForeground;
    }

    public Color getSurfaceBackground() {
        return surfaceBackground;
    }

    public Color getButtonBackground() {
        return buttonBackground;
    }

    public Color getEditorBackground() {
        return editorBackground;
    }

    public Color getEditorForeground() {
        return editorForeground;
    }

    public Color getEditorCaret() {
        return editorCaret;
    }

    public Color getKeywordColor() {
        return keywordColor;
    }

    public Color getNumberColor() {
        return numberColor;
    }

    public Color getStringColor() {
        return stringColor;
    }

    public Color getCommentColor() {
        return commentColor;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final ThemePalette DARK = new ThemePalette(
            "Dark",
            new Color(28, 28, 28),
            new Color(230, 230, 230),
            new Color(36, 36, 36),
            new Color(58, 58, 58),
            new Color(30, 30, 30),
            new Color(220, 220, 220),
            new Color(230, 230, 230),
            new Color(86, 156, 214),
            new Color(181, 206, 168),
            new Color(214, 157, 133),
            new Color(106, 153, 85)
    );

    public static final ThemePalette LIGHT = new ThemePalette(
            "Light",
            new Color(245, 245, 245),
            new Color(35, 35, 35),
            Color.WHITE,
            new Color(230, 230, 230),
            Color.WHITE,
            new Color(25, 25, 25),
            Color.BLACK,
            new Color(0, 70, 160),
            new Color(30, 110, 30),
            new Color(160, 80, 40),
            new Color(90, 130, 60)
    );

    public static final ThemePalette COOL_BLUE = new ThemePalette(
            "Cool Blue",
            new Color(17, 32, 56),
            new Color(220, 235, 255),
            new Color(26, 45, 72),
            new Color(40, 68, 104),
            new Color(22, 40, 66),
            new Color(220, 235, 255),
            new Color(235, 245, 255),
            new Color(119, 189, 255),
            new Color(173, 223, 255),
            new Color(255, 204, 149),
            new Color(140, 196, 255)
    );

    public static final ThemePalette MATRIX = new ThemePalette(
            "Matrix Green",
            new Color(8, 20, 10),
            new Color(102, 255, 102),
            new Color(10, 26, 12),
            new Color(18, 45, 20),
            new Color(5, 18, 8),
            new Color(135, 255, 135),
            new Color(140, 255, 140),
            new Color(80, 255, 80),
            new Color(140, 255, 160),
            new Color(185, 255, 185),
            new Color(95, 200, 95)
    );
}
