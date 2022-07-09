package com.muzeffereli.springbootphonebookapi.dto;

import com.muzeffereli.springbootphonebookapi.utils.OperationStatus;
import com.muzeffereli.springbootphonebookapi.utils.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    private UUID userId;
    private OperationType operationType;
    private OperationStatus operationStatus;
}
