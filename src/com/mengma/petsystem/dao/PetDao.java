package com.mengma.petsystem.dao;

import java.util.List;

import com.mengma.petsystem.entity.Pet;

public interface PetDao {
	List<Pet> getAllPets();//查询所有宠物的id和姓名
//	void listAllPets();//列出库存宠物

	List<Pet> getAllPetsByOwnerId(int ownerid);

	List<Pet> getAllPetsByStoreId(int storeid);
}
