package hr.murielkamgang.mf.components.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import hr.murielkamgang.mf.components.Recipe.RecipeActivity;
import hr.murielkamgang.mf.components.Recipe.RecipeModule;
import hr.murielkamgang.mf.components.detail.RecipeDetailActivity;
import hr.murielkamgang.mf.components.detail.RecipeDetailModule;
import hr.murielkamgang.mf.components.detail.stepdetail.StepDetailActivity;
import hr.murielkamgang.mf.components.detail.stepdetail.StepDetailActivityModule;

/**
 * Created by muriel on 9/20/17.
 */

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = RecipeModule.class)
    @ActivityScoped
    abstract RecipeActivity provideRecipeActiviy();

    @ContributesAndroidInjector(modules = RecipeDetailModule.class)
    @ActivityScoped
    abstract RecipeDetailActivity provideRecipeDetailsActivity();

    @ContributesAndroidInjector(modules = StepDetailActivityModule.class)
    @ActivityScoped
    abstract StepDetailActivity provideStepDetailActivity();
}