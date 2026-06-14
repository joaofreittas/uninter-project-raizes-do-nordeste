package com.uninter.gerenciadorunidade.core.gateway;

import com.uninter.gerenciadorunidade.core.domain.user.UserDomain;

import java.util.Optional;

public interface UserGateway {

    UserDomain save(UserDomain user);

    Optional<UserDomain> findByEmail(String email);

}
