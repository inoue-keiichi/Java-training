# なぜ次の呼び出しができないか？
```
final UnaryOperator<Color> op = Color::brighter;
final Image finalImage = transform(new Image(""), op.compose(Color::grayscale));

private static Image transform(final Image image, final UnaryOperator<Color> f) {
	// 実装略
}
```
型が異なるから。どちらも(Color)->Colorという関数を渡しているが、op.composeの返り値の型はFunction<Color,Color>であってUnaryOperator<Color>とは異なる。


# 関数合成に関しては、ストラクチャルとノミナルのどちらが役立つか？
型を意識せず構造が同じなら同じように扱えた方が役に立つので、ストラクチャルの方が役に立つ。

