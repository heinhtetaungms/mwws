package org.mw.mwws.repo;

import org.mw.mwws.entity.Role;
import org.mw.mwws.utils.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends BaseRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
