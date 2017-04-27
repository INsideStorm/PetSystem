package com.mengma.petsystem.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mengma.petsystem.dao.AccountDao;
import com.mengma.petsystem.dao.BaseDao;
import com.mengma.petsystem.entity.Account;

public class AccountDaoSqlServerImpl extends BaseDao implements AccountDao{

	@Override
	public List<Account> getAcount() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Account> list=null;
		String sql="select *from account";
		try {
			conn=this.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				Account account=new Account();
				account.setId(rs.getInt(1));
				account.setDeal_type(rs.getInt(2));
				account.setPet_id(rs.getInt(3));
				account.setSeller_id(rs.getInt(4));
				account.setBuyer_id(rs.getInt(5));
				account.setPrice(rs.getInt(6));
				account.setDeal_time(rs.getDate(7));
				list.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
	}

	@Override
	public void AddAccount(Account account) {
//		String sql="insert pet(masterid,name,typeid)values(?,?,?)";
//		Object[] param={pet.getMasterId(),pet.getName(),pet.getTypeId()};
//		return exceuteUpdate(sql, param);
		String sql="insert into account values(?,?,?,?,?,?)";
		Object[] param={account.getDeal_type(),account.getPet_id(),account.getSeller_id(),account.getBuyer_id(),account.getPrice(),account.getDeal_time()};
		int result=exceuteUpdate(sql, param);   
		if(result!=0){
			System.out.println("加账单成功");
		}
	}

}
