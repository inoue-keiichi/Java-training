package ch03.ex14;

import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

public interface ColorTransFormer {
    public Color apply(int x, int y, PixelReader reader);

    static ColorTransFormer compose(final ColorTransFormer ctf1, final ColorTransFormer ctf2) {
        return (x, y, reader) -> {
            return ctf2.apply(x, y, reader);
        };
    }

    static ColorTransFormer convert(final UnaryOperator<Color> f) {
        return (x, y, reader) -> {
            return f.apply(reader.getColor(x, y));
        };
    }
}
