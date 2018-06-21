package xzs.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HitRulesVO {
	String ruleName; // 规则名称
	String ruleId; // 规则 ID
	String score;// 规则风险系数，只有权重策略模式下有效
	String decision; //规则决策结果
	String memo; //规则击中信息备注，会根据不同的规则模板返回相对
	
	@JsonProperty("规则名称")
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	@JsonProperty(value = "规则 ID", access = JsonProperty.Access.READ_ONLY)
	public String getRuleId() {
		return ruleId;
	}
	@JsonProperty(value = "ruleId", access = JsonProperty.Access.WRITE_ONLY)
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	@JsonProperty(value = "规则风险系数", access = JsonProperty.Access.READ_ONLY)
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@JsonProperty(value = "规则决策结果", access = JsonProperty.Access.READ_ONLY)
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	@JsonProperty(value = "规则击中信息", access = JsonProperty.Access.READ_ONLY)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
