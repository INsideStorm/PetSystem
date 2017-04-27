package com.mengma.petsystem.doAble.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mengma.petsystem.dao.AccountDao;
import com.mengma.petsystem.dao.PetDao;
import com.mengma.petsystem.dao.PetOwnerDao;
import com.mengma.petsystem.dao.PetStoreDao;
import com.mengma.petsystem.dao.Impl.AccountDaoSqlServerImpl;
import com.mengma.petsystem.dao.Impl.PetDaoSqlServerImpl;
import com.mengma.petsystem.dao.Impl.PetOwnerDaoSqlServerImpl;
import com.mengma.petsystem.dao.Impl.PetStoreDaoSqlServerImpl;
import com.mengma.petsystem.doAble.Service.PetOwnerService;
import com.mengma.petsystem.entity.Account;
import com.mengma.petsystem.entity.Pet;
import com.mengma.petsystem.entity.PetOwner;

public class PetOwnerServiceImpl implements PetOwnerService {
	PetOwner petOwner = new PetOwner();

	@Override
	public void buy(Pet pet) {
		System.out.println("1、购买宠物：");
		System.out.println("------请输入选择要购买范围：只输入选择项的序号------");
		System.out.println("1、购买库存宠物");
		System.out.println("2、购买新培育宠物");
		System.out.println("----------------以下是库存宠物----------------");
		System.out.println("序号\t" + "宠物名称\t" + "类型\t" + "元宝数");
		PetDao petDao = new PetDaoSqlServerImpl();

		List<Pet> list = new ArrayList<Pet>();
		list = petDao.getAllPets();
		for (Pet pet2 : list) {
			System.out.print(pet2.getId() + "\t");
			System.out.print(pet2.getName() + "\t");
			System.out.print(pet2.getTypename() + "\t");
			System.out.println(pet2.getPrice());
		}
		System.out.println("------请选择要购买哪一个宠物，并输入选择项的序号-------");
		Scanner input = new Scanner(System.in);
		System.out.println(list.size());
		int chose = input.nextInt();
		if (chose > list.size()) {
			// 重新选
		} else {// 宠物入账
			Pet petAdd = new Pet();
//			petOwner.getName();// 获取当前主人id
			Date date = new Date();
			petAdd = list.get(chose - 1);
			// **********************************
			Account account = new Account();
//			account.setId(petAdd.getId());//这条是错误代码，会导致主键插入重复错误，是个人犯的逻辑错误
			account.setDeal_type(1);
			account.setPet_id(petAdd.getId());
			account.setSeller_id(petAdd.getStore_id());

			account.setBuyer_id(petOwner.getId());
			account.setPrice(petAdd.getPrice());
			account.setDeal_time(date);
			// ********************************
			AccountDao accountDao = new AccountDaoSqlServerImpl();
			accountDao.AddAccount(account);
			PetOwnerDao petOwnerDao = new PetOwnerDaoSqlServerImpl();
			petOwnerDao.changeMoney(-petAdd.getPrice(), petOwner.getId());// 更新主人的余额
			PetStoreDao petStoreDao = new PetStoreDaoSqlServerImpl();
			petStoreDao.change(petAdd.getPrice(), petAdd.getStore_id());// 更新商店的余额

		}
		// chose是选择的动物的id
		// 之后要写入账的函数

	}

	@Override
	public void sell(Pet pet) {
		System.out.println("2、卖出宠物");
		System.out.println("----------------以下是你拥有的宠物----------------");
		System.out.println("序号\t" + "宠物名称\t" + "类型\t" + "元宝数");
		PetDao petDao = new PetDaoSqlServerImpl();

		List<Pet> list = new ArrayList<Pet>();
//		list = petDao.getAllPets();
		list=petDao.getAllPetsByOwnerId(petOwner.getId());
		for (Pet pet2 : list) {
			System.out.print(pet2.getId() + "\t");
			System.out.print(pet2.getName() + "\t");
			System.out.print(pet2.getTypename() + "\t");
			System.out.println(pet2.getPrice());
		}
		System.out.println("------请选择要卖哪一个宠物，并输入选择项的序号-------");
		Scanner input = new Scanner(System.in);
		System.out.println(list.size());
		int chose = input.nextInt();
		if (chose > list.size()) {
			// 重新选,容错处理
		} else {// 宠物入账
			Pet petAdd = new Pet();
			//			petOwner.getName();// 获取当前主人id
			Date date = new Date();//获取系统当前的时间
			petAdd = list.get(chose - 1);
			// **********************************
			Account account = new Account();
			account.setDeal_type(2);
			account.setPet_id(petAdd.getId());
			account.setSeller_id(petAdd.getStore_id());
			account.setBuyer_id(petOwner.getId());
			account.setPrice(petAdd.getPrice());
			account.setDeal_time(date);
			// ********************************
			AccountDao accountDao = new AccountDaoSqlServerImpl();
			accountDao.AddAccount(account);
			PetOwnerDao petOwnerDao = new PetOwnerDaoSqlServerImpl();
			petOwnerDao.changeMoney(petAdd.getPrice(), petOwner.getId());// 更新主人的余额
			PetStoreDao petStoreDao = new PetStoreDaoSqlServerImpl();
			petStoreDao.change(-petAdd.getPrice(), petAdd.getStore_id());// 更新商店的余额

		}
		// chose是选择的动物的id
		// 之后要写入账的函数

	}

	@Override
	public PetOwner login() {
		System.out.println("主人登陆");
		String name = null;// 用户名
		String password = null;// 用户密码
		System.out.println("请先登陆，请您输入主人的名字：");
		Scanner input = new Scanner(System.in);
		name = input.next();
		System.out.println("请输入主人的密码：");

		password = input.next();

		PetOwnerDao petOwnerDao = new PetOwnerDaoSqlServerImpl();

		petOwner = petOwnerDao.findOwner(name);
		// System.out.println(petOwner.getName());
		// System.out.println(petOwner.getPassword());
		if (password.equals(petOwner.getPassword().trim())) {
			System.out.println("------恭喜您成功登陆------");
			System.out.println("------您的基本信息！------");
			System.out.println("名字:" + petOwner.getName());
			System.out.println("元宝数:" + petOwner.getMoney());
			System.out.println("登陆成功，您可以购买和卖出宠物，如果您想购买宠物请输入1，如果想卖出宠物请输入2");
			int chose = input.nextInt();
			if (chose == 1) {
				Pet pet = new Pet();
				buy(pet);
			}
			if (chose == 2) {
				Pet pet = new Pet();
				sell(pet);
			}
		}

		return petOwner;
	}

}
