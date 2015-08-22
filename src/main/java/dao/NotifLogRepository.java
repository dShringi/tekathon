package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.NotificationLog;

@Repository("notifLogRepo")
public interface NotifLogRepository extends MongoRepository<NotificationLog, String>{
}
