package xzs.util;

public class DeleteCode {
//	/**
//	 * 
//	 * @param bqsRiskResp
//	 *            resource
//	 * @param riskInfoVO
//	 */
//	private void copyProperties(BQSRiskResp bqsRiskResp, RiskInfoResp riskInfoVO) {
//
//		riskInfoVO.setFinalDecision(getValueDecision(bqsRiskResp.getFinalDecision()));
//		riskInfoVO.setResultCode(getValueResultCode(bqsRiskResp.getResultCode()));
//		riskInfoVO.setResultDesc(bqsRiskResp.getResultDesc());
//		riskInfoVO.setFlowNo(bqsRiskResp.getFlowNo());
//		riskInfoVO.setFinalScore(bqsRiskResp.getFinalScore());
//
//		String _strategyName = ""; // 策略名称
//		String _strategyDecision = ""; // 策略决策结果
//		String _riskType = ""; // 策略风险类型
//		String _ruleName = ""; // 规则名称
//		String _decision = ""; // 规则决策结果
//		String _memo = ""; // 规则击中信息备注，会根据不同的规则模板返回相对
//		StrategySetVO[] strategySets = bqsRiskResp.getStrategySet();
//		for (StrategySetVO strategySet : strategySets) {
//			_strategyName = _strategyName + "|" + strategySet.getStrategyName();
//			_strategyDecision = _strategyDecision + "|" + getValueDecision(strategySet.getStrategyDecision());
//			_riskType = _riskType + "|" + getValueRiskType(strategySet.getRiskType());
//			HitRulesVO[] hitRuless = strategySet.getHitRules();
//			_ruleName = _ruleName + "|";
//			_decision = _decision + "|";
//			_memo = _memo + "|";
//			for (HitRulesVO hitRules : hitRuless) {
//				_ruleName = _ruleName + hitRules.getRuleName() + ";";
//				_decision = _decision + getValueDecision(hitRules.getDecision()) + ";";
//				_memo = _memo + hitRules.getMemo() + ";";
//			}
//		}
//		riskInfoVO.setStrategyName(_strategyName);
//		riskInfoVO.setStrategyDecision(_strategyDecision);
//		riskInfoVO.setRiskType(_riskType);
//		riskInfoVO.setRuleName(_ruleName);
//		riskInfoVO.setDecision(_decision);
//		riskInfoVO.setMemo(_memo);
//	}
}
