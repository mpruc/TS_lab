package org.example.lista1techsieciowe.controller;

import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsDto;
import org.example.lista1techsieciowe.controller.dto.bookDetails.BookDetailsResponseDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanResponseDto;
import org.example.lista1techsieciowe.controller.dto.loan.GetLoanResponseDto;
import org.example.lista1techsieciowe.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    @PreAuthorize("hasRole('LIBRARIAN')")
    public CreateLoanResponseDto addLoan(@RequestBody @Validated CreateLoanDto loan) {
        return loanService.addLoan(loan);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GetLoanResponseDto>> getALl(@RequestParam (required = false) Integer userId) {
        List<GetLoanResponseDto> dto = loanService.getAll(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLoanResponseDto> getLoan(@PathVariable Integer id) {
        GetLoanResponseDto dto = loanService.getLoan(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    public ResponseEntity<CreateLoanResponseDto> updateLoan(@PathVariable Integer id, @RequestBody @Validated CreateLoanDto updatedLoan) {
        CreateLoanResponseDto dto = loanService.updateLoan(id, updatedLoan);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
