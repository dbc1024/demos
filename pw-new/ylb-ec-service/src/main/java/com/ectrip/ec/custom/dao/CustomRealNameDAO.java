package com.ectrip.ec.custom.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.ec.custom.dao.idao.ICustomRealNameDAO;
import com.ectrip.ec.model.user.CustomRealName;

@Repository
public class CustomRealNameDAO extends GenericDao implements ICustomRealNameDAO {
    private static Logger logger = LoggerFactory.getLogger(CustomRealNameDAO.class);

    public CustomRealName get(String id) {
        return (CustomRealName) this.get(CustomRealName.class, id);
    }

    public void oneTimePatchUserBank(){
        try {
            String sql = "select distinct new map(userid as userid, idname as idname, idnumber as idnumber, bindAgrno as bindAgrno) from Userbank";
            List<Map> list = this.find(sql);
            if (list != null && !list.isEmpty()) {
                logger.info("Userbank size:" + list.size());
                Date createDate = new Date();
                for (Map<String, String> map : list) {
                    String userId = map.get("userid");
                    String bindAgrno = map.get("bindAgrno");
                    logger.info(userId);
                    CustomRealName customRealName = this.get(userId);
                    if (customRealName == null) {
                        String sql2 = "select flowid from UserbankFlow where bindAgrno= ? order by bindAgrno";
                        List<String> flowIds = this.find(sql2, new Object[]{bindAgrno});
                        customRealName = new CustomRealName();
                        customRealName.setUserId(userId);
                        customRealName.setRealName(map.get("idname"));
                        customRealName.setIdNumber(DesEncryptUtil.encrypt(map.get("idnumber")));
                        if (flowIds != null && !flowIds.isEmpty()) {
                            customRealName.setFlowId(Long.valueOf(flowIds.get(0)));
                        }
                        customRealName.setCreateDate(createDate);
                        this.save(customRealName);
                    }
                }
            } else {
                logger.warn("Userbank û�����ݣ�");
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

	@Override
	public List getRealByUserIds(String userIds) {
		String[] split = userIds.split(",");
		
		StringBuilder newUserIds = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			if(i!=0) {
				newUserIds.append(",");
			}
			
			newUserIds.append("'"+ split[i]+ "'");
		}
		
		String sql = "select distinct new map(userId as userId, realName as realName, idNumber as idNumber) from CustomRealName crn where crn.userId in("+ newUserIds +")";
		
		List list = this.find(sql);
		
		return list;
	}
}
