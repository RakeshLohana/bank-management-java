package com.rakesh.banking_app.controller;

import java.util.List;
import java.util.Map;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakesh.banking_app.dto.AccountDto;
import com.rakesh.banking_app.entity.Account;
import com.rakesh.banking_app.service.AccountService;

import jakarta.persistence.Id;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	
	private AccountService accountService;
	
	
	
	
	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}




	@PostMapping("/")
	public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
		var account=accountService.createAccount(accountDto);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}
	

	@GetMapping("/")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		var accounts=accountService.getAllAccounts();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById( @PathVariable Long id){
		AccountDto accountDto=accountService.getAccountById(id);
		return  ResponseEntity.ok(accountDto);
	}
	
	
	@PostMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String,Double> request ){
		double value=request.get("amount");
		AccountDto accountDto= accountService.deposit(id, value);
		return ResponseEntity.ok(accountDto);
		
	}
	
	@PostMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String,Double> request ){
		double value=request.get("amount");
		AccountDto accountDto= accountService.withdraw(id, value);
		return ResponseEntity.ok(accountDto);
			
	}
	
}
