package org.example.lista1techsieciowe.service.loan;

import org.example.lista1techsieciowe.controller.dto.LoanDto;
import org.example.lista1techsieciowe.controller.dto.LoanResponseDto;
import org.example.lista1techsieciowe.entity.Loan;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.repository.LoanRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.loan.exceptions.LoanDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    public List<Loan> getAll() {

        return (List<Loan>) loanRepository.findAll();
    }
    public Loan getLoan(Integer id) {
        return loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
    }

    public LoanResponseDto addLoan(LoanDto loan) {
        User user = userRepository.findById(loan.getUser())
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(String.valueOf(loan.getUser())));

        Book book = bookRepository.findById(loan.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(loan.getBook()));

        var loanEntity = new Loan();

        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setBook(book);
        loanEntity.setUser(user);

        var newLoan = loanRepository.save(loanEntity);
        return new LoanResponseDto(newLoan.getLoanId(),
                newLoan.getBook().getBookId(),
                newLoan.getUser().getUserId(),
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate());
    }

    public void deleteLoan(Integer id) {
        if (!loanRepository.existsById(id)) {
            throw BookDoesntExistException.create(id);
        }
        loanRepository.deleteById(id);
    }
}
