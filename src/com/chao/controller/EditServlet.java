package com.chao.controller;

import com.chao.dao.FruitDao;
import com.chao.dao.FruitDaoImpl;
import com.chao.entity.Fruit;
import com.chao.mymvc.ViewBaseServlet;
import com.chao.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-29
 */
@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FruitDao fruitDao  = new FruitDaoImpl();
        // 获取请求参数
        String fidStr = req.getParameter("fid");
        Fruit fruit= null;
        if (!StringUtils.isEmpty(fidStr))
         fruit = fruitDao.getFruitById(Integer.parseInt(fidStr));
        // 添加到session域
        req.setAttribute("fruit",fruit);
        processTemplate("edit",req,resp);
    }
}
