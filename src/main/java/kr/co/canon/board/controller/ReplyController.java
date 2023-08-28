package kr.co.canon.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.canon.board.service.ReplyService;

@Controller
@RequestMapping
public class ReplyController {
	
	@Autowired
	private ReplyService rService;
	
	
	
	
}
