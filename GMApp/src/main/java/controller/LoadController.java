package controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by raychen on 16/5/27.
 */
@Controller
@RequestMapping("/")
public class LoadController extends BaseController {

    @Override
    @RequestMapping("/load")
    public String toView(HttpServletResponse response, HttpServletRequest request, Model model) {
//        model.addAttribute("data","cr");
        return "load";
    }

    @Override
    @ResponseBody
    @RequestMapping("/load-ajax")
    public JSONObject dealAjax(HttpServletResponse response, HttpServletRequest request) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = request.getParameter("var");
        JSONObject obj = new JSONObject();
        obj.put("msg", s);
        return obj;
    }

    @Override
    public ResultMessage validate(HttpServletRequest request) {
        return null;
    }
}
