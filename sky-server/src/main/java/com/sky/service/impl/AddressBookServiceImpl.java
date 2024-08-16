package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    @Override
    public List<AddressBook> list() {
        Long id = BaseContext.getCurrentId();
        return addressBookMapper.list(id);
    }

    @Override
    public void addAddress(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    @Override
    public void setDefault(AddressBook addressBook) {
        AddressBook addressBook1 = addressBookMapper.getById(addressBook.getId());
        BeanUtils.copyProperties(addressBook1, addressBook);
        addressBook.setIsDefault(1);
        AddressBook addressBookDefaulted = addressBookMapper.getByIsDefault(BaseContext.getCurrentId());
        if (addressBookDefaulted != null) {
            addressBookDefaulted.setIsDefault(0);
            addressBookMapper.update(addressBookDefaulted);
        }
        addressBookMapper.update(addressBook);
    }

    @Override
    public AddressBook getDefault() {
        return addressBookMapper.getByIsDefault(BaseContext.getCurrentId());
    }

    @Override
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    @Override
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
