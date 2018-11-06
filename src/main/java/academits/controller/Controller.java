package academits.controller;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiWallLinksForbiddenException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.wall.responses.GetResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/testTask/api")
public class Controller {
    private static final Integer APP_ID = 6731226;
    private static final String CLIENT_SECRET = "hLu7TXcJ7PKQ3vwvWoNI";
    private static final String REDIRECT_URI = "http://localhost:8080/testTask/api/logIn";
    private boolean needAddPost = false;

    @RequestMapping(value = "/logIn", method = RequestMethod.GET)
    public List logIn(@RequestParam(value = "code", required = false) String code,
                      @RequestParam(value = "post", required = false) String post,
                      /*@RequestParam(value = "access_token", required = false) String token,*/
                      HttpServletResponse resp, HttpServletRequest request) throws IOException, ClientException, ApiException {
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        //System.out.println(code);
        if (post == null && code == null) {
            resp.setStatus(403);
        } else if (post != null) {
            needAddPost = true;
            resp.setStatus(403);
        } else {
            UserAuthResponse authResponse = vk.oauth()
                    .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
            UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
            if (!needAddPost) {
                GetResponse getResponse = vk.wall().get(actor).execute();
                getResponse.getItems();
                return getResponse.getItems(); // Можно попробовать что-то другое вернуть
            } else {

                try {
                    needAddPost = false;
                    vk.wall().post(actor).message("Hello world").execute();
                } catch (ApiWallLinksForbiddenException e) {
                    // Links posting is prohibited
                } catch (ApiException e) {
                    // Business logic error
                } catch (ClientException e) {
                    // Transport layer error
                }
            }
        }
        return null;
    }
}