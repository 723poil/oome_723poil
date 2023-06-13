package org.oome.infra.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class EmailSendDto {

    private String to;

    private String subject;

    private String message;
}
