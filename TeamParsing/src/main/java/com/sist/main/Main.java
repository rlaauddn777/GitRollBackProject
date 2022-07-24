package com.sist.main;
import com.sist.db.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;



/*
CREATE TABLE School_info_test
(
  no        NUMBER        NOT NULL ,
  name      VARCHAR(100)  NOT NULL ,
  image     VARCHAR(260)  NOT NULL,
  price     int  NOT NULL,
  category  VARCHAR2(200) NULL    ,
  PRIMARY KEY (no)
);


*/



public class Main {
	
	public void SchoolInfo()
	{
		try 
		{
			//File input = new File("C:\\sist\\webDev\\webStudy\\GroupProject_1\\src\\main\\webapp\\以묒꽦�솕data.html");
			
			DAO dao=new DAO();
			Get_DetailLink gdl=new Get_DetailLink();
			List<String> list=gdl.DetailLinkData();
			
			//Document doc = (Document) Jsoup.connect("https://www.mypetplus.co.kr"+gdl.DetailLinkData()).get();
			for(int i=0; i<11; i++)
	        {
				Document doc = (Document) Jsoup.connect("https://www.mypetplus.co.kr"+list.get(i)).get();
				Elements name=doc.select("h2.tit");
				Elements price=doc.select("div.prod-gud dd.price strong");
				Elements store=doc.select("div.name strong");
				Elements s_info=doc.select("div.dv-tit p.sub");
				Elements poster=doc.select("div.prod-thumb div.thumb img");///Upload/Product/Pro_�긽�꽭硫붿씤_以묒꽦�솕_34.jpg
				Elements img=doc.select("div.prod-bx div.dl01 img"); ////Upload/Product/Pro_�긽�꽭_以묒꽦�솕_208.jpg
				Elements address=doc.select("div.name span");
				System.out.println(name.get(0).text());
				System.out.println(price.get(0).text());
				System.out.println(store.get(0).text());
				System.out.println(s_info.get(0).text());
				System.out.println(poster.get(0).attr("src"));
				System.out.println(img.get(2).attr("src"));
				System.out.println(address.get(0).text());
				System.out.println("=======================");
	        
			
					
				VO m=new VO();
//				m.setNo(j+1); price.get(i).text().replace(",", "") prod-gud dd.price strong
//				probx div.price strong
				m.setName(name.get(0).text());
				m.setPrice(price.get(0).text().replace(",", ""));
				m.setStore(store.get(0).text());
				m.setS_info(s_info.get(0).text());
				m.setPoster("mypetplus.co.kr"+poster.get(0).attr("src"));
				//m.setImg("mypetplus.co.kr"+img.get(2).attr("src"));
				   if(img.get(2).attr("src").contains("상품가"))
		            {
		               m.setImg("mypetplus.co.kr/Upload/Product/Pro_상세_심장사상충_11.jpg");
		            }
				   else if(img.get(2).attr("src").contains("인스타")) 
				   {
					   m.setImg("mypetplus.co.kr/Upload/Product/Pro_상세_심장사상충_11.jpg");
				   }
				   else if(img.get(2).attr("src").contains("링크용")) 
				   {
					   m.setImg("mypetplus.co.kr/Upload/Product/Pro_상세_심장사상충_11.jpg");
				   }
		            else
		            {
		               m.setImg("mypetplus.co.kr"+img.get(2).attr("src"));
		            }

				m.setAddress(address.get(0).text());
				dao.dataInsert(m);
				
	        }
//				if(poster.get(j).attr("src") == null)
//				{
//					m.setPoster("");
//				} 
//				else {
//					m.setPoster("mypetplus.co.kr"+poster.get(j).attr("src"));
//				}
//		
//						m.setPrice(Integer.parseInt(price.get(j).text().replaceAll(null, null)));
//						String pric=toString(price.get(j)).replaceAll(",", "");
//						int pr=Integer.parseInt(price);
//						m.setPrice(pr);

					//dao.dataInsert(m);
			
			System.out.println("저장완료");
//				}
		}catch (Exception e) {}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Main m=new Main();
        m.SchoolInfo();
	}
}