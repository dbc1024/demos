package com.ectrip.ec.model.ticket;

import java.io.Serializable;

public class LprPojo implements Serializable{
	private String seq;//��Ʊ�˱��
    private String rzti;// �״���������
    private String szregionalid;// ��Դ�ر��
    private String daoyou;// ��������
    private String daoyouid;// ���α��
    private String password;// ����
    private String zjhm;// ֤������
    private String iscenicid;// �����̱��
    private String mobile;// �ֻ�
    private String providername;
    private String iparentid;
    private String address;
    private String zjlb;
	private String ornote1; //����
	private String ornote2;//����ʱ�� for�Ƶ�
	private String ornote3;
	private String ornote4;
	private String ornote5;
	private String ornote6;
	private String ornote7;
	private String ornote8;
	private String ornote9;
	private String ornote10;
    
    public LprPojo() {
    	
	}

	public LprPojo(String seq, String rzti, String szregionalid, String daoyou,
			String daoyouid, String password, String zjhm, String iscenicid,
			String mobile, String providername, String iparentid,
			String address, String zjlb, String ornote1, String ornote2,
			String ornote3, String ornote4, String ornote5, String ornote6,
			String ornote7, String ornote8, String ornote9, String ornote10) {
		super();
		this.seq = seq;
		this.rzti = rzti;
		this.szregionalid = szregionalid;
		this.daoyou = daoyou;
		this.daoyouid = daoyouid;
		this.password = password;
		this.zjhm = zjhm;
		this.iscenicid = iscenicid;
		this.mobile = mobile;
		this.providername = providername;
		this.iparentid = iparentid;
		this.address = address;
		this.zjlb = zjlb;
		this.ornote1 = ornote1;
		this.ornote2 = ornote2;
		this.ornote3 = ornote3;
		this.ornote4 = ornote4;
		this.ornote5 = ornote5;
		this.ornote6 = ornote6;
		this.ornote7 = ornote7;
		this.ornote8 = ornote8;
		this.ornote9 = ornote9;
		this.ornote10 = ornote10;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvidername() {
        return providername;
    }

    public void setProvidername(String providername) {
        this.providername = providername;
    }

    public String getIparentid() {
        return iparentid;
    }

    public void setIparentid(String iparentid) {
        this.iparentid = iparentid;
    }

    public String getMobile() {
	return mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getIscenicid() {
	return iscenicid;
    }

    public void setIscenicid(String iscenicid) {
	this.iscenicid = iscenicid;
    }

    public String getRzti() {
	return rzti;
    }

    public void setRzti(String rzti) {
	this.rzti = rzti;
    }

    public String getSzregionalid() {
	return szregionalid;
    }

    public void setSzregionalid(String szregionalid) {
	this.szregionalid = szregionalid;
    }

    public String getDaoyou() {
	return daoyou;
    }

    public void setDaoyou(String daoyou) {
	this.daoyou = daoyou;
    }

    public String getDaoyouid() {
	return daoyouid;
    }

    public void setDaoyouid(String daoyouid) {
	this.daoyouid = daoyouid;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getZjhm() {
	return zjhm;
    }

    public void setZjhm(String zjhm) {
	this.zjhm = zjhm;
    }

	public String getOrnote1() {
		return ornote1;
	}

	public void setOrnote1(String ornote1) {
		this.ornote1 = ornote1;
	}

	public String getOrnote2() {
		return ornote2;
	}

	public void setOrnote2(String ornote2) {
		this.ornote2 = ornote2;
	}

	public String getOrnote3() {
		return ornote3;
	}

	public void setOrnote3(String ornote3) {
		this.ornote3 = ornote3;
	}

	public String getOrnote4() {
		return ornote4;
	}

	public void setOrnote4(String ornote4) {
		this.ornote4 = ornote4;
	}

	public String getOrnote5() {
		return ornote5;
	}

	public void setOrnote5(String ornote5) {
		this.ornote5 = ornote5;
	}

	public String getOrnote6() {
		return ornote6;
	}

	public void setOrnote6(String ornote6) {
		this.ornote6 = ornote6;
	}

	public String getOrnote7() {
		return ornote7;
	}

	public void setOrnote7(String ornote7) {
		this.ornote7 = ornote7;
	}

	public String getOrnote8() {
		return ornote8;
	}

	public void setOrnote8(String ornote8) {
		this.ornote8 = ornote8;
	}

	public String getOrnote9() {
		return ornote9;
	}

	public void setOrnote9(String ornote9) {
		this.ornote9 = ornote9;
	}

	public String getOrnote10() {
		return ornote10;
	}

	public void setOrnote10(String ornote10) {
		this.ornote10 = ornote10;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getZjlb() {
		return zjlb;
	}

	public void setZjlb(String zjlb) {
		this.zjlb = zjlb;
	}
}
