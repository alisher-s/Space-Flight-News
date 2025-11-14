package com.example.epam_project

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.recyclerview.widget.RecyclerView

@RunWith(AndroidJUnit4::class)
@LargeTest
class ArticleListFragmentTest {

    @Test
    fun testArticleListFragment() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))

        Espresso.onView(withId(R.id.swipeRefresh))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}
