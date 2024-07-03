package com.rakesh.banking_app.service;

import java.util.List;

import com.rakesh.banking_app.dto.AccountDto;
import com.rakesh.banking_app.entity.Account;

public interface AccountService {
	
	
	  AccountDto createAccount(AccountDto accountDto);
	  
	  List<AccountDto> getAllAccounts();
	  
	  AccountDto getAccountById(Long id);
	  
	  AccountDto deposit(Long id, double deposit);
	  
	  AccountDto withdraw(Long id, double withdraw);



}
