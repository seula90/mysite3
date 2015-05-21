package com.sds.icto.mysite.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.repository.BoardDao;


@Service
public class BoardService {

	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> list(){
		List<BoardVo> list = boardDao.fetchList();
		return list;
	}
	
	public void boardInsert(BoardVo vo){
		boardDao.insert(vo);
	}
	
	public void boardDelete(BoardVo vo){
		boardDao.delete(vo);
	}
	
	public void boardUpdate(BoardVo vo){
		boardDao.Update(vo);
	}
	
	public void boardUpdateViewcnt(BoardVo vo){
		boardDao.UpdateViewcnt(vo);
	}
	
	public BoardVo boardView(Long no){
		BoardVo vo2 = boardDao.View(no);
		return vo2;
	}
	
	public List<BoardVo> boardFind(String kwd){
		List<BoardVo> findlist = boardDao.findList(kwd);
		return findlist;
	}
}
