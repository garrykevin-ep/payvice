package com.garrykevin.payvice.repository;

import com.garrykevin.payvice.model.User;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  Optional<User> findById(Long id);

  Set<User> findByIdIn(Set<Long> ids);
}
