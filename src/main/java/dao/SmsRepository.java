package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.Sms;
@Repository("smsRepository")
public interface SmsRepository extends MongoRepository<Sms, String>{

}
