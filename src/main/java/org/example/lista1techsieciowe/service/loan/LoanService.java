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
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.LoginService;
import org.example.lista1techsieciowe.service.auth.OwnershipService;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.loan.exceptions.LoanDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, LoginRepository loginRepository) {
        super(loginRepository);
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @PreAuthorize("hasRole('LIBRARIAN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public List<GetLoanResponseDto> getAll(Integer userId) {
        List<Loan> loans;
        if(userId == null) {
            loans = loanRepository.findAll();
        } else {
            loans = loanRepository.findByUserId(userId);
        }
        return loans.stream().map(this :: mapLoan).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('LIBRARIAN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public GetLoanResponseDto getLoan(Integer id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
        return mapLoan(loan);
    }

    private GetLoanResponseDto mapLoan(Loan loan) {
        GetUserDto user = new GetUserDto(loan.getUser().getId(), loan.getUser().getName(), loan.getUser().getEmail());
        GetBookDto book = new GetBookDto(
                loan.getBook().getBookId(),
                loan.getBook().getIsbn(),
                loan.getBook().getPublisher(),
                loan.getBook().getAuthor(),
                loan.getBook().getTitle(),
                loan.getBook().getYearOfPublish(),
                loan.getBook().getAvailableCopies() > 0);
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
                newLoan.getUser().getId(),
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

    public CreateLoanResponseDto updateLoan(Integer id, CreateLoanDto updatedLoan) {
        Loan existingLoan = loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
        Book book = bookRepository.findById(updatedLoan.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(updatedLoan.getBook()));
        User user = userRepository.findById(updatedLoan.getUser())
                        .orElseThrow(()-> UserNotFoundException.create(updatedLoan.getUser()));
        existingLoan.setBook(book);
        existingLoan.setUser(user);
        existingLoan.setLoanDate(updatedLoan.getLoanDate());
        existingLoan.setDueDate(updatedLoan.getDueDate());
        existingLoan.setReturnDate(updatedLoan.getReturnDate());

        Loan savedLoan = loanRepository.save(existingLoan);
        return mapToCreateLoanResponseDto(savedLoan);
    }
    private CreateLoanResponseDto mapToCreateLoanResponseDto(Loan loan) {
        return new CreateLoanResponseDto(
                loan.getLoanId(),
                loan.getBook().getBookId(),
                loan.getUser().getId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate()
        );
    }

}
