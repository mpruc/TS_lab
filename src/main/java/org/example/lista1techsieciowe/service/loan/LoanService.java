package org.example.lista1techsieciowe.service.loan;

import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanDto;
import org.example.lista1techsieciowe.controller.dto.loan.CreateLoanResponseDto;
import org.example.lista1techsieciowe.controller.dto.loan.GetLoanResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
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
import java.util.stream.Collectors;

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
    public List<GetLoanResponseDto> getAll() {
        List<Loan> loan = (List<Loan>) loanRepository.findAll();
        return loan.stream().map(this :: mapLoan).collect(Collectors.toList());
    }

    public GetLoanResponseDto getLoan(Integer id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
        return mapLoan(loan);
    }

    private GetLoanResponseDto mapLoan(Loan loan) {
        GetUserDto user = new GetUserDto(loan.getUser().getUserId(), loan.getUser().getName(), loan.getUser().getEmail());
        GetBookDto book = new GetBookDto(
                loan.getBook().getBookId(),
                loan.getBook().getIsbn(),
                loan.getBook().getPublisher(),
                loan.getBook().getAuthor(),
                loan.getBook().getTitle(),
                loan.getBook().getYearOfPublish(),
                loan.getBook().getAvailableCopies()>0);
        return new GetLoanResponseDto(loan.getLoanId(), book, user, loan.getLoanDate(), loan.getDueDate(), loan.getReturnDate());
    }

    public CreateLoanResponseDto addLoan(CreateLoanDto loan) {
        User user = userRepository.findById(loan.getUser())
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(String.valueOf(loan.getUser())));

        Book book = bookRepository.findById(loan.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(loan.getBook()));

        var loanEntity = new Loan();

        loanEntity.setLoanDate(loan.getLoanDate());
        loanEntity.setDueDate(loan.getDueDate());
        loanEntity.setBook(book);
        loanEntity.setUser(user);


        loanEntity.setReturnDate(loan.getReturnDate());

        var newLoan = loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanId(),
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
