package hr.murielkamgang.mf.components.detail;

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
public abstract class RecipeDetailModule {

    @ActivityScoped
    @Provides
    static int provideRecipeId(RecipeDetailActivity recipeDetailActivity) {
        return recipeDetailActivity.getIntent().getIntExtra(RecipeDetailActivity.EXTRA_RECIPE_ID_KEY, -1);
    }

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

    @ContributesAndroidInjector
    @FragmentScoped
    abstract RecipeDetailFragment provideRecipeDetailFragment();

    @ActivityScoped
    @Binds
    abstract RecipeDetailContract.Presenter provideRecipeDetailPresenter(RecipeDetailPresenter recipeDetailPresenter);

    @ActivityScoped
    @Binds
    abstract Repository<Recipe, BaseKVH> provideRepository(RecipeRepository recipeRepository);

    @ContributesAndroidInjector
    @FragmentScoped
    abstract IngredientsFragment provideIngredientFragment();

    @ActivityScoped
    @Binds
    abstract IngredientContract.Presenter provideIngredientsPresenter(IngredientPresenter ingredientPresenter);

    @ContributesAndroidInjector
    @FragmentScoped
    abstract StepFragment provideStepFragment();

    @ActivityScoped
    @Binds
    abstract StepContract.Presenter provideStepPresenter(StepPresenter stepPresenter);

}
