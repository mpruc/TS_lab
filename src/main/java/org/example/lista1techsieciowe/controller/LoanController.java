package org.example.lista1techsieciowe.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * Controller handling operations related to loans.
 */
@RestController
@CrossOrigin
@RequestMapping("/loan")
@Tag(name = "Loan")

public class LoanController {
    private final LoanService loanService;

    /**
     * Constructor for LoanController.
     * @param loanService Service responsible for loan operations.
     */
    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Adds a new loan.
     * @param loan DTO representing the loan to be added.
     * @return Response containing the created loan.
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Loan added successfully" ),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Failed to add loan", content = @Content )
    })
    public CreateLoanResponseDto addLoan(@RequestBody @Validated CreateLoanDto loan) {
        return loanService.addLoan(loan);
    }

    /**
     * Retrieves all loans.
     * @param userId ID of the user (optional) to filter loans by.
     * @return ResponseEntity containing a list of loan DTOs.
     */
    @GetMapping("/getAll")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<List<GetLoanResponseDto>> getALl(@RequestParam (required = false) Integer userId) {
        List<GetLoanResponseDto> dto = loanService.getAll(userId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Retrieves details of a specific loan by ID.
     * @param id ID of the loan to retrieve.
     * @return ResponseEntity containing the loan details.
     */
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan found" ),
            @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<GetLoanResponseDto> getLoan(@PathVariable Integer id) {
        GetLoanResponseDto dto = loanService.getLoan(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /**
     * Deletes a loan by ID.
     * @param id ID of the loan to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully" ),
            @ApiResponse(responseCode = "404", description = "Delete failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public void delete(@PathVariable int id) {
        loanService.deleteLoan(id);
    }

    /**
     * Updates a loan with new information.
     * @param id ID of the loan to update.
     * @param updatedLoan DTO containing updated loan information.
     * @return ResponseEntity containing the updated loan response.
     */
    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan updated successfully" ),
            @ApiResponse(responseCode = "404", description = "Loan update failed", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not authorized", content = @Content)
    })
    public ResponseEntity<CreateLoanResponseDto> updateLoan(@PathVariable Integer id, @RequestBody @Validated CreateLoanDto updatedLoan) {
        CreateLoanResponseDto dto = loanService.updateLoan(id, updatedLoan);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
