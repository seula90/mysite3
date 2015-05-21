package com.sds.icto.mysite.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.sds.icto.mysite.domain.BoardVo;


@Repository
public class BoardDao {
	
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;

	public void insert(BoardVo vo) {

		sqlMapClientTemplate.insert("board.insert", vo);
	}

	public void delete(BoardVo vo) {

		sqlMapClientTemplate.delete("board.delete", vo);
	}

	public void Update(BoardVo vo) {

		sqlMapClientTemplate.update("board.update", vo);
	}

	public void UpdateViewcnt(BoardVo vo) {
		
		sqlMapClientTemplate.update("board.updateViewcnt", vo);
	}

	public BoardVo View(Long no) {
		
		BoardVo vo2 = (BoardVo) sqlMapClientTemplate.queryForObject("board.view", no);
		return vo2;

	}

	public List<BoardVo> findList(String kwd) {

		List<BoardVo> findlist = new ArrayList<BoardVo>();

		findlist= (List<BoardVo>) sqlMapClientTemplate.queryForList("board.find", kwd);
		
		return findlist;
	}

	public List<BoardVo> fetchList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		list = (List<BoardVo>) sqlMapClientTemplate.queryForList("board.list");
		
		return list;
	}

}
