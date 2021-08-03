package com.nyka.primedb.db

import android.content.Context
import androidx.room.*
import com.nyka.primedb.model.PopularMovie
import com.nyka.primedb.model.TrendingMovie

@Database(
    entities = [PopularMovie::class, TrendingMovie::class],
    version = 1
)
@TypeConverters(Converters::class, TrendingConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            "movie_db.db"
        ).build()
    }
}