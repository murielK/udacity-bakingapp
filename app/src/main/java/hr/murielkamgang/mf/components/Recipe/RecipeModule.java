package hr.murielkamgang.mf.components.Recipe;

import com.squareup.picasso.Picasso;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.mf.components.di.ActivityScoped;
import hr.murielkamgang.mf.components.di.FragmentScoped;
import hr.murielkamgang.mf.data.model.receipe.Recipe;
import hr.murielkamgang.mf.data.source.Repository;
import hr.murielkamgang.mf.data.source.base.BaseKVH;
import hr.murielkamgang.mf.data.source.receipe.RecipeLocalDataSource;
import hr.murielkamgang.mf.data.source.receipe.RecipeRemoteDataSource;
import hr.murielkamgang.mf.data.source.receipe.RecipeRepository;
import retrofit2.Retrofit;

@Module
public abstract class RecipeModule {

    @ActivityScoped
    @Provides
    static RecipeLocalDataSource provideLocalSource() {
        return new RecipeLocalDataSource();
    }

    @ActivityScoped
    @Provides
    static RecipeRemoteDataSource provideRemoteSource(Retrofit retrofit) {
        return new RecipeRemoteDataSource(retrofit);
    }

    @ActivityScoped
    @Provides
    static RecipeAdapter provideAdapter(Picasso picasso) {
        return new RecipeAdapter(picasso);
    }

    @ContributesAndroidInjector
    @FragmentScoped
    abstract RecipeFragment provideRecipeFragment();

    @ActivityScoped
    @Binds
    abstract Repository<Recipe, BaseKVH> provideRepository(RecipeRepository recipeRepository);

    @ActivityScoped
    @Binds
    abstract RecipeContract.Presenter providePresenter(RecipePresenter recipePresenter);
}
