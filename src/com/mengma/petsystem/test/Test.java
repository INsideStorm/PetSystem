package com.mengma.petsystem.test;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mengma.petsystem.dao.PetDao;
import com.mengma.petsystem.dao.PetOwnerDao;
import com.mengma.petsystem.dao.PetStoreDao;
import com.mengma.petsystem.dao.Impl.PetDaoSqlServerImpl;
import com.mengma.petsystem.dao.Impl.PetOwnerDaoSqlServerImpl;
import com.mengma.petsystem.dao.Impl.PetStoreDaoSqlServerImpl;
import com.mengma.petsystem.doAble.Service.Impl.PetOwnerServiceImpl;
import com.mengma.petsystem.doAble.Service.Impl.PetStoreServiceImpl;
import com.mengma.petsystem.entity.Pet;
import com.mengma.petsystem.entity.PetOwner;
import com.mengma.petsystem.entity.PetStore;

public class Test {
	public static void main(String[] args) {

		statPetShop();

	}

	private static void statPetShop() {
		// TODO Auto-generated method stub
		System.out.println("宠物管理系统启动，激活中...");
		System.out.println("************************************************");

		System.out.println("所有宠物主人被激活");
		System.out.println("************************************************");

		PetOwnerDao petOwnerDao = new PetOwnerDaoSqlServerImpl();

		List<PetOwner> listowner = new ArrayList<PetOwner>();
		listowner = petOwnerDao.getAllOwner();
		for (PetOwner petOwner : listowner) {
			System.out.print("主人的ID" + petOwner.getId());
			System.out.println("主人的名字" + petOwner.getName());
		}

		System.out.println("所有宠物商店被激活");
		System.out.println("************************************************");
		PetStoreDao petStoreDao = new PetStoreDaoSqlServerImpl();
		List<PetStore> liststore = new ArrayList<PetStore>();
		liststore = petStoreDao.getAllStore();
		for (PetStore petStore : liststore) {
			System.out.print("店名：" + petStore.getId());
			System.out.println("店名：" + petStore.getName());
		}

		System.out.println("所有宠物被激活");
		System.out.println("************************************************");
		PetDao petDao = new PetDaoSqlServerImpl();

		List<Pet> list = new ArrayList<Pet>();
		list = petDao.getAllPets();
		for (Pet pet : list) {
			System.out.print("宠物的ID" + pet.getId());
			System.out.println("宠物的名字" + pet.getName());
		}

		System.out.println("请选择输入登陆模式，输入1为宠物主人登陆，输入2为宠物商店登陆");
		Scanner input = new Scanner(System.in);
		int chose = input.nextInt();

		if (chose == 1) {
			System.out.println("宠物主人登陆");
			PetOwnerServiceImpl petOwnerServiceImpl = new PetOwnerServiceImpl();
			petOwnerServiceImpl.login();
		}
		if (chose == 2) {
			System.out.println("宠物商店登陆");
			PetStoreServiceImpl petStoreServiceImpl = new PetStoreServiceImpl();
			petStoreServiceImpl.login();
		}

	}
}
