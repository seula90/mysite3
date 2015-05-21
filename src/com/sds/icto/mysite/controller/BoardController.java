package com.sds.icto.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sds.icto.mysite.domain.BoardVo;
import com.sds.icto.mysite.domain.GuestBookVo;
import com.sds.icto.mysite.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
		
	@RequestMapping(value={"","/","/list"}, method=RequestMethod.GET)
	public String list(Model model){
		List<BoardVo> list = boardService.list();
		model.addAttribute("list",list);
		return "board/list";
	}
	
	@RequestMapping(value={"","/","/write"})
	public String write(){
		return "board/write";
	}
	
	@RequestMapping(value={"","/"}, method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVo vo){
		boardService.boardInsert(vo);
		return "redirect:/board";
	}
	
		
	@RequestMapping(value={"/delete/{no}"}, method=RequestMethod.GET)
	public String deleteForm(@PathVariable Long no, Model model){
		model.addAttribute("no",no);
		return "board/deleteform";
	}
	
	@RequestMapping(value={"/delete"}, method=RequestMethod.POST)
	public String delete(@ModelAttribute BoardVo vo){
		boardService.boardDelete(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value={"/view/{no}"}, method=RequestMethod.GET)
	public String view(@PathVariable Long no, Model model){
		model.addAttribute("no",no);
		BoardVo vo2 = boardService.boardView(no);
		model.addAttribute("vo2",vo2);
		boardService.boardUpdateViewcnt(vo2);
		return "board/view";
	}
	
	@RequestMapping(value={"/modify/{no}/{authNo}"}, method=RequestMethod.GET)
	public String modifyForm(@PathVariable Long no, 
			@PathVariable Long authNo, Model model){
		model.addAttribute("no",no);
		model.addAttribute("authNo",authNo);
		BoardVo vo = boardService.boardView(no);
		model.addAttribute("vo",vo);
		return "board/modify";
	}
	
	@RequestMapping(value={"/modify"}, method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo, Model model){
		
		boardService.boardUpdate(vo);
		BoardVo vo2 = boardService.boardView(vo.getNo());
		model.addAttribute("vo2",vo2);
		return "board/view";
	}
	
	@RequestMapping(value={"/find"}, method=RequestMethod.POST)
	public String findlist(@RequestParam String kwd, Model model){
		
		List<BoardVo> findlist = boardService.boardFind(kwd);
		model.addAttribute("list",findlist);
		return "board/list";
	}

	
}
	
	
	