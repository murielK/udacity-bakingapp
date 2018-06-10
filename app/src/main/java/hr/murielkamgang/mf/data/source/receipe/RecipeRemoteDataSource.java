package hr.murielkamgang.mf.data.source.receipe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.DataSourceException;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import hr.murielkamgang.mf.data.source.base.BaseRemoteDataSource;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public class RecipeRemoteDataSource extends BaseRemoteDataSource<Recipe, BaseKVH> {

    private final Logger logger = LoggerFactory.getLogger(RecipeRemoteDataSource.class);
    private final RecipeApi recipeApi;

    @Inject
    public RecipeRemoteDataSource(Retrofit retrofit) {
        recipeApi = retrofit.create(RecipeApi.class);
    }

    @Override
    public List<Recipe> getAllData() {
        try {
            return recipeApi.getAll().execute().body();
        } catch (IOException e) {
            logger.debug("", e);

            throw new DataSourceException("getAllData", e);
        }
    }

    @Override
    public Observable<List<Recipe>> getAllDataAsObservable() {
        return Observable.fromCallable(this::getAllData);
    }

    interface RecipeApi {

        @GET("/topher/2017/May/59121517_baking/baking.json")
        Call<List<Recipe>> getAll();
    }
}
