package com.marquess.learn.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.marquess.learn.vo.CFResponseVO;

@RestController
public class StockDownloadController {
	private static Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Scheduled(cron = "0/20 * * * * ? ")
    public void download() {
    	String baseUrl = "http://mdfm.eastmoney.com/EM_UBG_MinuteApi/Js/Get";
    	String dType = "all";
    	String token = "44c9d251add88e27b65ed86506f6e5da";
    	Integer rows = 144;
    	String cb = "jQuery17202058452988701962_1522999129449";
    	Integer page = 1;
    	String id = "300075";
    	
    	String url = "http://mdfm.eastmoney.com/EM_UBG_MinuteApi/Js/Get?dtype=all&token=44c9d251add88e27b65ed86506f6e5da&rows=144&cb=jQuery17202058452988701962_1522999129449&page=6&id=3000752&gtvolume=&sort=&_=1522999259699";
    	try{
    		Document doc = Jsoup.connect(url).get();
    		String content = doc.body().text();
    		System.out.println("hhh" + content);
    		int beg = content.indexOf("(");
    		int end = content.indexOf(")");
    		String jsonData = content.substring(beg+1, end);
    		System.out.println("hhh" + content.substring(beg+1, end));
    	
    	    CFResponseVO response = JSON.parseObject(jsonData, CFResponseVO.class);
    	    Map<String, Object> value = JSONObject.toJavaObject((JSONObject)response.getValue(), Map.class);
    	    System.out.println("ooooo"+ value.get("pc"));
    	    List<String> data = JSON.parseArray(value.get("data").toString(), String.class);//JSONObject.toJavaObject((JSONObject)value.get("data"), List.class);
    	    for(String row : data) {
    	    	System.out.println(row);
    	    }
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
