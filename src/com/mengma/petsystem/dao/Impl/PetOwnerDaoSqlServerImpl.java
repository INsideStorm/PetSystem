package com.mengma.petsystem.dao.Impl;

import java.security.acl.Owner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mengma.petsystem.dao.BaseDao;
import com.mengma.petsystem.dao.PetOwnerDao;
import com.mengma.petsystem.entity.PetOwner;

public class PetOwnerDaoSqlServerImpl extends BaseDao implements PetOwnerDao {

	@Override
	public List<PetOwner> getAllOwner() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetOwner petOwner = null;
		List<PetOwner> list = new ArrayList<PetOwner>();
		try {

			conn = this.getConnection();
			String sql = "select id,name from petowner";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				petOwner = new PetOwner();
				petOwner.setId(rs.getInt(1));
				petOwner.setName(rs.getString(2));
				list.add(petOwner);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public PetOwner findOwner(String name) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetOwner petOwner = null;

		try {
			conn = this.getConnection();
			String sql = "select * from petowner where name='" + name + "'";
			// 通过名字查询主人
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				petOwner = new PetOwner();
				petOwner.setId(rs.getInt(1));
				petOwner.setName(rs.getString(2));
				petOwner.setPassword(rs.getString(3));
				petOwner.setMoney(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		} 
//		finally {
//			this.closeAll(conn, stmt, rs);
//		}

		return petOwner;
	}

	@Override
	public void changeMoney(int money,int id ){
		Connection conn = null;
		Statement stmt = null;
		int rs=0;
		PetOwner petOwner = new PetOwner();//创建owner类
		
		petOwner=this.findOwnerById(id);//获取
		int balance= petOwner.getMoney();//获取该主人当前的余额
		if(money>0){
			balance=balance+money;//更新余额
		}
		else {
			balance=balance-money;
		}
		
		String sql="update petowner set money='"+balance+"'where id='"+id+"'";//更新余额
		try {
			conn=this.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeUpdate(sql);
			System.out.println("更新余额成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public PetOwner findOwnerById(int id) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PetOwner petOwner = null;

		try {
			conn = this.getConnection();
			String sql = "select * from petowner where id='" + id + "'";
			// 通过名字查询主人
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				petOwner = new PetOwner();
				petOwner.setId(rs.getInt(1));
				petOwner.setName(rs.getString(2));
				petOwner.setPassword(rs.getString(3));
				petOwner.setMoney(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			this.closeAll(conn, stmt, rs);
//		}

		return petOwner;
	}

}
