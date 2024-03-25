package com.board.user.controller;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Users")
public class UserController {
	
	@Autowired
	private UserMapper userMapper;

	// home.jsp 의 '/Users/List' : 사용자 목록
	@RequestMapping("/List")
	public ModelAndView list() {
		
		// 사용자 목록 조회(= db 조회)  ->  db : usermapper 한테 시킴 -> getUserList(= interface, 해당 id 의 xml 실행)
		List<UserVo> userList = userMapper.getUserList();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject( "userList", userList );
		mv.setViewName("users/list");
		
		// .jsp 이동
		return mv;
	}
	
	//  /Users/WriteForm
	@RequestMapping("/WriteForm")
	public ModelAndView write() {
		
		ModelAndView   mv     = new ModelAndView();
		LocalDateTime  today  = LocalDateTime.now();
		String         now    = today.format(DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss:SSSS"));
		DayOfWeek      wkday  = today.getDayOfWeek();
		               now    += " " + wkday;
		mv.addObject( "now", now );
		mv.setViewName("users/write");
		return         mv;
	}
	
	//  /Users/Write
	@RequestMapping("/Write")
	public ModelAndView write( UserVo userVo ) {
		
		// 저장 -> mapper 에 insert user
		userMapper.insertUser( userVo );
		
		
		// 데이터를 가지고 이동한다
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/Users/List");
		return mv;
	}
	
	// http://localhost:9090/Users/View?userid=lupy0505
	@RequestMapping("/View")
	public ModelAndView view( UserVo userVo ) {
		
		// db 에서 userid=lupy0505 조회
		// 조회 : lupy0505 인 id 를 들고가서 한줄을 가져옴 -> 어디에 담아서?
		// UserVo vo = userMapper.getUser( userVo );		// userMapper 안에 있는 getUser 를 들고올건데 userVo 를 담아서 들고 올거야
		HashMap<String, Object> map = userMapper.getUser( userVo );		// UserMapper.java (interface) 수정 함으로 여기도 수정해줘야함
		
		// System.out.println( "vo: " + vo );		// vo: UserVo(userid=lupy0505, passwd=0505, username=루피, email=lupy@abc.com, upoint=0, indate=2024-03-22)
		log.info("map: {}" + map);		// 20240325 11:23:55.190 [http-nio-9090-exec-1] INFO c.b.u.c.UserController - vo: {}UserVo(userid=lupy0505, passwd=0505, username=루피, email=lupy@abc.com, upoint=0, indate=2024-03-22) 
		
		// map.get("userid")
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("vo", map);		// "vo" : view.jsp 에서 ${ vo.userid } 의 vo
		mv.setViewName("users/view");
		return mv;
	}
	
	//  /Users/UpdateForm?userid=sky
	@RequestMapping("/UpdateForm")
	public ModelAndView updateForm( UserVo userVo ) {
		
		// 아이디(userid)로 수정할 한명의 data 를 조회 -> getView
		// userMapper.getUser( userVo );  <-  getUser 에 F2 누르면 HashMap<String, Object> 앞에 나옴(HashMap 을 가져온다는거) -> 복붙, 아래줄
		HashMap<String, Object> map = userMapper.getUser( userVo );
		
		// 수정할 data 를 Model 에 담는다
		ModelAndView mv = new ModelAndView();
		mv.addObject( "vo", map );
		
		// 이동한다
		mv.setViewName("users/update");
		
		return mv;
	}
	
	
}
