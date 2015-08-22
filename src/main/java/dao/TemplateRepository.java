package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.TemplateLink;

@Repository("templateRepo")
public interface TemplateRepository extends MongoRepository<TemplateLink, String>{}
