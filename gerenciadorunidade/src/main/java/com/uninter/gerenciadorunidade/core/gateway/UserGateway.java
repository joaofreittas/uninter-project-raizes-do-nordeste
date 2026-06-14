package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.UserDomain;

import java.util.Optional;

public interface UserGateway {

    UserDomain save(UserDomain user);

    Optional<UserDomain> findByEmail(String email);

}
