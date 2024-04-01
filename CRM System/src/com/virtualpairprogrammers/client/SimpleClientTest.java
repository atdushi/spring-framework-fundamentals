package com.virtualpairprogrammers.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.virtualpairprogrammers.domain.Customer;
import com.virtualpairprogrammers.services.calls.CallHandlingService;
import com.virtualpairprogrammers.services.customers.CustomerManagementService;
import com.virtualpairprogrammers.services.customers.CustomerNotFoundException;
import com.virtualpairprogrammers.services.diary.DiaryManagementService;

public class SimpleClientTest {

    public static void main(String[] args)
    {
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application-full.xml");

        try
        {
            CustomerManagementService customerService = container.getBean(CustomerManagementService.class);
            CallHandlingService callService = container.getBean(CallHandlingService.class);
            DiaryManagementService diaryService = container.getBean(DiaryManagementService.class);

            try
            {
                Customer oldCustomer = customerService.findCustomerById("CS03939");
                customerService.deleteCustomer(oldCustomer);
            }
            catch (CustomerNotFoundException e)
            {
                System.out.println("Sorry, that customer doesn't exisit");
            }
        }
        finally
        {
            container.close();
        }
    }

}
