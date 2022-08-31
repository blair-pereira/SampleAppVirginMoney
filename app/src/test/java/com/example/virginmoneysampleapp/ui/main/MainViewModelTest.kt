package com.example.virginmoneysampleapp.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.virginmoneysampleapp.model.PeopleModel
import com.example.virginmoneysampleapp.model.PeopleModelItem
import com.example.virginmoneysampleapp.model.RoomModelItem
import com.example.virginmoneysampleapp.repo.Repository
import com.example.virginmoneysampleapp.util.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import retrofit2.Response

internal class MainViewModelTest{

    val dispatcher = TestCoroutineDispatcher()
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `Given People data from response when success Then result is empty`()= runBlocking{
        val response = PeopleModel()
        whenever(repository.getPeople()).thenReturn(Response.success(response))

        viewModel.getData(true)

        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UIState.Success<*>).uiResponse,response)
        }

    }

    @Test
    fun `Given People data from response when error then return error`()= runBlocking{
        whenever(repository.getPeople()).thenReturn(Response.error(404,"Error ".toResponseBody()))
        viewModel.getData(true)
        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UIState.Fail).error,"")
        }

    }

    @Test
    fun `Given People data from response When success Then return all data`()= runBlocking{
        val people = arrayListOf<PeopleModelItem>(
            PeopleModelItem(avatar = "", createdAt = "",
        email = "", favouriteColor = "", firstName = "", id = "", jobTitle = "", lastName = "",
        latitude = 234.56, longitude = 123.55, phone = ""))

        Mockito.`when`(repository.getPeople()).thenReturn(Response.success(people))
        viewModel.getData(true)
        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UIState.Success<*>).uiResponse,people)
        }

    }

    @Test
    fun `Given Room data from response When success Then return all data`()= runBlocking{
        val rooms = arrayListOf<RoomModelItem>(
            RoomModelItem(created_at = "", id = "", is_occupied = true, max_occupancy = 23, name = ""))

        Mockito.`when`(repository.getRooms()).thenReturn(Response.success(rooms))
        viewModel.getData(true)
        viewModel.data.asLiveData().observeForever{
            assertEquals((it as UIState.Success<*>).uiResponse,rooms)
        }

    }
}