package member.board.intercepter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.board.dto.MemDto;

public class LoginIntercepter implements HandlerInterceptor {

	public List<String> loginEssential = Arrays.asList("/board/**"); // board의 모든 하위 파일 (,찍고 다른 파일도 추가할 수 있음)
	
	public List<String> loginInessential = Arrays.asList("/board/list/**","/board/content/**","/board/search/**"); // **은 글 번호가 바뀌어도 받을 수 있게

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemDto dto = (MemDto) request.getSession().getAttribute("user"); // user라는 이름의 세션 어트리뷰트 - 로그인 정보
	
		if (dto != null && dto.getId() != null) { // 요청 처리 순서 묻기
			return true;
		} else {
			response.sendRedirect("/main");
			return false;
		}
	}
	
	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView) // view한테 보낼 모델 데이터 고칠 수 있음
	 * throws Exception { // TODO Auto-generated method stub
	 * HandlerInterceptor.super.postHandle(request, response, handler,
	 * modelAndView); }
	 * 
	 * @Override public void afterCompletion(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, Exception ex) throws Exception
	 * { // TODO Auto-generated method stub
	 * HandlerInterceptor.super.afterCompletion(request, response, handler, ex); }
	 */
}
