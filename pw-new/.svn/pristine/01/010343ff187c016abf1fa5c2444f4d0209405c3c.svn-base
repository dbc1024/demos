package com.ectrip.ec.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.ec.model.user.Userbank;
import com.ectrip.ec.user.dao.idao.IUserbankDAO;
@Repository
public class UserbankDAO extends GenericDao implements IUserbankDAO {

    /**
     * ����ĳ�û��󶨵�ĳ�����п���Ϣ
     * @param userId
     * @param cardNum
     * @return
     */
    public Userbank findUserBank(String userId, String cardNum){
        List<Userbank> userbanks = (List<Userbank>)this.find("select ub from Userbank ub where ub.userid=? and ub.cardnumber=?", new Object[]{userId, cardNum});
        if(userbanks == null || userbanks.isEmpty()){
            return null;
        }
        return userbanks.get(0);
    }

    /**
     * ����ĳ�û���ʵ����Ϣ
     * @param userId
     * @return
     */
    public Userbank findUserBank(String userId){
        List<Userbank> userbanks = (List<Userbank>)this.find("select ub from Userbank ub where ub.userid=? and ub.idname is not null and ub.idnumber is not null", new Object[]{userId});
        if(userbanks == null || userbanks.isEmpty()){
            return null;
        }
        return userbanks.get(0);
    }

}

