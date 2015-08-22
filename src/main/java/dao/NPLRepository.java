package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.NotificationPayload;

@Repository("nplRepository")
public interface NPLRepository extends MongoRepository<NotificationPayload, String>{}
