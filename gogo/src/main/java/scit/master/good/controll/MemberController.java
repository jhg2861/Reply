package scit.master.good.controll;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import scit.master.good.dao.MemberRepository;
import scit.master.good.vo.Member;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberRepository repository;
	
	@RequestMapping("/join")
	public String join() {
		
		return "member/joinForm";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(Member member) {
		logger.info("member : {}", member.toString());
		
		int result = repository.join(member);
		logger.info("회원가입 결과 : {}", result);
		
		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String login() {
		
		return "member/loginForm";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Member member, Model model, HttpSession session) {
		Member m = repository.login(member);
		
		String message = "";
		if(m == null) {
			message = "로그인을 할 수 없습니다.";
			model.addAttribute("message", message);
			
			return "member/loginForm";
		} else {
			session.setAttribute("loginId", m.getUserid());
			session.setAttribute("loginName", m.getUsername());
			return "redirect:/";
		}
	
	
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/modify")
	public String modify(Member member, Model model) {
		Member m = repository.selectMember(member);
		model.addAttribute("member", m);
		return "member/modifyForm";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(Member member) {
		
		int result = repository.updateMember(member);
		
		return "redirect:/";
	}
	
}























