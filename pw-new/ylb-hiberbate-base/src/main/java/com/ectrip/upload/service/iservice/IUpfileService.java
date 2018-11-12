package com.ectrip.upload.service.iservice;

import java.io.File;
import java.util.List;

import com.ectrip.upload.model.Upfile;

public interface IUpfileService {

	public void saveSingleFile(File file, String fileName, Upfile upfile, int i,String waterMark);

	public void saveMultipeFile(File[] uploads, String[] uploadFileNames,
			String[] uploadContentTypes, String[] upid, String[] upname,
			String[] upfrom, String[] note, String[] author,String[] utype, 
			String[] upfilename, String[] upadder, String abelong,String waterMark);

//	public List findUpfiles(String abelong, String avalue);
}
