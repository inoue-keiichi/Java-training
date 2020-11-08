# PairのflatMapはどうなる？
PairにflatMapは実装できない。flatMapによって、N個のPair<T>をN:1に変換するが、
Pair\<T>の持つ対のオブジェクトをそれぞれ連結できるかどうかは型パラメータTに依存するから。

例えば、TがFunction\<U,R>だったらf1(f2(...fN(U)))=g(U)という変換をするFunction<U ,R'>作成できる。
しかし、TがStringだったらStringのオブジェクトを連結する事ができない。
"Hello"と"World"を結合して"HelloWorld"にすることは新しいインスタンスを作ることになってしまってflatMapによる連結とは異なってしまうからだ。