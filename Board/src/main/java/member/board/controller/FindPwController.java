package member.board.controller;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.board.dto.MemDto;
import member.board.service.EmailService;
import member.board.service.MemService;
import member.board.vo.EmailVO;

@Controller
public class FindPwController {
	
	@Autowired
	MemService memservice;
	
	@Autowired
	EmailService emailservice;
	

	@GetMapping("/findpw")
	public String findpw() {
		return "mem/findpw";
	}
	
	@GetMapping("/findpw2")
	public String findpw2() {
		return "mem/findpw2";
	}
	
	@GetMapping("/findpwresult")
	public String findpwresult(@ModelAttribute("command") MemDto dto, BindingResult error, @RequestParam("email")String emailAddress) throws Exception {
		String newPw = createNewPassword(); // 새로운 비번
		dto.setPassword(newPw);
		int count = memservice.updatePassword(dto);
		
		
		if(count==0) {// id, name 틀림
			error.reject("nocode","해당 아이디,이름의 유저가 없습니다.");
			return "mem/findpw2";
		}else {// 이메일 전송, loginform
			EmailVO email = new EmailVO();
			String receiver = emailAddress; // Receiver.
			String subject = "새로운 비번";
			String content = "새 비밀 번호는 "+newPw+"입니다.";
		
			email.setReceiver(receiver);
			email.setSubject(subject);
			email.setContent(content);
			Boolean result = emailservice.sendMail(email);
			
			return "mem/loginform";
		}
		
		
	}
	
	
	@RequestMapping("/sendmail")
	@ResponseBody
	public String[] id_name_ck(@RequestParam("emailAddress") String emailAddress,
			@RequestParam("id")String id, @RequestParam("name") String name) throws Exception {
		// 아이디, 이름 확인
		int check = memservice.id_name_ck(id, name);
		
		if(check != 1) {
			return new String[] {"","false"};
		}else {// 유저 정보 존재
			// 메일 전송
			EmailVO email = new EmailVO();
			String receiver = emailAddress; // Receiver.

			String subject = "새로운 비번";

			String pw = createNewPassword();
			
			String content = "새 비밀 번호는 "+pw+"입니다.";
			
			email.setReceiver(receiver);
			email.setSubject(subject);
			email.setContent(content);
			Boolean result = emailservice.sendMail(email);
			memservice.updatePw(pw, id);
			return new String[] {pw, result.toString()};
		}
	}

	private String createNewPassword() { // 복잡한 인증 번호
	      char[] chars = new char[] {
	            '0','1','2','3','4','5','6','7','8','9',
	            'a','b','c','d','e','f','g','h','i','j',
	            'k','l','m','n','o','p','q','r','s','t',
	            'u','v','w','x','y','z'   };
	      
	      StringBuffer stringBuffer = new StringBuffer();
	      SecureRandom secureRandom = new SecureRandom();
	      secureRandom.setSeed(new Date().getTime());

	      int index = 0;
	      int length = chars.length;
	      for(int i = 0; i < 8; i++) {
	         index = secureRandom.nextInt(length);
	         
	         if(index % 2 == 0) {
	            stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
	         }else {
	            stringBuffer.append(String.valueOf(chars[index]).toLowerCase());
	         }
	      }
	      
	      System.out.println("newPASSWORD : " + stringBuffer.toString());
	      
	      return stringBuffer.toString();
	   }

}
