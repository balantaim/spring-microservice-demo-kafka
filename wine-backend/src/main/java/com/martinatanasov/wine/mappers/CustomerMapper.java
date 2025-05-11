package com.martinatanasov.wine.mappers;

import com.martinatanasov.parent.model.CustomerDTO;
import com.martinatanasov.wine.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);

}
