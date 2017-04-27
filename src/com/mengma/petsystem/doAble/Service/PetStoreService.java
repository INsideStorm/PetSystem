package com.mengma.petsystem.doAble.Service;

import java.util.List;

import com.mengma.petsystem.doAble.Accountable;
import com.mengma.petsystem.doAble.Breedable;
import com.mengma.petsystem.doAble.Buyable;
import com.mengma.petsystem.doAble.Sellable;
import com.mengma.petsystem.entity.Pet;
import com.mengma.petsystem.entity.PetOwner;
import com.mengma.petsystem.entity.PetStore;

public interface PetStoreService extends Buyable, Sellable, Accountable, Breedable{

	public abstract List<Pet> getPetsInStock(int storeId);

	public abstract List<Pet> getPetBreed(int storeId);

	public abstract void charge(Pet pet);

	public abstract PetStore login();
}
