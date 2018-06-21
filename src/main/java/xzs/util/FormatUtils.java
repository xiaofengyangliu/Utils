package xzs.util;

import java.util.Map;

public class FormatUtils {
	/**
	 * 格式化
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);

			// 遇到{ [换行，且下一行缩进
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;

			// 遇到} ]换行，当前行缩进
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;

			// 遇到"]}之后的,换行
			case ',':
				sb.append(current);
				char beforeThis = jsonStr.charAt(i-1);
				String ruleStr = "\"]}";
				if(ruleStr.indexOf(beforeThis)!=-1){  
					if (last != '\\') {
						sb.append('\n');
						addIndentBlank(sb, indent);
					}
				} 
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}
	
	public static String mapToStr(Map<String,Object> map){
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("{");
		for(String key : map.keySet()){
			Object value = map.get(key);
			strBuff.append(key+"="+value.toString()+",");
		}
		String str = strBuff.substring(0, strBuff.length()-1)+"}";
		return str;
		
	}

	/**
	 * 添加space
	 * 
	 * @param sb
	 * @param indent
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}

	public static void main(String[] args) {
		String resultData = "{\"result\":\"-1\"}";
		String info = "{\"finalDecision\":\"Reject\",\"finalScore\":\"0\",\"flowNo\":\"18051416314852B0EF3625421B88FFE9FDE35180514\",\"resultCode\":\"BQS000\",\"strategySet\":[{\"hitRules\":[{\"decision\":\"Review\",\"detail\":[{\"name\":\"总数\",\"type\":\"all\",\"value\":\"2\"},{\"name\":\"其他\",\"type\":\"single\",\"value\":\"2\"}],\"memo\":\"总数:2,其他:2\",\"ruleId\":\"288764\",\"ruleName\":\"手机号12小时内多头申请过多\",\"score\":0,\"template\":\"realTimeMultiLoan\"},{\"decision\":\"Review\",\"detail\":[{\"name\":\"总数\",\"type\":\"all\",\"value\":\"11\"},{\"name\":\"持牌消费金融\",\"type\":\"single\",\"value\":\"2\"},{\"name\":\"P2P理财\",\"type\":\"single\",\"value\":\"4\"}],\"memo\":\"总数:11,持牌消费金融:2,P2P理财:4\",\"ruleId\":\"288755\",\"ruleName\":\"手机号三天内多头借贷过多\",\"score\":0,\"template\":\"multiLoan\"}],\"riskType\":\"creditRisk\",\"strategyDecision\":\"Review\",\"strategyId\":\"62f4e25441c44182b66153247572452d\",\"strategyMode\":\"WorstMode\",\"strategyName\":\"多头风险策略\",\"strategyScore\":0},{\"hitRules\":[{\"decision\":\"Review\",\"detail\":[{\"firstType\":\"信贷行业\",\"grade\":\"灰名单\",\"secondType\":\"模型分值低\"}],\"memo\":\"灰名单-信贷行业-模型分值低\",\"ruleId\":\"288725\",\"ruleName\":\"身份证比对信贷行业模型分值低名单\",\"score\":0,\"template\":\"compare\"},{\"decision\":\"Reject\",\"detail\":[{\"firstType\":\"信贷行业\",\"grade\":\"黑名单\",\"secondType\":\"骗取补贴\"}],\"memo\":\"黑名单-信贷行业-骗取补贴\",\"ruleId\":\"288727\",\"ruleName\":\"身份证比对信贷行业骗取补贴名单\",\"score\":0,\"template\":\"compare\"}],\"riskType\":\"suspiciousAction\",\"strategyDecision\":\"Reject\",\"strategyId\":\"fa60aea742374bb4b96c1b20d69a9e7a\",\"strategyMode\":\"WorstMode\",\"strategyName\":\"失信风险策略\",\"strategyScore\":0}]}";
		String xmlStr = formatJson(info);
		System.out.println(xmlStr);
	}
}
