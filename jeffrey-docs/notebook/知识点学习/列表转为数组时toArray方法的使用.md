# 列表转为数组时toArray方法的使用
在java中将列表数据转为数组时，使用List接口中的toArray方法。
下面这种用法会出现ClassCastException错误。

```
public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        String[] a=(String[]) list.toArray();
        for(String string:a) {
            System.out.println(string);
        }
```

强制转换是不行的，toArray方法返回的是 Object[],可以使用带参数的方式，如下：

```
list.toArray(new String[0])
或者
list.toArray(new String[]{})
```
是可以正常运行的，带参数的toArray方法的返回值是和参数相同的。
我把toArray方法的源码贴出来，可以更明了的看出来。

```
// 不带参数
 public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
// 带参数
public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }
```
开始疑惑为什么传进去的是空数组，怎么确定数组的大小呢，
看了源码也就明白为什么使用`list.toArray(new String[0])`这种方式也可以了，源码里有数组的长度判断，如果数组小于list的size，会重新创建一个新数组。如果传进去的数组长度大于list的size，那么大于的部分都会是null。
