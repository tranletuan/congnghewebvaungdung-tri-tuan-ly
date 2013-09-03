package quanlyhocvu.api.mongodb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoService {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    MongoOperations mongoOperations;
    @Autowired
    GridFsTemplate gridFsTemplate;
}
