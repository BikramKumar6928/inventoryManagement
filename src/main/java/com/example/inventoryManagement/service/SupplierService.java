package com.example.inventoryManagement.service;

import com.example.inventoryManagement.beans.OrderDetails;
import com.example.inventoryManagement.beans.Owner;
import com.example.inventoryManagement.beans.ProductCount;
import com.example.inventoryManagement.beans.Supplier;
import com.example.inventoryManagement.repository.OwnerRepository;
import com.example.inventoryManagement.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SupplierService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private SupplierRepository supplierRepository;


    public void supplyStock(OrderDetails orderDetails){
        Supplier supplier = orderDetails.getSupplier();
        Owner owner = orderDetails.getOwner();
        List<ProductCount> itemList = orderDetails.getProducts();
        owner.addStocks(itemList);
        supplier.removeStocks(itemList);

        synchronized (ownerRepository){
            ownerRepository.save(owner);
        }
        synchronized (supplierRepository){
            supplierRepository.save(supplier);
        }
    }

//    public void receivePayment(OrderDetails orderDetails){
//
//    }


}
