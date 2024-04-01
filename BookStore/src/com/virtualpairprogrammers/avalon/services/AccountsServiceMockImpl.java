package com.virtualpairprogrammers.avalon.services;

import com.virtualpairprogrammers.avalon.domain.Book;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
@Service
public class AccountsServiceMockImpl implements AccountsService
{	
	public void raiseInvoice(Book requiredBook) throws CustomerCreditExcededException
	{
		throw new CustomerCreditExcededException();
	}
}
