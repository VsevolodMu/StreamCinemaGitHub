package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JwtRepository extends JpaRepository<Jwt, Long> {
    @Query("""
                    from Jwt jwt
                    join fetch jwt.user
                    where jwt.user.id=:userId and jwt.isRevoked=false
            """)
    Optional<List<Jwt>> findAllValidTokensByUser(@Param(value = "userId") long userId);

    Optional<Jwt> findByToken(String token);

}
