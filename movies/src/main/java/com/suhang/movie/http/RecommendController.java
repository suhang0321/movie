package com.suhang.movie.http;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suhang.movie.model.Resp;
import com.suhang.movie.service.RecommendService;
import com.suhang.movie.util.LoginUtil;

/**
 * @author hang.su
 * @since 2017-04-26 下午12:17
 */
@Controller
@RequestMapping(value = "/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Resp recommend() {
        Long userId = LoginUtil.getLoginUserId();
        return Resp.ok(recommendService.recommend(userId));
    }
}
