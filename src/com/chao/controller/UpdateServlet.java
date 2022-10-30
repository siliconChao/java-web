package com.chao.controller;

import com.chao.dao.FruitDao;
import com.chao.dao.FruitDaoImpl;
import com.chao.entity.Fruit;
import com.chao.mymvc.ViewBaseServlet;

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
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        int fId = Integer.parseInt(request.getParameter("fId"));
        String fName = request.getParameter("fName");
        int fPrice = Integer.parseInt(request.getParameter("fPrice"));
        int fCount = Integer.parseInt(request.getParameter("fCount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit(fId, fName, fPrice, fCount, remark);
        fruitDao.updateFruit(fruit);
        // 服务器内部转发
        // processTemplate("index", request, response);
        // 客户端重定向 重新给IndexServlet发数据 重新查询数据库
        response.sendRedirect("index_fruit");
    }
}