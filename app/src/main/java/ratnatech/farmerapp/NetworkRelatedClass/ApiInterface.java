package ratnatech.farmerapp.NetworkRelatedClass;

import ratnatech.farmerapp.ModelClass.ResponseModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {

    @Multipart
    @POST("api/upload/file_upload")//  file_upload_api/upload.php
    Call<ResponseModel> fileUpload(
            // @Part("sender_information") RequestBody description,
            @Part("qna_id") RequestBody description,

            @Part("my_story_id") RequestBody description2,
            @Part MultipartBody.Part file);


}
