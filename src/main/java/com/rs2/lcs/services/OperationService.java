package com.rs2.lcs.services;

import com.rs2.lcs.dto.OperationDto;
import com.rs2.lcs.exceptions.InvalidOperationException;
import com.rs2.lcs.model.OperationEnum;

public interface OperationService {
    void save(OperationDto operationDto, OperationEnum operationType) throws InvalidOperationException;
}
