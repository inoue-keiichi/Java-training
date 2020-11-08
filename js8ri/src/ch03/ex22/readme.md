# CompletableFutureに対するflatMapは？

supplyAsyncで複数のCompletableFutureを作成してそれらをanyOfの引数に利用する。
理由は以下２点の変換がflatMapに対応するため。
- supplyAsyncで、U→CompletableFuture\<V>
- anyOfで、...CompletableFuture\<V>→CompletableFuture\<Object>

flatMapの変換は以下の通り
- U → Stream\<? extends V>
- ...Stream\<? extends V> → Stream\<V>


```
// CompletableFutureの実装例
List<CompletableFuture<Integer>> cfs = Arrays.asList(
                CompletableFuture.supplyAsync(() -> 100),
                CompletableFuture.supplyAsync(() -> 200),
                CompletableFuture.supplyAsync(() -> 300));
 CompletableFuture<Object> ccc = CompletableFuture.anyOf(cfs.toArray(new CompletableFuture[cfs.size()]));

// Streamの実装例
 Stream<Object> stream = Stream.of("Canada", "Japan", "England", "UnitedStates").flatMap(x -> Stream.of(x, x.length()));
```