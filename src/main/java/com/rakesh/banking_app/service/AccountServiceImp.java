package com.rakesh.banking_app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.ListModel;

import org.springframework.stereotype.Service;

import com.rakesh.banking_app.dto.AccountDto;
import com.rakesh.banking_app.entity.Account;
import com.rakesh.banking_app.mapper.AccountMapper;
import com.rakesh.banking_app.repository.AccountRepository;


@Service
public class AccountServiceImp implements AccountService {
	
	
	public AccountRepository accountRepository;
	
	

	public AccountServiceImp(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}




	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account= AccountMapper.mapToAccount(accountDto);
		Account accountSaved=accountRepository.save(account);
		return AccountMapper.mapToAccountDto(accountSaved);
	}
	
	
	public List<AccountDto> getAllAccounts(){
		List<Account> list=  accountRepository.findAll();
		System.out.println(list);
		      return list.stream()
		  			.map(AccountMapper::mapToAccountDto)
					.collect(Collectors.toList());
		    		  
		
	}




	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository.findById(id)
				.orElseThrow(() ->new RuntimeException("Account does not exist") );
		return AccountMapper.mapToAccountDto(account);
	}




	@Override
	public AccountDto deposit(Long id, double deposit) {
	Account account=accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Account does not exist") );;
		double total= account.getBalance()+deposit;
		 account.setBalance(total);
		 accountRepository.save(account);
	return AccountMapper.mapToAccountDto(account);
	}
	
	
	@Override
	public AccountDto withdraw(Long id, double withdraw) {
	Account account=accountRepository.findById(id).orElseThrow(() ->new RuntimeException("Account does not exist") );;
	
	if(account.getBalance()>=withdraw) {
		double total= account.getBalance()-withdraw;
		 account.setBalance(total);
		 accountRepository.save(account);
	}else {
		throw new RuntimeException("Balance is not sufficient");
	}

	return AccountMapper.mapToAccountDto(account);
	}
	

}
