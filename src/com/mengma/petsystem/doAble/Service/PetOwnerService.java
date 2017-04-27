package com.mengma.petsystem.doAble.Service;

import com.mengma.petsystem.doAble.Buyable;
import com.mengma.petsystem.doAble.Sellable;
import com.mengma.petsystem.entity.PetOwner;

public interface PetOwnerService extends Buyable, Sellable {
	 
	public abstract PetOwner login();
}
 

