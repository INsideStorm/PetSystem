package com.mengma.petsystem.dao;

import java.util.List;

import com.mengma.petsystem.entity.Account;

public interface AccountDao {
	List<Account> getAcount();
	void AddAccount(Account account);
}
