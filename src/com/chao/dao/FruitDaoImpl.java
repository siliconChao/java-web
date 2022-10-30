package com.chao.dao;

import com.chao.entity.Fruit;

import java.util.List;

/**
 * @author Mrchao
 * @version 1.0.0
 * @date 2022-10-27
 */
public class FruitDaoImpl extends BaseDao<Fruit> implements FruitDao {

    @Override
    public Fruit getFruitById(int id) {
        String sql = "SELECT fid AS fId ,fname AS fName, fprice AS fPrice,fcount AS fCount,  remark FROM tbl_fruit where fid=?";
        Fruit fruit = queryForObject(sql, id);
        return fruit;
    }

    @Override
    public List<Fruit> getAllFruit(String keyWord,Integer pageNo,Integer pageNum) {
        String sql = "SELECT fid AS fId ,fname AS fName, fprice AS fPrice,fcount AS fCount, remark FROM tbl_fruit where fname like ? or remark like ? limit ?,?";
        return queryForList(sql,"%"+keyWord+"%","%"+keyWord+"%",(pageNo-1)*5,pageNum);
    }

    @Override
    public void deleteFruitById(int id) {
        String sql = "delete from tbl_fruit where fid = ?";
        update(sql, id);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update tbl_fruit set  fname = ? ,fprice=?, fcount = ?,remark = ? where fid = ?";
        update(sql,fruit.getfName(),fruit.getfPrice(),fruit.getfCount(),fruit.getRemark(),fruit.getfId());
    }

    /*@Override
    public void updatePriceById(int id, int price) {
        String sql = "update tbl_fruit set fprice=? where id = ? ";
        update(sql, price, id);
    }*/


    @Override
    public long getCount(String keyWord) {
        String sql = "select count(1) from tbl_fruit where fname like ? or remark like ?";
        return (long) getValue(sql,"%"+keyWord+"%","%"+keyWord+"%");
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "INSERT INTO tbl_fruit(fname,fprice,fcount,remark) VALUES(?,?,?,?)";
        update(sql,fruit.getfName(),fruit.getfPrice(),fruit.getfCount(),fruit.getRemark());
    }
}
