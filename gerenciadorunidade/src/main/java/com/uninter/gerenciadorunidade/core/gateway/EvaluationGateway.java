package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.EvaluationDomain;

import java.util.List;

public interface EvaluationGateway {

    EvaluationDomain save(EvaluationDomain evaluation);

    List<EvaluationDomain> findByEmployeeId(Long employeeId);

}
