# 名前にFilterを含む関数インターフェースがJavaAPIにはいくつあるか？
- java.nio.file.DirectoryStream.Filter
- java.xml.stream.EventFilter
- java.io.FileFilter
- java.io.FileNameFilter
- java.util.logging.Filter
- javax.management.NotificationFilter
- javax.imageio.spi.ServiceRegistry.Filter
- javax.xml.stream.StreamFilter

# そのうちどれがPredicate<T>よりも付加価値がありますか？
- javax.management.NotificationFilter
メソッドの説明に「指定された通知をリスナーに送信する前に呼び出されます。」とある。送信される前に毎回呼び出される仕組みになっているため付加価値がある。
