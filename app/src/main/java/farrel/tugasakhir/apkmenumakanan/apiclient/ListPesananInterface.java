package farrel.tugasakhir.apkmenumakanan.apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ListPesananInterface {
    @GET("ListPesanan/")
    Call<List<ListPesanan>> getListPesanan();

    @FormUrlEncoded
    @POST("ListPesanan/")
    Call<ListPesanan> postListPesanan(@Field("namaMakanan")String namaMakanan, @Field("hargaMakanan")int hargaMakanan, @Field("jumlahMakanan")int jumlahMakanan);

    @DELETE("ListPesanan/")
    Call<ListPesanan> delListPesanan(@Query("id")int id);

}
