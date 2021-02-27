package com.eduramza.groovytdd

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test

class PlaylistDetailFeature: BaseUITest() {
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayPlaylistNameAndDetails(){

        onView(allOf(withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlists_list), 0))))
            .perform(click())

        Thread.sleep(3000)

        onView(allOf(withId(R.id.tv_detail_name)))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))
        onView(allOf(withId(R.id.tv_details_details)))
            .check(matches(isDisplayed()))
    }
}