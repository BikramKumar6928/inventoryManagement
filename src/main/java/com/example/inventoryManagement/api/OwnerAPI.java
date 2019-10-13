package com.example.inventoryManagement.api;

import com.example.inventoryManagement.beans.OrderDetails;
import com.example.inventoryManagement.beans.Owner;
import com.example.inventoryManagement.repository.OwnerRepository;
import com.example.inventoryManagement.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class OwnerAPI {
    @Autowired
    private OwnerService ownerService;

    @RequestMapping(value = "/owner", method = RequestMethod.POST)
    public Owner create(@RequestBody Owner owner){
        return ownerService.createOwner(owner);
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET)
    public Owner get(@RequestParam String id){
        Owner owner = ownerService.getOwner(id);
        if(isNull(owner)){
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return owner;
    }

    @RequestMapping(value = "/owner/request", method = RequestMethod.POST)
    public void requestBuyStock(@RequestBody OrderDetails orderDetails){

    }

}
