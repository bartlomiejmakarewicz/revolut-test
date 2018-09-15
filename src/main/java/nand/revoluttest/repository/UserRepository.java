package nand.revoluttest.repository;

import nand.revoluttest.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
