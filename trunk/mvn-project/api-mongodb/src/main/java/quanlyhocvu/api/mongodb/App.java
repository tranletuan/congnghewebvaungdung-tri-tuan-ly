package quanlyhocvu.api.mongodb;

import quanlyhocvu.api.mongodb.service.MongoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new GenericXmlApplicationContext(
                "noSqlApplicationContext.xml");
        MongoService mongoService = (MongoService) ctx.getBean("mongoService");
    }
}
