package ru.myitlesson.api.request;

import com.google.gson.JsonObject;
import retrofit2.Response;
import ru.myitlesson.api.MyItLessonClient;
import ru.myitlesson.api.Utils;
import ru.myitlesson.api.entity.UserEntity;
import ru.myitlesson.api.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class UserRequest extends Request<UserService> {

    public UserRequest(MyItLessonClient client, UserService service) {
        super(client, service);
    }

    public UserEntity get(int id) throws IOException {
        Response<UserEntity> response = service.get(client.getBasic(), id).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public List<UserEntity> getAmount(int limit, int offset) throws IOException {
        Response<List<UserEntity>> response = service.getAmount(client.getBasic(), limit, offset).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public int add(UserEntity user) throws IOException {
        Response<JsonObject> response = service.add(client.getBasic(), user.getUsername(), user.getPassword(), user.getRole().getValue()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));

        if(client.getError() != null) {
            return -1;
        }

        return Objects.requireNonNull(response.body()).get("id").getAsInt();
    }

    public void delete(UserEntity user) throws IOException {
        Response<JsonObject> response = service.delete(client.getBasic(), user.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
    }
}
