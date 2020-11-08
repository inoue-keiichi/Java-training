# reduceのBiFunctionへの最初の型引数でUを? super Uと宣言すべきか？

宣言すべきでない。BiFunctionの第一引数Uは、identityの型と返り値を表す第三引数の型パラメーターにも使われている。
これら２つはreduceが実行される際にBiFunctionの第一引数に代入されるが、これらの値の型はUであり、これ以外の型の値が引数に使われることはない。
そのため第一引数を?superUという下限付きワイルドカードにする必要がない。

```
<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
```