package com.virtualpairprogrammers.services.customers;

import com.virtualpairprogrammers.domain.Call;
import com.virtualpairprogrammers.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerManagementServiceMockImpl implements CustomerManagementService {
    private HashMap<String, Customer> customerMap;

    public CustomerManagementServiceMockImpl() {
        customerMap = new HashMap<>();

        customerMap.put("CS03939", new Customer("CS03939", "Acme Ltd", "Some notes."));
        customerMap.put("CS03940", new Customer("CS03940", "VirtualPairProgrammers", "Some notes."));
        customerMap.put("CS03941", new Customer("CS03941", "Microsoft", "Some notes."));
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        customerMap.put(newCustomer.getCustomerId(), newCustomer);
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        customerMap.put(changedCustomer.getCustomerId(), changedCustomer);
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        customerMap.remove(oldCustomer.getCustomerId());
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        if (!customerMap.containsKey(customerId)) {
            throw new CustomerNotFoundException();
        }
        return customerMap.get(customerId);
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        List<Customer> results = new ArrayList<Customer>();

        for (Customer nextCustomer : customerMap.values())
        {
            if (nextCustomer.getCompanyName().equals(name))
            {
                results.add(nextCustomer);
            }
        }

        return results;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        return findCustomerById(customerId);
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        Customer customer = getFullCustomerDetail(customerId);
        customer.addCall(callDetails);
    }
}
