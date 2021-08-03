package com.nyka.primedb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertPopularMovie(popularMovie: PopularMovie): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTrendingMovie(trendingMovie: TrendingMovie)

    @Query("SELECT * FROM popularMovie")
    fun getAllPopular(): LiveData<List<PopularMovie>>

    @Query("SELECT * FROM trendingMovie")
    fun getAllTrendingMovie(): LiveData<List<TrendingMovie>>

    @Delete
    suspend fun delete(popularMovie: PopularMovie)

    @Delete
    suspend fun deleteTrendingMovie(trendingMovie: TrendingMovie)

}
