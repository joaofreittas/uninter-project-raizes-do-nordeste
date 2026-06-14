package com.uninter.gerenciadorunidade.dataprovider.database.auth;

import com.uninter.gerenciadorunidade.core.domain.UserDomain;
import com.uninter.gerenciadorunidade.core.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDatabaseGateway implements UserGateway {

    private final UserJpaRepository repository;

    @Override
    public UserDomain save(final UserDomain user) {
        return repository.save(UserEntity.from(user)).toDomain();
    }

    @Override
    public Optional<UserDomain> findByEmail(final String email) {
        return repository.findByEmail(email).map(UserEntity::toDomain);
    }

}
