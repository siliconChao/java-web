package com.chao.dao;

import com.chao.entity.Fruit;

import java.util.List;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-27
 */
public interface FruitDao {
    Fruit getFruitById(int id);
    List<Fruit> getAllFruit(String keyWord,Integer pageNo,Integer pageNum);
    void deleteFruitById(int id);
    void updateFruit(Fruit fruit);
    long getCount(String keyWord);
    void addFruit(Fruit fruit);
}