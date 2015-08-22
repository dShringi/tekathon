package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.Web;
@Repository("webRepository")
public interface WebRepository extends MongoRepository<Web, String>{

}
