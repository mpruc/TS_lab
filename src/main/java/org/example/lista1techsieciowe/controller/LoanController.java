package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.entity.Loan;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public @ResponseBody Loan addLoan(@RequestBody Loan loan){
        return loanService.addLoan(loan);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable <Loan> getAllLoans(){
        return loanService.getAll();
    }

    @GetMapping("/{id}")
    public Loan getLoan(@PathVariable int id) {
        return loanService.getLoan(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }
}
