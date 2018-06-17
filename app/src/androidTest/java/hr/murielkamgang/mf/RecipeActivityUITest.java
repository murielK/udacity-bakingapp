package hr.murielkamgang.mf;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.murielkamgang.mf.components.Recipe.RecipeActivity;

import static android.support.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeActivityUITest {

    @Rule
    public ActivityTestRule<RecipeActivity> recipeActivityActivityTestRule = new ActivityTestRule(RecipeActivity.class);

    @Test
    public void clickOnRecyclerViewItem() {
        onView(ViewMatchers.withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        onView(ViewMatchers.withId(R.id.tabLayout))
                .check(ViewAssertions.matches(ViewMatchers.hasChildCount(2)));
    }

}