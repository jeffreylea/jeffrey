# 关于JSON的解析
这两天在读取一个JSON文件。JSON解析本来是很简单的事，但由于自己太菜，折腾了很久。
首先是读取JSON文件，以流的方式读取并返回JSON字符串。

```
/**
     * 读取json文件
     * 
     * @param fileName json文件名
     * @return 返回json字符串
     */
    public static String readJsonFile(File jsonFile) {
        String jsonStr = "";
        log.info("————开始读取" + jsonFile.getPath() + "文件————");
        try {
            // File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            log.info("————读取" + jsonFile.getPath() + "文件结束!————");
            return jsonStr;
        } catch (Exception e) {
            log.info("————读取" + jsonFile.getPath() + "文件出现异常，读取失败!————");
            e.printStackTrace();
            return null;
        }
    }
```

返回JSON字符串
