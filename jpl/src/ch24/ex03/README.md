# 日付の解析はどのくらい寛容か？

* yyyy/mm/dd, yy/mm/dd, yy/m/dは解析できて、形式はSHORT, MEDIUM, LONG
* yy年mm月dd日は解析できて、形式はFULL
* yy-mm-ddは解析できない。

```
2020/02/02
short : 20/02/02
medium : 2020/02/02
long : 2020/02/02

20/02/02
short : 20/02/02
medium : 0020/02/02
long : 0020/02/02

20/2/2
short : 20/02/02
medium : 0020/02/02
long : 0020/02/02

2020年2月2日
full : 2020年2月2日

2020-02-02
なし
```