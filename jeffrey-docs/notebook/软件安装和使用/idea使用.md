# idea忽略隐藏文件
setting ->edit ->file type设置下方的ignore文件或文件夹，例如忽略.iml文件，就在输入框中添加.iml。

# idea中像eclipse一样在同一个窗口中打开多个项目
在Idea一个窗口中显示多个项目，由于idea没有workspace空间的概念，idea中的项目就相当于
eclipse中空间的概念，idea中可以导入多个模块。首先选择一个项目文件夹作为项目打开，然后打开project structure项目结构，点击+号导入或新建模块，也可以在maven管理窗口中，点击+号添加maven项目，选择相应的pom文件，两者做法效果是一样的。

# idea编辑MD文档不能自动换行
setting ->edit ->general，拉到soft wrap标题中，勾选soft-wrap files，输入框中如果没有则添加相应的文件类型，例如：```*.md; *.txt;```,再次编辑这些文档时，就会自动换行了。

# idea 中管理所有服务的services窗口
首先要在service工具窗口中指定所要显示的类型，指定的方式：Run-> Edit Configurations ,选择 Templates，将需要显示的配置添加到列表configuration variable in service中，比如要显示spring boot类型的服务，就添加到列表中，此时在窗口中就显示出了所有的springboot服务，可以根据类型和组显示服务。

# idea在创建类时自动添加类的注释
在创建类时，需要自动生成注释，比如作者，时间，描述信息等。设置路径setting ->edit ->file and code template，点击选择include选项，选择file Header，file Header是idea中自带的模板名称，默认是空的，可以在里面编辑，编辑模板如：
 ```
/**
 *@Author ${USER}
 *@Date ${DATE} ${TIME}
**/
 ```
再次创建类时就会生成作者和时间信息。也可以自定义模板，但要在类文件中设置下，设置方式：setting ->edit ->file and code template，选择file，选择class，可以看到模板信息，把模板信息中的#parse("File Header.java")改为自定义的名称，默认的名称是File Header.java。

# idea中properties文件不显示中文或乱码问题
设置方法：setting ->file encodings,在properties files下设置默认编码为UTF8，并勾选 Transparent native-to-ascii conversion选项。

# idea中提示Wrong tag 解决方法
在idea使用自定义注释的时候，会提示```Wrong tag "XXX"```，解决的方式提示```"add "XXX" to custom tag```,点击就能消除提示解决问题，但问题是这个custom tag在哪呢，在提示中还有一个快捷方式叫more actions，更多操作，点击之后可以看到在```add "XXX" to custom tag```后还有选项，其中有个`edit inspection profile setting`,点击之后会打开窗口： ![自定义tag添加删除方法](/jeffrey-docs/image/自定义tag添加删除方法.png),同样可以在设置窗口找，可以看到在`additional Javadoc Tags`下就有我们刚才在操作`add "XXX" to custom tag`时的`"XXX"`,想删除或者添加直接在里面写就可以，多个tag就用逗号分开。这样就可以解决提示`Wrong tag` 的问题了。

# idea 自动导入时出现*的问题
File->settings->Editor->Code Style->java->imports,设置参数999

# idea背景主题模式设置
File->settings->Editor->color scheme,选择对应的模式即可设置

# idea 中Ctrl + shift+ F失效解决方式
这个快捷键是搜索字符串的，我经常用到，但是由于快捷键的冲突，在idea中失效了，只有从菜单中调出，比较麻烦。我的是由于和搜狗输入法中的快捷键冲突，在搜狗输入法中这个快捷键的作用是简繁字体切换，搜狗输入法中的这个快捷键我是不用的，所以就把他修改成了其他的，这样在idea中字符串搜索快捷键就可以正常使用了。

# idea 工具栏显示位置
菜单View->Toolbar，选中靠左显示，取消靠又显示，默认是不选中的。

# idea打开多个文件显示多行设置
File->settings->Editor->general->edit tabs,取消选中show tabs in one row

# idea中无法打印汉字解决方式
使用的输入法是搜狗输入法，突然无法输入中文，切换到中文模式，打出的提示也是英文的，使用快捷键Ctrl+Shift+E就可以切换到正常状态，应该是不小心按到这个快捷键了。这个快捷键是搜狗输入法的快捷键，是英文不全快捷键。

# idea 不区分大小写设置
File->settings->Editor->general->code completion,取消勾选match case，这个默认是选上的。

# idea 自动导入类设置
Editor>General>Auto Import，勾选上add unambiguous imports on the fly选项，应用后会自动导入对应的类，Optimize imports on the fly选项勾选后，可以帮助我们去掉不需要的类



idea2020.1激活：

http://fls.jetbrains-agent.com



参考：https://blog.csdn.net/Du939/article/details/106518131/







