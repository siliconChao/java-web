package com.chao.controller;

import com.chao.dao.FruitDao;
import com.chao.dao.FruitDaoImpl;
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
@WebServlet("/del.do")
public class DelServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fidStr = request.getParameter("fid");
        if (!StringUtils.isEmpty(fidStr)) {
            fruitDao.deleteFruitById(Integer.parseInt(fidStr));
        }
        // 客户端重定向
        response.sendRedirect("index_fruit");
    }
}
