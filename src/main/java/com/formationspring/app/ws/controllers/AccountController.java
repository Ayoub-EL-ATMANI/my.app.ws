package com.formationspring.app.ws.controllers;


public class AccountController {
		
//		@Autowired
//		private AccountRepository repository;
//		
//		@GetMapping(value = "/account/customer/{customer}")
//		public Flux<Account> findByCustomer(@PathVariable("customer") Integer customerId) {
//		    return repository.findByCustomerId(customerId)
//		            .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//		}
//		
//		@GetMapping(value = "/account")
//		public Flux<Account> findAll() {
//		    return repository.findAll().map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//		}
//		
//		@GetMapping(value = "/account/{id}")
//		public Mono<Account> findById(@PathVariable("id") Integer id) {
//		    return repository.findById(id)
//		            .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//		}
//		
//		@PostMapping("/person")
//		public Mono<Account> create(@RequestBody Publisher<Account> accountStream) {
//		    return repository
//		            .save(Mono.from(accountStream)
//		                    .map(a -> new pl.piomin.services.account.model.Account(a.getNumber(), a.getCustomerId(),
//		                            a.getAmount())))
//		            .map(a -> new Account(a.getId(), a.getCustomerId(), a.getNumber(), a.getAmount()));
//		}

}