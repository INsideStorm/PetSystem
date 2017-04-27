package com.mengma.petsystem.dao.Impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mengma.petsystem.dao.BaseDao;
import com.mengma.petsystem.dao.PetStoreDao;
import com.mengma.petsystem.entity.PetOwner;
import com.mengma.petsystem.entity.PetStore;

public class PetStoreDaoSqlServerImpl extends BaseDao implements PetStoreDao {

	@Override
	public List<PetStore> getAllStore() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetStore petStore = null;
		List<PetStore> list = new ArrayList<PetStore>();
		try {

			conn = this.getConnection();
			String sql = "select id,name from petstore";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				petStore = new PetStore();
				petStore.setId(rs.getInt(1));
				petStore.setName(rs.getString(2));
				list.add(petStore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public void change(int money, int id) {// 更新商铺的余额
		Connection conn = null;
		Statement stmt = null;
		int rs = 0;
		PetStore petStore = new PetStore();
		petStore = this.findStoreById(id);
		int balance = petStore.getBalance();
		if (money > 0) {
			balance = balance + money;
		} else {
			balance = balance - money;
		}

		String sql = "update petstore set balance='" + balance + "'where id='" + id + "'";// 更新余额
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeUpdate(sql);
			System.out.println("更新商店余额成功");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public PetStore findStoreById(int id) {// 通过id查找商店
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetStore petStore = null;

		try {
			conn = this.getConnection();
			String sql = "select * from petstore where id='" + id + "'";
			// 通过名字查询主人
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				petStore = new PetStore();
				petStore.setId(rs.getInt(1));
				petStore.setName(rs.getString(2));
				petStore.setPassword(rs.getString(3));
				petStore.setBalance(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return petStore;

	}

	@Override
	public PetStore findStoreByName(String petStoreName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetStore petStore = null;

		try {
			conn = this.getConnection();
			String sql = "select * from petstore where name='" + petStoreName + "'";
			// 通过名字查询主人
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				petStore = new PetStore();
				petStore.setId(rs.getInt(1));
				petStore.setName(rs.getString(2));
				petStore.setPassword(rs.getString(3));
				petStore.setBalance(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return petStore;

	}

}
