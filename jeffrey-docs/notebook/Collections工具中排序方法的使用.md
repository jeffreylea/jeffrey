# Collections工具排序方法练习
下面练习的方法有：

```
 void reverse(List<?> list): 反转
 Collections.shuffle(list)：随机排序
 Collections.sort(list):自然排序：正序
 Collections.sort(list,new Comparator<String>:定制排序
 Collections.swap(list, 0, 2):交换两个索引位置的元素
 Collections.rotate(List<?> list, int distance):旋转
```
首先创建一个初始的列表：

```
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        list.add("test5");
        log.info("原始列表:{}", list);
``` 
// void reverse(List<?> list): 反转

```
Collections.reverse(list);
        log.info("Collections.reverse(list):{}", list);
``` 
 
        
        // Collections.shuffle(list)：随机排序，shuffle这个单词有洗牌的意思，
        // 就像洗牌一样打乱顺序
        Collections.shuffle(list);
        log.info("Collections.shuffle(list):{}", list);
        // Collections.sort(list):自然排序：正序
        Collections.sort(list);
        log.info("Collections.sort(list):{}", list);
        //Collections.sort(list,new Comparator<String>:定制排序，由比较器comparator控制排序逻辑
        Collections.sort(list,new Comparator<String>() {

            // 重写比较器，如果是对象，例如根据对象中的姓名排序，可以用o1.getName.compareTo(o2)
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        log.info("Collections.sort(list, compatator):{}", list);
        // Collections.swap(list, 0, 2)交换两个索引位置的元素 ;swap意为交换，下面即为第一个元素和第三个元素交换
        Collections.swap(list, 0, 2);
        log.info("Collections.swap(list):{}", list);
        //  在练习Collections.rotate(List<?> list, int distance)这个方法时，首先学习rotate这个单词，rotate是旋转的意思，该方法就是将集合进行旋转操作。
        //  当 distance>0时，将list后的distance个元素整体挪到前面,例如当distance参数为2时，结果如下
        // [test3, test4, test5, test2, test1]->[test1, test3, test4, test5, test2]
        // 当distance<0 时，将list的distance个元素整体移动到后面，当distance>list的长度时，可以看做distance = distance-list
        Collections.rotate(list, 6);
        log.info("Collections.rotate(list):{}", list);
        
        /**
         *总结：
         *log.info("原始列表:{}", list);
         *log.info("Collections.reverse(list):{}", list);
         *log.info("Collections.shuffle(list):{}", list);
         *log.info("Collections.sort(list):{}", list);
         *log.info("Collections.sort(list):{}", list);
         *log.info("Collections.swap(list, compatator):{}", list);
         *log.info("Collections.rotate(list):{}", list);
         *
         *上面语句打印的结果如下：
         * 原始列表:[test1, test2, test3, test4, test5]
            Collections.reverse(list):[test5, test4, test3, test2, test1]
            Collections.shuffle(list):[test1, test5, test2, test4, test3]
            Collections.sort(list):[test1, test2, test3, test4, test5]
            Collections.sort(list, compatator):[test5, test4, test3, test2, test1]
            Collections.swap(list):[test3, test4, test5, test2, test1]
            Collections.rotate(list):[test1, test3, test4, test5, test2]
            明白单词的意思，很容易记下Collections工具中排序方法的使用。
         */
        
        
 