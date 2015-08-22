package dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.AccountPref;

@Repository("acntPrefRepo")
public interface AccPrefRepository extends MongoRepository<AccountPref, String>{
	public List<AccountPref> findByAcctNo(String acctNo);
}
