package com.mengma.petsystem.doAble;
import java.util.List;

import com.mengma.petsystem.entity.Account;
public interface Accountable {
	public abstract List<Account> getAccount(int storeId);
}
