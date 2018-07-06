package xzs.util.zy;

import com.fasterxml.jackson.databind.ObjectMapper;
import xzs.util.zy.RespVO;

/**
 * Created by jj on 2018/7/6.
 */
public class ZYTest {

    public static void main(String[] args) {
        String jsonStr="{\"jobid\":\"20180704100235638100614\",\"bizno\":\"000100150000598\",\"requestsn\":\"1530669755147\",\"responsetime\":\"20180704100235875\",\"consumestate\":\"true\",\"errcode\":\"00000\",\"errmsg\":\"验证通过\",\"result\":{\"resultdata\":\"{\\\"dsorderid\\\":\\\"20180704100235657672275\\\",\\\"merchno\\\":\\\"0000000000003910\\\",\\\"returncode\\\":\\\"2000\\\",\\\"errtext\\\":\\\"查询成功\\\",\\\"transcode\\\":\\\"301\\\",\\\"ordersn\\\":\\\"20180704100235657672275\\\",\\\"orderid\\\":\\\"20180704190148647\\\",\\\"sign\\\":\\\"e98384600d350747993c05b3e6a4c4b9\\\",\\\"platformCode\\\":\\\"001000000\\\",\\\"platformDesc\\\":\\\"查询成功\\\",\\\"data\\\":{\\\"phone\\\":\\\"18810837018\\\",\\\"result\\\":\\\">24\\\",\\\"message\\\":\\\"zhengc\\\"}}\",\"remark\":{\"area_score\":\"74.10\",\"operator_score\":\"59.83\"}}}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            RespVO respVo = mapper.readValue(jsonStr, RespVO.class);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
