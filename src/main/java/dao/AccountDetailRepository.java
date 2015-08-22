package dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dto.AccountDetail;

@Repository("acctDetailRepo")
public interface AccountDetailRepository extends MongoRepository<AccountDetail, String>{}
