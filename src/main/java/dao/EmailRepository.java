package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.Email;
@Repository("emailRepository")
public interface EmailRepository extends MongoRepository<Email, String>{

}
