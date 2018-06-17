package hr.murielkamgang.mf;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.murielkamgang.mf.components.detail.RecipeDetailActivity;

import static android.support.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailActivityUITest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> recipeDetailActivityActivityTestRule = new ActivityTestRule<RecipeDetailActivity>(RecipeDetailActivity.class);

    @Test
    public void isTabDisplayed() {
        onView(ViewMatchers.withId(R.id.viewPage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(ViewMatchers.withId(R.id.tabLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(ViewMatchers.withId(R.id.tabLayout)).check(ViewAssertions.matches(ViewMatchers.hasChildCount(2)));
    }

}
