package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.UnitDomain;

import java.util.List;
import java.util.Optional;

public interface UnitGateway {

    UnitDomain save(UnitDomain unit);

    Optional<UnitDomain> findById(Long id);

    List<UnitDomain> findAll();

    void delete(UnitDomain unit);

}
