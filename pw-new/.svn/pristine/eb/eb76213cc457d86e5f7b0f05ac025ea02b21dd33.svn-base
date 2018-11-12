package com.ectrip.oauth.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ectrip.oauth.business.entity.Esfemployeetab;
import com.ectrip.oauth.business.entity.Galcompanyinfotab;
import com.ectrip.oauth.business.repository.EsfemployeeTabRepository;
import com.ectrip.oauth.business.repository.GalcompanyinfotabRepository;

/**
 * 自定义用户获取方式
 * <p>
 * Created by xw on 2017/3/17.
 * 2017-03-17 20:13
 */
@Service
public class CustomUserService implements UserDetailsService {


	@Autowired
	private EsfemployeeTabRepository userRepository;
	
	@Autowired
	private GalcompanyinfotabRepository galcompanyinfotabRepository;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Esfemployeetab user = userRepository.findOneByEmpid(s);
		if (user == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		Galcompanyinfotab galcompanyinfotab = galcompanyinfotabRepository.findOne(user.getIcompanyinfoid());
		if(galcompanyinfotab != null) {
			user.setSzcompanyinfoname(galcompanyinfotab.getSzcompanyname());
			user.setCompanytype(galcompanyinfotab.getCompanytype());
		}
		return user;
	}
}
