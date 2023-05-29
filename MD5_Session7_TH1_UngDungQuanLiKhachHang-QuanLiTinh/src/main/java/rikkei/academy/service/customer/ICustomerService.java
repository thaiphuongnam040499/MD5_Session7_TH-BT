package rikkei.academy.service.customer;

import rikkei.academy.model.Customer;
import rikkei.academy.model.Province;
import rikkei.academy.service.IGenerateService;

public interface ICustomerService extends IGenerateService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}
