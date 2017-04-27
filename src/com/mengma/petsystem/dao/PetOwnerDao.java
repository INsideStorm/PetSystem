package com.mengma.petsystem.dao;

import java.security.acl.Owner;
import java.util.List;

import com.mengma.petsystem.entity.PetOwner;

public interface PetOwnerDao {
	List<PetOwner> getAllOwner();//查询所有宠物主人信息
	PetOwner findOwner(String name);//查询单个主人，用于登陆
	PetOwner findOwnerById(int id);//查询单个主人，用于更新金钱时查找
	void changeMoney(int money,int id);//通过id来更改金钱
}
