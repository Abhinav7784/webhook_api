package com.fintech.webhook_api.controller;

import com.fintech.webhook_api.model.ReportTransaction;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalDate;
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
public class ReportSseController {

    @GetMapping(
            value = "/reports",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter streamReports(
            @RequestHeader("external_ref_id") String externalRefId,
            @RequestHeader("correlation_id") String correlationId) {

        SseEmitter emitter = new SseEmitter(0L);

        List<ReportTransaction> reports = new CopyOnWriteArrayList<>(List.of(
                new ReportTransaction("NAID-001", "PAYMENT", "PDF", "CREATED",
                        LocalDate.now().minusDays(5), LocalDate.now()),
                new ReportTransaction("NAID-002", "SETTLEMENT", "CSV", "CREATED",
                        LocalDate.now().minusDays(3), LocalDate.now()),
                new ReportTransaction("NAID-003", "REFUND", "TXT", "CREATED",
                        LocalDate.now().minusDays(1), LocalDate.now())
        ));

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                for (ReportTransaction r : reports) {

                    r.setReportStatus("IN_PROGRESS");
                    emitter.send(SseEmitter.event()
                            .name("report-update")
                            .data(reports));
                    Thread.sleep(2000);

                    r.setReportStatus("SUCCESS");
                    emitter.send(SseEmitter.event()
                            .name("report-update")
                            .data(reports));
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
