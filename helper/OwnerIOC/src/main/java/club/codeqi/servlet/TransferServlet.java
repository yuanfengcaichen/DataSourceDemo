package club.codeqi.servlet;

import club.codeqi.Annotation.Autowire;
import club.codeqi.Annotation.Component;
import club.codeqi.Service.AccountService;
import club.codeqi.Service.impl.AccountServiceImpl;
import club.codeqi.factory.ProxyFactory;
import club.codeqi.factory.beanFactorybyAnno;
import club.codeqi.utils.JsonUtils;
import club.codeqi.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "transferServlet",urlPatterns = "/transferServlet")
public class TransferServlet extends HttpServlet {

    private static AccountService accountService = (AccountService) beanFactorybyAnno.getObject("accountService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("fromCardNo");
        String to = req.getParameter("toCardNo");
        Integer money = Integer.valueOf(req.getParameter("money"));
        Result result = new Result();
        try{
            int transfer = accountService.transfer(from, to, money);
            if(transfer==1){
                result.setStatus("200");
                result.setMessage("转账成功");
            }
        }
        catch (Exception e){
            try {
                throw e;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            result.setStatus("201");
            result.setMessage(e.toString());
        }
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtils.object2Json(result));
    }
}
