package dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.AccountPref;
import dto.TemplateLink;

@Repository("acntPrefRepo")
public interface TemplateRepository extends MongoRepository<TemplateLink, String>{
	public List<AccountPref> findByAcctNo(String acctNo);
}
