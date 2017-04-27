package com.mengma.petsystem.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mengma.petsystem.dao.BaseDao;
import com.mengma.petsystem.dao.PetDao;
import com.mengma.petsystem.entity.Pet;

public class PetDaoSqlServerImpl extends BaseDao implements PetDao {

	@Override
	public List<Pet> getAllPets() {
		Connection conn = null;
		Statement pstmt = null;
		ResultSet rs = null;
		Pet pet = null;
		List<Pet> list = new ArrayList<Pet>();
		try {
			conn = this.getConnection();
			String sql = "select *from pet";
			pstmt = conn.createStatement();
			rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				pet = new Pet();
				pet.setId(rs.getInt(1));
				pet.setName(rs.getString(2));
				pet.setTypename(rs.getString(3));
				pet.setHealth(rs.getInt(4));
				pet.setLove(rs.getInt(5));
				pet.setBirthday(rs.getDate(6));
				pet.setOwner_id(rs.getInt(7));
				pet.setStore_id(rs.getInt(8));
				pet.setPrice(rs.getInt(9));
				list.add(pet);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Pet> getAllPetsByOwnerId(int ownerid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Pet pet = null;
		String sql = "select *from pet where owner_id='" + ownerid + "'";
		List<Pet> list = new ArrayList<Pet>();
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pet = new Pet();
				pet.setId(rs.getInt(1));
				pet.setName(rs.getString(2));
				pet.setTypename(rs.getString(3));
				pet.setHealth(rs.getInt(4));
				pet.setLove(rs.getInt(5));
				pet.setBirthday(rs.getDate(6));
				pet.setOwner_id(rs.getInt(7));
				pet.setStore_id(rs.getInt(8));
				pet.setPrice(rs.getInt(9));
				list.add(pet);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Pet> getAllPetsByStoreId(int storeid) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Pet pet = null;
		String sql = "select *from pet where store_id='" + storeid + "'";
		List<Pet> list = new ArrayList<Pet>();
		try {
			conn = this.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pet = new Pet();
				pet.setId(rs.getInt(1));
				pet.setName(rs.getString(2));
				pet.setTypename(rs.getString(3));
				pet.setHealth(rs.getInt(4));
				pet.setLove(rs.getInt(5));
				pet.setBirthday(rs.getDate(6));
				pet.setOwner_id(rs.getInt(7));
				pet.setStore_id(rs.getInt(8));
				pet.setPrice(rs.getInt(9));
				list.add(pet);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

}
