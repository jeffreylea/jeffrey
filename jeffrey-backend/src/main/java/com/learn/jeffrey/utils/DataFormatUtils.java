/**
 * 
 */
package com.learn.jeffrey.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * <p>
 * description
 * </p>
 * 
 * @author lijianfei
 * @date 2019-04-08
 */
public class DataFormatUtils {

	/**
	 * dependency: dom4j 
	 * 将接收到的XML转为map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream is = request.getInputStream();
		Document doc = reader.read(is);
		Element root = doc.getRootElement();
		List<Element> list = root.elements();
		for (Element e : list) {
			map.put(e.getName(), e.getText());
		}
		is.close();
		return map;
	}
	
	/**
	 * 将map转化为json
	 * dependency: fastjson 
	 * @param map
	 * @return
	 */
	public static Object mapToJson(Object javaObject) {
		return JSONObject.toJSON(javaObject);
	}
	
	/**
	 * dependency: fastjson 
	 * 将json字符串转化为model对象
	 * @param text 
	 * @param clazz 
	 * @return
	 */
	public static <T> T jsonStringToObject(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}
	
	/**
	 * 
	 * 通过Map创建XML,Map可以多层转换
	 * 
	 * @param params
	 * @return	String-->XML
	 */
	public static String mapToXml(String parentName,Map<String, Object> params,boolean isCDATA){
		Document doc = DocumentHelper.createDocument();
		doc.addElement(parentName);
		String xml = iteratorXml(doc.getRootElement(),parentName,params,isCDATA);
		return formatXML(xml);
	}

	/**
	 * 
	 * 通过Map创建XML,Map可以多层转换
	 * 可以自定义parent节点
	 * 
	 * @param params
	 * @return	String-->XML
	 */
	public static String mapToXml(String parentName,Map<String, Object> params){
		Document doc = DocumentHelper.createDocument();
		doc.addElement(parentName);
		String xml = iteratorXml(doc.getRootElement(),parentName,params,false);
		return formatXML(xml);
	}
	
	/**
	 * 
	 * 通过Map创建XML,Map可以多层转
	 * 固定节点parent为Document
	 * 
	 * @param params
	 * @return	String-->XML
	 */
	public static String mapToXml(Map<String, Object> params){
		String parentName = "Document";
		Document doc = DocumentHelper.createDocument();
		doc.addElement(parentName);
		String xml = iteratorXml(doc.getRootElement(),parentName,params,false);
		return formatXML(xml);
	}
	
	/**
	 * 
	 * MapToXml循环遍历创建xml节点
	 * 此方法在value中加入CDATA标识符
	 * 
	 * @param element 根节点
	 * @param parentName 子节点名字
	 * @param params map数据
	 * @return String-->Xml
	 */
	
	@SuppressWarnings("unchecked")
	public static String iteratorXml(Element element,String parentName,Map<String,Object> params,boolean isCDATA) {
		Element e = element.addElement(parentName);
		Set<String> set = params.keySet();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			if(params.get(key) instanceof Map) {
				iteratorXml(e,key,(Map<String,Object>)params.get(key),isCDATA);
			}else {
				String value = params.get(key)==null?"":params.get(key).toString();
				if(!isCDATA) {
					e.addElement(key).addText(value);	
				}else {
					e.addElement(key).addCDATA(value);	
				}
			}
		}
		return e.asXML(); 
	}

	/**
	 * 格式化xml,显示为容易看的XML格式
	 * 
	 * @param inputXML
	 * @return
	 */
	public static String formatXML(String inputXML){
		String requestXML = null;
		XMLWriter writer = null;
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(new StringReader(inputXML));
			if (document != null) {
				StringWriter stringWriter = new StringWriter();
				OutputFormat format = new OutputFormat("	", true);//格式化，每一级前的空格
				format.setNewLineAfterDeclaration(false);	//xml声明与内容是否添加空行
				format.setSuppressDeclaration(false);		//是否设置xml声明头部
				format.setNewlines(true);		//设置分行
				writer = new XMLWriter(stringWriter, format);
				writer.write(document);
				writer.flush();
				requestXML = stringWriter.getBuffer().toString();
			}
			return requestXML;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}finally {
			if (writer != null) { 
				try {
					writer.close();
				} catch (IOException e) {
					
				}
			}
		}
	}
	
	/**
	 * json字符串转为map
	 * @param json
	 * @return
	 */
	public static Map<String,Object> parseToMap(String json) {
		return JSON.parseObject(json, new TypeReference<Map<String,Object>>(){});
	}
	
	/**
	 * 解析URL中参数
	 * @param URL
	 * @return
	 */
    public static Map<String, String> urlSplit(String URL){
        Map<String, String> mapRequest = new HashMap<String, String>();
        String[] arrSplit=null;
        String strUrlParam=truncateUrlPage(URL);
        if(strUrlParam==null){
            return mapRequest;
        }
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit){
              String[] arrSplitEqual=null;         
              arrSplitEqual= strSplit.split("[=]");
              //解析出键值
              if(arrSplitEqual.length>1){
                  //正确解析
                  mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
              }else{
                  if(arrSplitEqual[0]!=""){
                  //只有参数没有值，不加入
                  mapRequest.put(arrSplitEqual[0], "");       
                  }
              }
        }   
        return mapRequest;   
    }
    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     * @author lzf
     */
    private static String truncateUrlPage(String strURL){
        String strAllParam=null;
        String[] arrSplit=null;
        strURL=strURL.trim().toLowerCase();
        arrSplit=strURL.split("[?]");
        if(strURL.length()>1){
          if(arrSplit.length>1){
              for (int i=1;i<arrSplit.length;i++){
                  strAllParam = arrSplit[i];
              }
          }
        }
        return strAllParam;   
    }

}
