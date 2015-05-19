package com.sds.icto.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.GuestBookVo;

import com.sds.icto.mysite.repository.GuestBookDao;

@Service
public class GuestbookService {

	@Autowired
	GuestBookDao guestbookDao;
	
	public List<GuestBookVo> list(){
		List<GuestBookVo> list = guestbookDao.fetchList();
		return list;
	}
	
	public void guestbookInsert(GuestBookVo vo){
		guestbookDao.insert(vo);
	}
	
	public void guestbookDelete(GuestBookVo vo){
		guestbookDao.delete(vo);
	}
		
}
