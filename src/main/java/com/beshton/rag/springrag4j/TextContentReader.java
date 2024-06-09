package com.beshton.rag.springrag4j;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

import org.rag4j.indexing.ContentReader;
import org.rag4j.indexing.InputDocument;
import org.springframework.web.multipart.MultipartFile;

public class TextContentReader implements ContentReader {
    private final MultipartFile file;
    public TextContentReader(MultipartFile file) {
        this.file = file;
    }

//    @Override
//    public Stream<InputDocument> read() {
//        String filename = "vasa-timeline.jsonl";
//        JsonlReader jsonlReader = new JsonlReader(List.of("title", "timerange", "body"), filename);
//        return jsonlReader.getLines().stream().map((line) -> {
//            Map<String, Object> properties = Map.of("title", line.get("title"), "timerange", line.get("timerange"));
//            String documentId = ((String)line.get("title")).toLowerCase(Locale.ROOT).replace(" ", "-");
//            return InputDocument.builder().documentId(documentId).text((String)line.get("body")).properties(properties).build();
//        });
//    }

    //Not sure how to read text content here.... too many different forms?
    @Override
    public Stream<InputDocument> read() {
        System.out.println("file is delivered successfully here!!");
        System.out.println("The file name is :" + file.getOriginalFilename());

        String filename = file.getOriginalFilename();

        //I manually replaced JsonReader to MyJsonReader since I don't need fileName, but just simply file
        //that front end uploaded here
        MyJsonReader jsonlReader = new MyJsonReader(List.of("title", "timerange", "body"), file);
        return jsonlReader.getLines().stream().map(line -> {
            Map<String, Object> properties = Map.of(
                    "title", line.get("title"),
                    "timerange", line.get("timerange")
            );
            String documentId = line.get("title").toLowerCase(Locale.ROOT).replace(" ", "-");
            return InputDocument.builder()
                    .documentId(documentId)
                    .text(line.get("body"))
                    .properties(properties)
                    .build();
        });

//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
//            System.out.println("Inside reader.....");
//            return reader.lines().map(line -> {
//                // Example conversion, replace with actual logic to parse your specific file format
//                return InputDocument.builder()
//                        .documentId("example-id") // Determine ID based on content
//                        .text(line) // Use line or parse line for actual text
//                        .build();
//            });
//
//        } catch (Exception e) {
//            System.err.println("Failed to process the file: " + file.getOriginalFilename());
//            e.printStackTrace();
//            return Stream.empty();
//        }

    }
}
