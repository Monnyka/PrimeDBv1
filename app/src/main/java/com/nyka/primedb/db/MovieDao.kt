package com.nyka.primedb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTrendingMovie(trendingMovie: TrendingMovie)
    @Query("SELECT * FROM trendingMovie")
    fun getAllTrendingMovie(): LiveData<List<TrendingMovie>>
    @Delete
    suspend fun deleteTrendingMovie(trendingMovie: TrendingMovie)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPopularMovie(popularMovie: PopularMovie): Long
    @Query("SELECT * FROM popularMovie")
    fun getAllPopular(): LiveData<List<PopularMovie>>
    @Delete
    suspend fun deletePopularMovie(popularMovie: PopularMovie)


}
