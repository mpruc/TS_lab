package org.example.lista1techsieciowe.service;

import org.example.lista1techsieciowe.entity.Loan;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.LoanRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    public List<Loan> getAll() {

        return (List<Loan>) loanRepository.findAll();
    }
    public Loan getLoan(int id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }
    public Loan addLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    public void deleteLoan(int id) {
        loanRepository.deleteById(id);
    }



}
