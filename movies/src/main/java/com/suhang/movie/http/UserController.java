package com.suhang.movie.http;

import static com.suhang.movie.util.CheckUtil.checkState;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suhang.movie.model.Resp;
import com.suhang.movie.model.RespCode;
import com.suhang.movie.model.User;
import com.suhang.movie.service.UserService;

/**
 * @author hang.su
 * @since 2017-04-25 下午9:34
 */
@Controller("userController")
@RequestMapping(value = "/user/")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Resp update(User user) {
        userService.update(user);
        return Resp.OK;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Resp delete(@RequestParam String username) {
        userService.delete(username);
        return Resp.OK;
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public Resp info(@RequestParam(name = "username") String username) {
        User user = userService.findByUsername(username);
        checkState(user != null, RespCode.USER_NOT_EXISTS);
        return Resp.ok(user);
    }
}
