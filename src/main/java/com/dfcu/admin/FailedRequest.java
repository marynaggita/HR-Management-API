package com.dfcu.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "failedrequests")

public class FailedRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action")
    private String action;

    @Column(name = "error")
    private String error;

    @Column(name = "http_status_code")
    private int httpStatusCode;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
