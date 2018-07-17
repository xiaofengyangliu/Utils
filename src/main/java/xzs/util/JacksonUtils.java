package xzs.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {
	public static ObjectMapper mapper = new ObjectMapper();
	/**
	 *
	 * @param object
	 * @return
	 */
	public static String bean2Json(Object object){
		String jsonStr="";
		try {
			jsonStr = mapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	public static Object json2Bean(String jsonStr,Class<?> c){
		Object o = null ;
		try {
			o =mapper.readValue(jsonStr, c);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return o;

	}
}
