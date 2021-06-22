package com.kluevja.interscore.controller;

import com.kluevja.interscore.entity.*;
import com.kluevja.interscore.repository.*;
import com.kluevja.interscore.security.AuthResponse;
import com.kluevja.interscore.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private OptionRepository optionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/profile/{id:\\d+}")
    public ResponseEntity userPage(@AuthenticationPrincipal UserPrincipal principal, @PathVariable("id") UserEntity user) {
        if(user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("Данного пользователя не найдено");
    }

    @GetMapping("/poll/{id}")
    public Poll getPoll(@PathVariable("id") Long idPoll) {
        Optional<Poll> pollEntity = pollRepository.findById(idPoll);
        if(pollEntity.isPresent()) {
            return pollEntity.get();
        }else{
            return null;
        }
    }

    @GetMapping("/interview/{id}")
    public Interview getInterview(@PathVariable("id") Long idInterview) {
        Optional<Interview> interviewEntity = interviewRepository.findById(idInterview);
        if(interviewEntity.isPresent()) {
            return interviewEntity.get();
        }else{
            return null;
        }
    }

    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers(@AuthenticationPrincipal UserPrincipal principal) {
        return userRepository.findAll();
    }

    @GetMapping("/login")
    public String login() {
        return "ok";
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity userEntity) {
        if(userService.canLogin(userEntity)) {
            AuthResponse response = userService.login(userEntity);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Ошибка при авторизации");
    }

    @GetMapping("/activation/{code}")
    public String activation(@PathVariable String code) {
        if(userService.activate(code)){
            return "redirect:/login";
        }else{
            return "redirect:/login";
        }
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody UserEntity userEntity) {
        if(userService.canRegister(userEntity)) {
            AuthResponse response = userService.register(userEntity);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("Пользователь с данным именем или email уже существует.");
    }

    @PostMapping("/createInterview")
    public String createInterview(@RequestBody Interview interviewEntity) {
        System.out.println(interviewEntity);

        UserEntity interviewee = userRepository.findById(interviewEntity.getInterviewee().getId()).get();
        UserEntity interviewer = userRepository.findById(interviewEntity.getInterviewer().getId()).get();

        interviewEntity.setInterviewee(interviewee);
        interviewEntity.setInterviewer(interviewer);

        for(Question t : interviewEntity.getQuestions()) {
            questionRepository.save(t);
        }
        interviewRepository.save(interviewEntity);
        return "success";
    }

    @PostMapping("/answerInterview")
    public String answerInterview(@RequestBody Interview interviewEntity) {
        for(Question t : interviewEntity.getQuestions()) {
            questionRepository.save(t);
        }
        interviewRepository.save(interviewEntity);
        return "success";
    }

    @GetMapping("/getMyInterviews/{id}")
    public List<Interview> getMyInterviews(@PathVariable Long id) {
        return interviewRepository.findByInterviewerId(id);
    }

    @GetMapping("/getMyInterviewsUser/{id}")
    public List<Interview> getMyInterviewsUser(@PathVariable Long id) {
        return interviewRepository.findByIntervieweeId(id);
    }

    @PostMapping("/pollCreate")
    public String pollCreate(@RequestBody Poll pollEntity) {
        UserEntity interviewee = userRepository.findById(pollEntity.getInterviewee().getId()).get();
        UserEntity interviewer = userRepository.findById(pollEntity.getInterviewer().getId()).get();

        pollEntity.setInterviewee(interviewee);
        pollEntity.setInterviewer(interviewer);

        for(Test t : pollEntity.getTests()) {
            for(Option o : t.getOptions()) {
                optionRepository.save(o);
            }
            testRepository.save(t);
        }
        pollRepository.save(pollEntity);
        return "success";
    }

    @PostMapping("/answerPoll")
    public String answerPoll(@RequestBody Poll pollEntity) {
        for(Test t : pollEntity.getTests()) {
            for(Option o : t.getOptions()) {
                optionRepository.save(o);
            }
            testRepository.save(t);
        }
        pollRepository.save(pollEntity);
        return "success";
    }

    @PostMapping("/categoryCreate")
    public String categoryCreate(@RequestBody Category categoryEntity) {
        if(categoryEntity.getParent().getId() == null){
            categoryEntity.setParent(null);
        }else{
            Category categoryRoot = categoryRepository.findById(categoryEntity.getParent().getId()).get();
            categoryEntity.setParent(categoryRoot);
        }
        categoryRepository.save(categoryEntity);
        return "success";
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(@AuthenticationPrincipal UserPrincipal principal) {
        return categoryRepository.findAll();
    }

    @GetMapping("/getMyPolls/{id}")
    public List<Poll> getMyPolls(@PathVariable Long id) {
        return pollRepository.findByInterviewerId(id);
    }

    @GetMapping("/getMyPollsUser/{id}")
    public List<Poll> getMyPollsUser(@PathVariable Long id) {
        return pollRepository.findByIntervieweeId(id);
    }

    @PostMapping("/uploadFile/{id}")
    public ResponseEntity uploadFile(@RequestBody String photoUrl, @PathVariable Long id) {
        Optional<UserEntity> user = userRepository.findById(id);

        if(user.isPresent()){
            UserEntity userPreset = user.get();
            userPreset.setPhoto(photoUrl);
            userRepository.save(userPreset);
        }

        return ResponseEntity.ok().body("hi");
    }
}