package com.mengma.petsystem.dao;

import java.util.List;

import com.mengma.petsystem.entity.PetStore;

public interface PetStoreDao {
	List<PetStore> getAllStore();//查询所有宠物商店信息
	void change(int money,int id);//更新商店余额
	PetStore findStoreById(int id);
	PetStore findStoreByName(String petStoreName);//通过姓名查询，用于登陆
}
