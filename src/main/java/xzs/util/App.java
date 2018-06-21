package xzs.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xzs.vo.HitRulesVO;
import xzs.vo.JacksonTest;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	String str = "{\"decision\":\"Review\",\"detail\":[{\"name\":\"总数\",\"type\":\"all\",\"value\":\"11\"},{\"name\":\"持牌消费金融\",\"type\":\"single\",\"value\":\"2\"},{\"name\":\"P2P理财\",\"type\":\"single\",\"value\":\"4\"}],\"memo\":\"总数:11,持牌消费金融:2,P2P理财:4\",\"ruleId\":\"288755\",\"ruleName\":\"手机号三天内多头借贷过多\",\"score\":0,\"template\":\"multiLoan\"}";
//    	String result = "";
//    	try {
//    		HitRulesVO hitRulesVO = JacksonUtils.mapper.readValue(str, HitRulesVO.class);
//			result = JacksonUtils.mapper.writeValueAsString(hitRulesVO);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	System.out.println(FormatUtils.formatJson(str));
//    	System.out.println("---------------");
//    	System.out.println(FormatUtils.formatJson(result));
////    	 String colorJson = "{\"color\":\"red\"}";
//    	    ObjectMapper mapper = new ObjectMapper();
//    	    JacksonTest colotObject;
//			try {
//				 colotObject = mapper.readValue(colorJson, JacksonTest.class);
//				 String ser = mapper.writeValueAsString(colotObject);
//				 System.out.println("Serialized colotObject: " + ser);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	String str = "%E6%B5%8B%E8%AF%95";
//    			
//    	try {
//    		
//			System.out.println( URLDecoder.decode(str,"UTF-8"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	    String s= "{\"data\":{\"desc\":\"人脸比对成功\",\"score\":\"45.322943\"},\"guid\":\"20180518145832_9zqitG4L_1074352\",\"RESULT\":\"1\",\"MESSAGE\":\"身份证号与姓名匹配，且比对成功\"}";
    	    System.out.println(FormatUtils.formatJson(s));
    	    int type=1;
    	    switch (type) {
			//学历查询
			case 1:
				System.out.println(type);
				break;
			//在网时长查询
			case 2:
				System.out.println(type);
				break;
			//在网状态查询
			case 3:
				System.out.println(type);
				break;
			//风险名单查询
			case 4:
				System.out.println(type);
				break;
			//人像比对查询
			case 5:
				System.out.println(type);
				break;
			default:
				break;
			}
    }
    
}
