package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SimpleController implements Controller {
    private final UserDao userDao;
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user =userDao.findById(Integer.valueOf( request.getParameter("id")));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
      return modelAndView;
    }
    //data를 전달하고 어떤 view에 그릴거야를 전달하는 object
}