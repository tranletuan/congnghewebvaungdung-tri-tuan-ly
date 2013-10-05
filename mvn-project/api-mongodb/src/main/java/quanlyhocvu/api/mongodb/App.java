package quanlyhocvu.api.mongodb;

import java.util.List;
import quanlyhocvu.api.mongodb.service.MongoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import quanlyhocvu.api.mongodb.DTO.address.AddressDTO;
import quanlyhocvu.api.mongodb.DTO.address.DistrictDTO;
import quanlyhocvu.api.mongodb.DTO.address.ProvinceDTO;
import quanlyhocvu.api.mongodb.DTO.address.WardDTO;

class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new GenericXmlApplicationContext(
                "noSqlApplicationContext.xml");
        MongoService mongoService = (MongoService) ctx.getBean("mongoService");
        List<ProvinceDTO> list = mongoService.getAllProvince();
        String provinceId = list.get(0).getId();
        DistrictDTO district = new DistrictDTO();
        List<DistrictDTO> list1 = mongoService.getDistrictByProvinceId(provinceId);
        List<WardDTO> list2 = mongoService.getWardByDistrictId(list1.get(0).getId());
        AddressDTO address = new AddressDTO();
        address.setAddressName("55/12");
        address.setWardId(list2.get(0).getId());
        mongoService.insertAddress(address);
    }
}
