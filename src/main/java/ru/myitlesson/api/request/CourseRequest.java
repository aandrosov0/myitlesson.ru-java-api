package ru.myitlesson.api.request;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import ru.myitlesson.api.MyItLessonClient;
import ru.myitlesson.api.Utils;
import ru.myitlesson.api.entity.CourseEntity;
import ru.myitlesson.api.entity.UserEntity;
import ru.myitlesson.api.service.CourseService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;
import java.util.Objects;

public class CourseRequest extends Request<CourseService> {

    public CourseRequest(MyItLessonClient client, CourseService service) {
        super(client, service);
    }

    public CourseEntity get(int id) throws IOException {
        Response<CourseEntity> response = service.get(client.getBasic(), id).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public List<CourseEntity> getAmount(int limit, int offset) throws IOException {
        Response<List<CourseEntity>> response = service.getAmount(client.getBasic(), limit, offset).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
        return response.body();
    }

    public int add(CourseEntity course, UserEntity user) throws IOException {
        Response<JsonObject> response = service.add(client.getBasic(), course.getTitle(), course.getDescription(), user.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));

        if(client.getError() != null) {
            return -1;
        }

        int id = Objects.requireNonNull(response.body()).get("id").getAsInt();
        user.getAuthoredCourses().add(id);
        course.setAuthor(user.getId());

        return id;
    }

    public void delete(CourseEntity course) throws IOException {
        Response<JsonObject> response = service.delete(client.getBasic(), course.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
    }

    public File getImage(CourseEntity course) throws IOException {
        Response<ResponseBody> response = service.getImage(client.getBasic(), course.getId()).execute();
        client.setError(Utils.parseErrorIfExists(client, response));

        if(client.getError() != null) {
            return null;
        }

        try(ResponseBody body = Objects.requireNonNull(response.body())) {
            InputStream inputStream = body.byteStream();
            String filename = Objects.requireNonNull(response.headers().get("Content-Disposition")).split("\"")[1];
            Path tempFilePath = Paths.get(System.getProperty("java.io.tmpdir") + "/" + filename);
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
            return tempFilePath.toFile();
        }
    }

    public void setImage(CourseEntity course, File image) throws IOException {
        String fileMimeType = Files.probeContentType(Paths.get(image.getPath()));
        RequestBody imageRequestBody = RequestBody.create(MediaType.get(fileMimeType), image);

        Response<JsonObject> response = service.setImage(client.getBasic(), course.getId(), imageRequestBody).execute();
        client.setError(Utils.parseErrorIfExists(client, response));
    }
}
