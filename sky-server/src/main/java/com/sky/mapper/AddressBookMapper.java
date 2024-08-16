package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    @Select("select * from address_book where user_id = #{userId}")
    List<AddressBook> list(Long userId);

    @Insert("insert into address_book (user_id, consignee, sex, phone, province_code, " +
            "province_name, city_code, city_name, district_code, district_name, detail, label, is_default)" +
            "values (#{userId}, #{consignee}, #{sex}, #{phone}, #{provinceCode}, " +
            "#{provinceName}, #{cityCode}, #{cityName}, #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void insert(AddressBook addressBook);

    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);

    @Select("select * from address_book where is_default = 1 and user_id = #{userId}")
    AddressBook getByIsDefault(Long userId);

    void update(AddressBook addressBook);

    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}
