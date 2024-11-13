package org.mw.mwws.repo;

import org.mw.mwws.entity.User;
import org.mw.mwws.utils.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends BaseRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
