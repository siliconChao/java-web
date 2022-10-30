package com.chao.controller;

import com.chao.dao.FruitDao;
import com.chao.dao.FruitDaoImpl;
import com.chao.entity.Fruit;
import com.chao.mymvc.ViewBaseServlet;
import com.chao.utils.StringUtils;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-28
 */

/**
 * Servlet从3.0开始支持注解的方式
 */
@WebServlet("/index_fruit")
public class IndexServlet extends ViewBaseServlet {
    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        showIndexPage(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request,response);
    }

    private void showIndexPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        // 当前页号
        Integer pageNo = 1;
        String operation = request.getParameter("operation");
        String keyWord = null;
        // 不是空 就是带了隐藏参数 那就是点击模糊查询按钮过来的
        if (!StringUtils.isEmpty(operation) && "search".equalsIgnoreCase(operation)){
            // 重置一下页号
            pageNo = 1;
            // 获取到查询关键字
            keyWord = request.getParameter("keyWord");
            if (StringUtils.isEmpty(keyWord)){
                keyWord="";
            }
            // 把关键字保存在 session保存域
            session.setAttribute("keyWord",keyWord);
        }else {
            String pageNoStr = request.getParameter("pageNo");
            if (!StringUtils.isEmpty(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            // 去获取一下输入框中的关键字
            Object keyWordObj = session.getAttribute("keyWord");
            if (keyWordObj != null){
                keyWord = (String) keyWordObj;
            }else {
                keyWord = "";
            }
        }
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("keyWord",keyWord);
        long count = fruitDao.getCount(keyWord);
        long pageCount = (count + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);
        int perPageCount = 5;

        List<Fruit> fruits  = fruitDao.getAllFruit(keyWord,pageNo,perPageCount);
        session.setAttribute("fruits", fruits);
        // 此处的视图名称是index
        // 那么Thymeleaf会将这个视图名称 对应到物理视图 名称上去
        // 逻辑视图名称 : index
        // 物理视图名称 : view-prefix + 逻辑视图名称 + view-suffix
        // 所以真实的视图名称是 /index
        processTemplate("index", request, response);
    }

}
