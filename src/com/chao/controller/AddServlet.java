package com.chao.controller;

import com.chao.dao.FruitDao;
import com.chao.dao.FruitDaoImpl;
import com.chao.entity.Fruit;
import com.chao.mymvc.ViewBaseServlet;
import com.sun.org.apache.bcel.internal.generic.DCMPG;

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
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {
    FruitDao fruitDao = new FruitDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        //int fId = Integer.parseInt(request.getParameter("fId"));
        String fName = request.getParameter("fName");
        int fPrice = Integer.parseInt(request.getParameter("fPrice"));
        int fCount = Integer.parseInt(request.getParameter("fCount"));
        String remark = request.getParameter("remark");

        Fruit fruit = new Fruit();
        fruit.setfName(fName);
        fruit.setfPrice(fPrice);
        fruit.setfCount(fCount);
        fruit.setRemark(remark);
        fruitDao.addFruit(fruit);
        // 客户端重定向
        response.sendRedirect("index_fruit");
    }
}
