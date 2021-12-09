package kr.pe.loginapi.controller;

import kr.pe.loginapi.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    private final PostsService postsService;
//    private final HttpSession httpSession;
//
//    // Model 객체
//    // 서버 템플릿 엔진에서 사용 가능한 객체를 저장할 수 있다.
//    // 해당 메소드에서는 postsService.findAllDesc()로 가져온 결과를 posts라는 이름으로 index.mustache에  전달
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAllAttributes("posts", postsService.findAllDesc());
//
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
//
//        // 세션에 저장된 값이 있을 경우만 model에 userName으로 등록
//        if(user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//        return "index";
//    }
}
