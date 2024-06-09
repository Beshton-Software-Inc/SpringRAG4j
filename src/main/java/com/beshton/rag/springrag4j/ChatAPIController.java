package com.beshton.rag.springrag4j;

//import com.beshton.rag.service.Rag4jService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
public class ChatAPIController {

//    private final Rag4jService rag4jService;
    private final RAG4jService rag4jService;
    private final IndexingtoWeaviateService indexingtoWeaviateService;

    public ChatAPIController(RAG4jService rag4jService, IndexingtoWeaviateService indexingtoWeaviateService) {
        this.rag4jService = rag4jService;
        this.indexingtoWeaviateService = indexingtoWeaviateService;
    }

    //Possible questions:
//    List<String> exampleSentences = List.of(
//            "How many bolts were replaced?",
//            "Since When could people visit the Vasa?",
//            "Since when was the Vasa available for the public to visit?",
//            "Who was responsible for building the Vasa ship?",
//            "Where did the person responsible for building the Vasa ship come from?",
////                questions that I added below:
//            "When did Vasa move?",
//            "Where was the keel of Vasa laid during the late winter?",
//            "Who passed away a year after Vasa's design was completed?",
//            "What is required for certain things to happen?",
//            "What is the possible result of high humidity combining with sulphur in the wood?"
//
//    );


    @PostMapping("/api/ask")
    public ResponseEntity<?> askQuestion(@RequestBody String question) {
        String answer = rag4jService.processQuestion(question);
        return ResponseEntity.ok().body(Map.of("answer", answer));
    }

    @GetMapping("/api/test")
    public String indextest() {
        return "Greetings from Spring Boot again!";
    }

    @PostMapping("/api/upload")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        //process the file or store it
        String message;
        try {
            // Process or store file here
            indexingtoWeaviateService.processFile(file);
            message = file.getOriginalFilename() + " uploaded successfully!";
        } catch (Exception e) {
            message = "Failed to upload " + file.getOriginalFilename() + "!" + "The reason is" + e;
        }
        return ResponseEntity.ok().body(Map.of("message", message));
    }

}

