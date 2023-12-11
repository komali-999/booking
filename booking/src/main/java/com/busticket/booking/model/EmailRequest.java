package com.busticket.booking.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmailRequest {

    String from;
    String to;
    String subject;
    String cc;
    String fileName;

}
