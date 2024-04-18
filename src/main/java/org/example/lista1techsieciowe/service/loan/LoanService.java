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


/**
 * Service that provides operations related to loans.
 */
@Service
public class LoanService extends OwnershipService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    /**
     * Constructs a new LoanService with the provided dependencies.
     *
     * @param loanRepository  The repository for Loan entities.
     * @param bookRepository  The repository for Book entities.
     * @param userRepository  The repository for User entities.
     * @param loginRepository The repository for Login entities.
     */
    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, LoginRepository loginRepository) {
        super(loginRepository);
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Retrieves all loans, optionally filtered by user ID.
     *
     * @param userId The ID of the user to filter loans by. If null, retrieves all loans.
     * @return A list of GetLoanResponseDto containing loan details.
     */
    @PreAuthorize("hasRole('LIBRARIAN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public List<GetLoanResponseDto> getAll(Integer userId) {
        List<Loan> loans;
        if(userId == null) {
            loans = (List<Loan>) loanRepository.findAll();
        } else {
            loans = loanRepository.findByUserId(userId);
        }
        return loans.stream().map(this :: mapLoan).collect(Collectors.toList());
    }

    /**
     * Retrieves loan details by ID.
     *
     * @param id The ID of the loan to retrieve.
     * @return A GetLoanResponseDto containing loan details.
     * @throws LoanDoesntExistException If the loan with the specified ID is not found.
     */
    @PreAuthorize("hasRole('LIBRARIAN') or isAuthenticated() and this.isOwner(authentication.name, #userId)")
    public GetLoanResponseDto getLoan(Integer id) {
        Loan loan = loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
        return mapLoan(loan);
    }


    /**
     * Maps a Loan entity to a GetLoanResponseDto.
     *
     * @param loan The Loan entity to map.
     * @return A GetLoanResponseDto containing loan details.
     */
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

    /**
     * Adds a new loan.
     *
     * @param loan The DTO containing loan details.
     * @return A CreateLoanResponseDto containing information about the added loan.
     * @throws UserWithGivenLoginDoesntExistException If the specified user does not exist.
     * @throws BookDoesntExistException             If the specified book does not exist.
     */
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

        if (loanEntity.getReturnDate() == null) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }
        var newLoan = loanRepository.save(loanEntity);
        return new CreateLoanResponseDto(newLoan.getLoanId(),
                newLoan.getBook().getBookId(),
                newLoan.getUser().getId(),
                newLoan.getLoanDate(),
                newLoan.getDueDate(),
                newLoan.getReturnDate());
    }

    /**
     * Deletes a loan by ID.
     *
     * @param id The ID of the loan to delete.
     * @throws LoanDoesntExistException If the loan with the specified ID is not found.
     */
    public void deleteLoan(Integer id) {
        if (!loanRepository.existsById(id)) {
            throw LoanDoesntExistException.create(id);
        }
        loanRepository.deleteById(id);
    }

    /**
     * Updates a loan with the specified ID.
     *
     * @param id           The ID of the loan to update.
     * @param updatedLoan  The DTO containing updated loan details.
     * @return A CreateLoanResponseDto containing the updated loan details.
     * @throws LoanDoesntExistException      If the specified loan does not exist.
     * @throws BookDoesntExistException      If the specified book does not exist.
     * @throws UserNotFoundException        If the specified user does not exist.
     */

    public CreateLoanResponseDto updateLoan(Integer id, CreateLoanDto updatedLoan) {
        Loan existingLoan = loanRepository.findById(id).orElseThrow(() -> LoanDoesntExistException.create(id));
        Book book = bookRepository.findById(updatedLoan.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(updatedLoan.getBook()));
        User user = userRepository.findById(updatedLoan.getUser())
                        .orElseThrow(()-> UserNotFoundException.create(updatedLoan.getUser()));
        if (existingLoan.getReturnDate() == null && updatedLoan.getReturnDate() != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }

        existingLoan.setBook(book);
        existingLoan.setUser(user);
        existingLoan.setLoanDate(updatedLoan.getLoanDate());
        existingLoan.setDueDate(updatedLoan.getDueDate());
        existingLoan.setReturnDate(updatedLoan.getReturnDate());

        Loan savedLoan = loanRepository.save(existingLoan);
        return new CreateLoanResponseDto(
                savedLoan.getLoanId(),
                savedLoan.getBook().getBookId(),
                savedLoan.getUser().getId(),
                savedLoan.getLoanDate(),
                savedLoan.getDueDate(),
                savedLoan.getReturnDate()
        );
    }

}
