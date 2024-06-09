//package com.beshton.rag.controller;
//
//import com.beshton.rag.service.Rag4jService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//public class ChatApiController {
//
////    private final Rag4jService rag4jService;
////
////    public ChatApiController(Rag4jService rag4jService) {
////        this.rag4jService = rag4jService;
////    }
////
////    @PostMapping("/api/ask")
////    public ResponseEntity<?> askQuestion(@RequestBody String question) {
////        String answer = rag4jService.processQuestion(question);
////        return ResponseEntity.ok().body(Map.of("answer", answer));
////    }
//
//    @GetMapping("/api/test")
//    public String indextest() {
//        return "Greetings from Spring Boot again!";
//    }
//
//}
