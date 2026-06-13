package com.uninter.gerenciadorunidade.service.unity;

import com.uninter.gerenciadorunidade.controller.employee.dto.AddEvaluationRequest;
import com.uninter.gerenciadorunidade.controller.employee.dto.CreateEmployeeRequest;
import com.uninter.gerenciadorunidade.controller.employee.dto.UpdateEmployeeRequest;
import com.uninter.gerenciadorunidade.model.unit.Employee;
import com.uninter.gerenciadorunidade.model.unit.Evaluation;
import com.uninter.gerenciadorunidade.repository.EmployeeRepository;
import com.uninter.gerenciadorunidade.repository.EvaluationRepository;
import com.uninter.gerenciadorunidade.repository.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EvaluationRepository evaluationRepository;
    private final UnitRepository unitRepository;

    public Employee create(final CreateEmployeeRequest request) {
        var unit = unitRepository
            .findById(request.unitId())
            .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + request.unitId()));

        if (employeeRepository.findByDocument(request.document()).isPresent()) {
            throw new IllegalArgumentException("Document already registered: " + request.document());
        }

        var now = LocalDateTime.now();
        var employee = Employee.builder()
            .unit(unit)
            .name(request.name())
            .address(request.address())
            .document(request.document())
            .birthDate(request.birthDate())
            .type(request.type())
            .createdAt(now)
            .updatedAt(now)
            .build();

        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(final Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found: " + id));
    }

    public Employee update(final Long id, final UpdateEmployeeRequest request) {
        var existing = findById(id);
        var updated = Employee.builder()
            .id(existing.getId())
            .unit(existing.getUnit())
            .name(request.name())
            .address(request.address())
            .document(existing.getDocument())
            .birthDate(request.birthDate())
            .type(request.type())
            .createdAt(existing.getCreatedAt())
            .updatedAt(LocalDateTime.now())
            .build();

        return employeeRepository.save(updated);
    }

    public Evaluation addEvaluation(final Long employeeId, final AddEvaluationRequest request) {
        var employee = findById(employeeId);
        var now = LocalDateTime.now();
        var evaluation = Evaluation.builder()
            .employee(employee)
            .rating(request.rating())
            .comment(request.comment())
            .createdAt(now)
            .updatedAt(now)
            .build();

        return evaluationRepository.save(evaluation);
    }

    public void delete(final Long id) {
        var employee = findById(id);
        employeeRepository.delete(employee);
    }

}
