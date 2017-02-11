package controller;

import org.json.simple.JSONObject;
import org.springframework.ui.Model;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by raychen on 16/5/14.
 */
public abstract class BaseController {

    /**
     * 对应显示的view,以及处理表单请求
     *
     * @param response
     * @param request
     * @param model
     * @return
     */
    public abstract String toView(HttpServletResponse response, HttpServletRequest request, Model model);

    /**
     * 处理ajax请求
     *
     * @param response
     * @param request
     * @return
     */
    public abstract JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request);

    /**
     * 验证请求数据
     *
     * @param request
     * @return
     */
    public abstract ResultMessage validate(HttpServletRequest request);
}
