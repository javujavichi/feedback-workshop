package feedback.workshop.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import feedback.workshop.application.service.FeedbackService;

import java.util.List;

@RestController
public class FeedbackController {
   private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody FeedbackRequest feedbackRequest){
        this.feedbackService.save(feedbackRequest);
    }

    @GetMapping("/feedback/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<FeedbackResponse> getById(@PathVariable Integer id){
        return this.feedbackService.findById(id);
    }

    @GetMapping("/feedback")
    @ResponseStatus(HttpStatus.OK)
    public List<FeedbackResponse> getByGiven(@RequestParam(required = false) String givenBy){
        return this.feedbackService.findAllByGivenBy(givenBy);
    }

    @GetMapping("/feedback/all")
    @ResponseStatus(HttpStatus.OK)
    public List<FeedbackResponse> all(){
        return this.feedbackService.findAll();
    }
}
