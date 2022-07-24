package com.sist.main;
import com.sist.db.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;



/*
CREATE TABLE hospital_info_test
(
  no        NUMBER        NOT NULL ,
  name      VARCHAR(100)  NOT NULL ,
  image     VARCHAR(260)  NOT NULL,
  price     int  NOT NULL,
  category  VARCHAR2(200) NULL    ,
  PRIMARY KEY (no)
);


*/



public class Get_DetailLink {

public List<String> DetailLinkData()
	{
		List<String> list=new ArrayList();
		try
		{
			File input = new File("C:\\WebDev\\heart.html");
			Document doc = Jsoup.parse(input, "UTF-8");
			Elements link=doc.select("div.probx a");

			for(int j=0;j<link.size();j++) {
				try 
				{	
					//Get_DetailLink vo=new Get_DetailLink();
					String s=link.get(j).attr("href");
					Document doc2=Jsoup.connect("https://www.mypetplus.co.kr"+s).get();
		    		if(s.contains("/Product/ProductInfo/Detail/"))
		    		{
		    			list.add(s);
		    			//System.out.println(s);
		    		}
				}catch(Exception ex)
				{
						ex.printStackTrace();
				}
			}
		}catch (Exception e) {}
		return list;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Get_DetailLink m=new Get_DetailLink();
        m.DetailLinkData();
        
        /*Get_DetailLink_test gdl=new Get_DetailLink_test();
        List<String> list=gdl.DetailLinkData();
        for(int i=0; i<list.size(); i++)
        {
        	System.out.println(i);
        }*/
	}
}
