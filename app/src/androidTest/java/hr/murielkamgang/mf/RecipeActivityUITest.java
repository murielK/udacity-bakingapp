package hr.murielkamgang.mf;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hr.murielkamgang.mf.components.Recipe.RecipeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RecipeActivityUITest {

    @Rule
    public ActivityTestRule<RecipeActivity> recipeActivityActivityTestRule = new ActivityTestRule(RecipeActivity.class);

    @Test
    public void clickOnRecyclerViewItem() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfTitleDisplayed() {
        onView(allOf(isAssignableFrom(TextView.class)
                , withParent(isAssignableFrom(Toolbar.class))))
                .check(matches(withText(InstrumentationRegistry.getTargetContext().getResources().getString(R.string.title_recipes))));
    }

}