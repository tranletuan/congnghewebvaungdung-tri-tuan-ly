package quanlyhocvu.api.web.controller.guest;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import quanlyhocvu.api.mongodb.DTO.Authority.UserDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CatalogNewsDTO;
import quanlyhocvu.api.mongodb.DTO.staff.CoverImageDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsCounterDTO;
import quanlyhocvu.api.mongodb.DTO.staff.NewsDTO;
import quanlyhocvu.api.mongodb.jms.ClientJMS;
import quanlyhocvu.api.mongodb.jms.ServerJMS;
import quanlyhocvu.api.mongodb.service.MongoService;
import quanlyhocvu.api.web.util.Tools;

@Controller
public class HomeController {

    @Autowired
    MongoService mongoService;

    @Autowired
    ServerJMS serverJMS;

    @RequestMapping(value = {"check_online"})
    public @ResponseBody
    ModelAndView checkMessage() throws InterruptedException {
        Map<String, Object> model = new HashMap<>();

        try {
            //Server kiểm tra thông điệp người dùng online
            List<String> listUserId = serverJMS.getListUserId();
            List<UserDTO> listUserOnline = new ArrayList<UserDTO>();

            for (String userId : listUserId) {
                UserDTO user = mongoService.getUserById(userId);
                listUserOnline.add(user);
            }

            model.put("listUser", listUserOnline);

        } catch (Exception e) {

        }

        return new ModelAndView("guest/list_online", model);
    }

    @RequestMapping(value = {"receiveChat"})
    public @ResponseBody
    ModelAndView receiveMessageChat(HttpServletRequest request) {
        Map<String, Object> model = new HashMap<>();
        String receiveId = request.getParameter("receiveId");
        String sendId = request.getParameter("sendId").substring(8);
        UserDTO sender = mongoService.getUserById(sendId);
        UserDTO receiver = mongoService.getUserById(receiveId);

        List<String> listMessage = serverJMS.getMessageBy(sendId, receiveId);

//        System.out.println(sender.getUsername());
//        System.out.println(receiver.getUsername());
        if (listMessage != null) {
            System.out.println(listMessage);
        }

        model.put("listMessage", listMessage);
        model.put("chatboxtitle", sender.getUsername());

        return new ModelAndView("guest/content_chat", model);
    }

    @RequestMapping(value = {"sendMessage"})
    public @ResponseBody
    ModelAndView sendMessageChat(HttpServletRequest request) throws JMSException {
        Map<String, Object> model = new HashMap<>();
        String receiveId = request.getParameter("receiveId");
        String sendId = request.getParameter("sendId");
        String message = request.getParameter("message");

        try {
            ClientJMS client = new ClientJMS(sendId);
            client.sendMessageChat(receiveId, message);
        } catch (Exception e) {

        }
        return new ModelAndView("guest/content_chat", model);
    }

    @RequestMapping(value = {"", "/", "home", "index"})
    public @ResponseBody
    ModelAndView index(HttpServletRequest request) throws JMSException, InterruptedException {
        Map<String, Object> model = new HashMap<>();
        String username = Tools.getCurrentUser();
        if (username != null) {
            UserDTO dto = mongoService.getUserByUserName(username);
            if (dto != null) {
                ClientJMS client = new ClientJMS((dto.getId()));
                client.sendStatusOnline();

                //Tránh trường hợp server chưa xử lý thông điệp kiệp thời thì listUser vẫn tồn tại ở server
                List<UserDTO> listUser = new ArrayList<UserDTO>();

                model.put("listUser", listUser);
            }

            model.put("user", dto);
        }
        List<CatalogNewsDTO> list = mongoService.getAllCatalog();
        model.put("listCatalogs", list);
        List<CoverImageDTO> listcover = mongoService.getAllCovers();
        model.put("covers", listcover);
        return new ModelAndView("guest/home", model);
    }

    @RequestMapping(value = "home_list_news/{catalogId}/{page}")
    public @ResponseBody
    ModelAndView homeListNews(
            @PathVariable(value = "catalogId") String catalogId,
            @PathVariable(value = "page") String page,
            HttpServletRequest request
    ) {
        Map<String, Object> model = new HashMap<>();
        int limit = 6;
        int offset = 0;
        try {
            offset = limit * (Integer.parseInt(page) - 1);
        } catch (NumberFormatException ex) {
            model.put("message", "Xảy ra lỗi tải trang");
            return new ModelAndView("guest/home_list_news", model);
        }
        List<NewsDTO> listNews;
        if (!"0".equals(catalogId)) {
            listNews = mongoService.getNewsByCatalogIdAndPage(catalogId, limit, offset);
        } else {
            listNews = mongoService.getAllNewsByPage(limit, offset);
            if (listNews.isEmpty()) {
                listNews = mongoService.getAllNewsByPage(limit, 0);
            }
        }
        model.put("listNews", listNews);
        return new ModelAndView("guest/home_list_news", model);
    }

    @RequestMapping(value = "news/{newsId}")
    @ResponseBody
    public ModelAndView news_id(
            @PathVariable(value = "newsId") String newsId,
            HttpServletRequest request
    ) {
        Map<String, Object> model = new HashMap<>();
        mongoService.increaseConterNews(newsId);
        List<NewsCounterDTO> listcounter = mongoService.getHotNews(5);
        List<CoverImageDTO> listcover = mongoService.getAllCovers();
        model.put("covers", listcover);
        String content = mongoService.getNewsContentByNewsId(newsId);
        model.put("content", content);
        model.put("listhotnews", listcounter);
        return new ModelAndView("guest/home", model);

    }

}
