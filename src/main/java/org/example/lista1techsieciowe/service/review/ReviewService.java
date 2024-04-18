package org.example.lista1techsieciowe.service.review;

import org.example.lista1techsieciowe.commonTypes.UserRole;
import org.example.lista1techsieciowe.controller.dto.book.GetBookDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewDto;
import org.example.lista1techsieciowe.controller.dto.review.ReviewResponseDto;
import org.example.lista1techsieciowe.controller.dto.user.GetUserDto;
import org.example.lista1techsieciowe.entity.Book;
import org.example.lista1techsieciowe.entity.Review;
import org.example.lista1techsieciowe.entity.User;
import org.example.lista1techsieciowe.repository.BookRepository;
import org.example.lista1techsieciowe.repository.LoginRepository;
import org.example.lista1techsieciowe.repository.ReviewRepository;
import org.example.lista1techsieciowe.repository.UserRepository;
import org.example.lista1techsieciowe.service.auth.OwnershipService;
import org.example.lista1techsieciowe.service.auth.exceptions.UnauthorizedException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserNotFoundException;
import org.example.lista1techsieciowe.service.auth.exceptions.UserWithGivenLoginDoesntExistException;
import org.example.lista1techsieciowe.service.book.exceptions.BookDoesntExistException;
import org.example.lista1techsieciowe.service.review.exceptions.ReviewDoesntExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service that provides operations related to reviews.
 */
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoginRepository loginRepository;

    /**
     * Constructs a ReviewService instance.
     *
     * @param reviewRepository The repository for review entities.
     * @param userRepository   The repository for user entities.
     * @param bookRepository   The repository for book entities.
     * @param loginRepository  The repository for login entities.
     */
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, BookRepository bookRepository, LoginRepository loginRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.loginRepository = loginRepository;
    }

    /**
     * Retrieves all reviews.
     *
     * @return A list of ReviewResponseDto objects representing all reviews.
     */
    public List<ReviewResponseDto> getAll() {
        List<Review> review = (List<Review>) reviewRepository.findAll();
        return review.stream().map(this::mapReview).collect(Collectors.toList());

    }
    /**
     * Retrieves a review by its ID.
     *
     * @param id The ID of the review to retrieve.
     * @return The ReviewResponseDto object representing the retrieved review.
     * @throws ReviewDoesntExistException If the review with the given ID does not exist.
     */
    public ReviewResponseDto getReview(Integer id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> ReviewDoesntExistException.create(id));
        return mapReview(review);
    }

    /**
     * Maps a Review entity to a ReviewResponseDto object.
     *
     * @param review The Review entity to map.
     * @return The mapped ReviewResponseDto object.
     */
    private ReviewResponseDto mapReview(Review review) {
        GetUserDto user = new GetUserDto(
                review.getUser().getId(),
                review.getUser().getName(),
                review.getUser().getEmail());

        GetBookDto book = new GetBookDto(
                review.getBook().getBookId(),
                review.getBook().getIsbn(),
                review.getBook().getPublisher(),
                review.getBook().getAuthor(),
                review.getBook().getTitle(),
                review.getBook().getYearOfPublish(),
                review.getBook().getAvailableCopies() > 0);

        return new ReviewResponseDto(review.getReviewId(), book, user, review.getGrade(), review.getComment(), review.getReviewDate());
    }

    /**
     * Adds a new review.
     *
     * @param review The DTO containing review details.
     * @return A ReviewResponseDto containing the added review details.
     * @throws UserWithGivenLoginDoesntExistException  If the specified user does not exist.
     * @throws BookDoesntExistException               If the specified book does not exist.
     */
    public ReviewResponseDto addReview(ReviewDto review) {
        User user = userRepository.findById(review.getUser())
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(String.valueOf(review.getUser())));

        Book book = bookRepository.findById(review.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(review.getBook()));

        var reviewEntity = new Review();
        reviewEntity.setReviewDate(review.getReviewDate());
        reviewEntity.setUser(user);
        reviewEntity.setBook(book);
        reviewEntity.setComment(review.getComment());
        reviewEntity.setGrade(review.getGrade());

        var newReview = reviewRepository.save(reviewEntity);

        GetBookDto getBookDto = new GetBookDto(
                book.getBookId(),
                book.getIsbn(),
                book.getPublisher(),
                book.getAuthor(),
                book.getTitle(),
                book.getYearOfPublish(),
                book.getAvailableCopies() > 0);

        GetUserDto getUserDto = new GetUserDto(
                user.getId(),
                user.getName(),
                user.getEmail());

        return new ReviewResponseDto(newReview.getReviewId(), getBookDto, getUserDto, newReview.getGrade(), newReview.getComment(), newReview.getReviewDate());
    }

    /**
    * Deletes a review.
    *
    * @param id The ID of the review to delete.
    * @throws ReviewDoesntExistException If the review with the given ID does not exist.
    * @throws UnauthorizedException      If the user is not authorized to delete the review.
    */
    public void deleteReview(Integer id) {
        reviewRepository.findById(id)
                .orElseThrow(() -> ReviewDoesntExistException.create(id));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        var login = loginRepository.findByUsername(username)
                .orElseThrow(() -> UserWithGivenLoginDoesntExistException.create(username));

        UserRole role = login.getRole();

        Optional<Review> optionalReview = reviewRepository.findById(id);

        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();


            Integer userId = login.getUser().getId();

            if (!reviewRepository.existsById(id)) {
                throw ReviewDoesntExistException.create(id);
            } else if (role == UserRole.ROLE_LIBRARIAN || review.getUser().getId().equals(userId)) {
                reviewRepository.deleteById(id);
            } else {
                throw UnauthorizedException.create();
            }
        }
    }

    /**
     * Updates a review.
     *
     * @param id            The ID of the review to update.
     * @param updatedReview The ReviewDto object containing the updated details of the review.
     * @return The updated ReviewResponseDto object representing the review.
     * @throws ReviewDoesntExistException If the review with the given ID does not exist.
     * @throws UnauthorizedException      If the user is not authorized to update the review.
     */
    public ReviewResponseDto updateReview(Integer id, ReviewDto updatedReview) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> ReviewDoesntExistException.create(id));

        if (!existingReview.getUser().getLogin().getUsername().equals(username)) {
            throw UnauthorizedException.create();
        }

        Book book = bookRepository.findById(updatedReview.getBook())
                .orElseThrow(() -> BookDoesntExistException.create(updatedReview.getBook()));

        User user = userRepository.findById(updatedReview.getUser())
                .orElseThrow(() -> UserNotFoundException.create(updatedReview.getUser()));

        existingReview.setBook(book);
        existingReview.setUser(user);
        existingReview.setGrade(updatedReview.getGrade());
        existingReview.setComment(updatedReview.getComment());
        existingReview.setReviewDate(updatedReview.getReviewDate());

        Review savedReview = reviewRepository.save(existingReview);
        return mapReview(savedReview);
    }

}


