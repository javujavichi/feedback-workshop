package feedback.workshop.application.infrastructure.web.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import feedback.workshop.application.domain.entity.Feedback;
import feedback.workshop.application.infrastructure.web.model.FeedbackResponse;
import feedback.workshop.application.infrastructure.web.model.SubmitFeedbackRequest;
import feedback.workshop.application.usecase.GetAllFeedbackUseCase;
import feedback.workshop.application.usecase.GetFeedbackById;
import feedback.workshop.application.usecase.GetFeedbackByGivenByUseCase;
import feedback.workshop.application.usecase.SubmitFeedbackUseCase;

@RestController
@RequestMapping("/feedback")
public class FeedbackEndpoint {
    private final SubmitFeedbackUseCase submitFeedbackUseCase;
    private final GetFeedbackById getFeedbackByIdUseCase;
    private final GetAllFeedbackUseCase getAllFeedbackUseCase;
    private final GetFeedbackByGivenByUseCase getFeedbackByGivenByUseCase;

    public FeedbackEndpoint(SubmitFeedbackUseCase submitFeedbackUseCase,
            GetFeedbackById getFeedbackByIdUseCase, GetAllFeedbackUseCase getAllFeedbackUseCase,
            GetFeedbackByGivenByUseCase getFeedbackByGivenByUseCase) {
        this.submitFeedbackUseCase = submitFeedbackUseCase;
        this.getFeedbackByIdUseCase = getFeedbackByIdUseCase;
        this.getAllFeedbackUseCase = getAllFeedbackUseCase;
        this.getFeedbackByGivenByUseCase = getFeedbackByGivenByUseCase;

    }

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(@RequestBody SubmitFeedbackRequest submitFeedbackRequest) {
        Feedback feedback = submitFeedbackUseCase.submitFeedback(
            submitFeedbackRequest.getGivenBy(), 
            submitFeedbackRequest.getFeedback());
        FeedbackResponse feedbackResponse 
        = new FeedbackResponse(
            feedback.getId(), 
            feedback.getGivenBy(),
            feedback.getFeedback());
        return ResponseEntity.ok(feedbackResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponse> getFeedbackById(@PathVariable Integer id) {
        Optional<Feedback> feedbackOptional = getFeedbackByIdUseCase.getFeedbackById(id);
        if (feedbackOptional.isPresent()) {
            Feedback feedback = feedbackOptional.get();
            FeedbackResponse feedbackResponse = FeedbackResponse.fromFeedback(feedback);
            return ResponseEntity.ok(feedbackResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponse>> getAllFeedback() {
        List<Feedback> feedbackList = getAllFeedbackUseCase.getAllFeedback();
        List<FeedbackResponse> feedbackResponseList = feedbackList.stream()
                .map(feedback -> new FeedbackResponse(feedback.getId(), feedback.getGivenBy(), feedback.getFeedback()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbackResponseList);
    }

    @GetMapping("/givenBy/{givenBy}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByUser(@PathVariable String givenBy) {
        List<Feedback> feedbackList = getFeedbackByGivenByUseCase.getFeedbackByGivenBy(givenBy);
        List<FeedbackResponse> feedbackResponseList = feedbackList.stream()
                .map(feedback -> FeedbackResponse.fromFeedback(feedback))
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbackResponseList);
    }
}