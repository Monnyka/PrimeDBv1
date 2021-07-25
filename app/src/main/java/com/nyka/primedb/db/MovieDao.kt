package com.nyka.primedb.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nyka.primedb.data.model.TrendingMovie
import retrofit2.http.DELETE

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(trendingMovie: TrendingMovie): Long

    @Query("SELECT * FROM trendingMovie")
    fun getAllMovie(): LiveData<List<TrendingMovie>>

    @Delete
    suspend fun delete(trendingMovie: TrendingMovie)

}
