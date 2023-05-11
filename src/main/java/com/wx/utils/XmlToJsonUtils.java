//package com.wx.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//public class XmlToJsonUtils {
//
//    public static String xmlToJson(String xmlPath) {
//        try {
//            // 创建一个SAXReader解析器
//            SAXReader reader = new SAXReader();
//
//            // 读取xml文件,转换成Document结点
//            Document doc = reader.read(new File(xmlPath));
//
//            // 递归打印xml文档信息
//            JSONObject jsonObject = xmlToJsonRoot(doc);
//            return jsonObject.toJSONString();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * xml转json对象
//     *
//     * @param document
//     * @return
//     */
//    public static JSONObject xmlToJsonRoot(Document document) {
//        // 取根节点
//        Element root = document.getRootElement();
//        List<Element> elements = root.elements();
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(root.getName(), elementToJson(elements));
//        return jsonObject;
//    }
//
//    /**
//     * 递归读取节点，并简单判定节点状态（值、对象、列表三种状态）
//     *
//     * @param elements
//     * @return
//     */
//    public static Object elementToJson(List<Element> elements) {
//        Object object = null;
//        if (elements.isEmpty()) {
//            return object;
//        }
//        if (elements.size() == 1) {
//            //只有一条，算对象
//            JSONObject jsonObject = new JSONObject();
//            Element element = elements.get(0);
//            List<Element> children = element.elements();
//            if (!children.isEmpty()) {
//                jsonObject.put(element.getName(), elementToJson(children));
//            } else {
//                jsonObject.put(element.getName(), element.getText());
//            }
//            object = jsonObject;
//            return object;
//        }
//        if (!elements.get(0).getName().equals(elements.get(1).getName())) {
//            //同一层级的名称不一样，不是列表
//            JSONObject jsonObject = new JSONObject();
//            for (Element element : elements) {
//                List<Element> children = element.elements();
//                if (!children.isEmpty()) {
//                    jsonObject.put(element.getName(), elementToJson(children));
//                } else {
//                    jsonObject.put(element.getName(), element.getText());
//                }
//            }
//            object = jsonObject;
//            return object;
//
//        }
//        //同一层级的名称一样，是列表
//        List<JSONObject> jsonList = new ArrayList<>();
//        for (Element element : elements) {
//            JSONObject jsonObject = new JSONObject();
//            List<Element> children = element.elements();
//            if (!children.isEmpty()) {
//                jsonObject = (JSONObject) elementToJson(children);
//            } else {
//                jsonObject.put(element.getName(), element.getText());
//            }
//            jsonList.add(jsonObject);
//        }
//        object = jsonList;
//        return object;
//    }
//}