package com.suhang.movie.http;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suhang.movie.exception.ServiceException;
import com.suhang.movie.model.Resp;
import com.suhang.movie.model.RespCode;
import com.suhang.movie.model.User;
import com.suhang.movie.service.PassportService;
import com.suhang.movie.service.UserService;

/**
 * @author hang.su
 * @since 2017-04-25 下午8:14
 */
@Controller("passportController")
@RequestMapping(value = "/")
public class PassportController {

    @Resource
    private UserService userService;

    @Resource
    private PassportService passportService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Resp register(User user) {
        userService.create(user);
        return Resp.OK;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public Resp login() {
        throw new ServiceException(RespCode.UNAUTHORIZED, "method not supported, please ticket via post");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Resp login(@RequestParam String username, @RequestParam String password, @RequestParam(name = "remember_me", required = false, defaultValue = "false") boolean rememberMe) {
        passportService.login(username, password, rememberMe);
        return Resp.OK;
    }

    @RequestMapping(value = "unauthorized", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Resp unauthorized() {
        return Resp.UNAUTHORIZED;
    }

    @RequestMapping(value = "unauthenticated", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Resp unauthenticated() {
        return Resp.UNAUTHENTICATED;
    }

    @RequestMapping(value = "ok", method = RequestMethod.GET)
    @ResponseBody
    public Resp ok() {
        return Resp.OK;
    }
}
