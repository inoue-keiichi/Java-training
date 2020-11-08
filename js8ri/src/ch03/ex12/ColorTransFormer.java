package ch03.ex12;

import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ColorTransFormer {

    public Color apply(int x, int y, Color color);

    static ColorTransFormer compose(final ColorTransFormer ctf1, final ColorTransFormer ctf2) {
        return (x, y, color) -> {
            return ctf2.apply(x, y, ctf1.apply(x, y, color));
        };
    }

    static ColorTransFormer convert(final UnaryOperator<Color> f) {
        return (x, y, color) -> {
            return f.apply(color);
        };
    }
}