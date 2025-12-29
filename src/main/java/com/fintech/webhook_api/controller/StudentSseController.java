package com.fintech.webhook_api.controller;

import com.fintech.webhook_api.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = { RequestMethod.GET, RequestMethod.OPTIONS }
)
@RestController
@RequestMapping("/api")
public class StudentSseController {

    @GetMapping(
            value = "/stream",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter streamStudents(
            @RequestHeader("external_ref_id") String externalRefId,
            @RequestHeader("correlation_id") String correlationId) {

        SseEmitter emitter = new SseEmitter(0L);

        // Initial data
        List<Student> students = new CopyOnWriteArrayList<>(List.of(
                new Student(1L, "Alice", "PENDING"),
                new Student(2L, "Bob", "PENDING"),
                new Student(3L, "Charlie", "PENDING")
        ));

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                for (int i = 0; i < students.size(); i++) {

                    // Update ONE field every time
                    students.get(i).setStatus("COMPLETED");

                    emitter.send(SseEmitter.event()
                            .name("students-update")
                            .data(students));

                    Thread.sleep(2000);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
