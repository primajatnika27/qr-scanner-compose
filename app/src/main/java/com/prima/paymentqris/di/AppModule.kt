package com.prima.paymentqris.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.prima.paymentqris.data.data_sources.local.TransactionDB
import com.prima.paymentqris.data.repository_impl.BarcodeScannerRepositoryImpl
import com.prima.paymentqris.data.repository_impl.TransactionRepositoryImpl
import com.prima.paymentqris.domain.repository.BarcodeScannerRepository
import com.prima.paymentqris.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(app: Application):Context{
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun providesQuestionsCacheDB(app: Application): TransactionDB {
        return Room.databaseBuilder(
            app,
            TransactionDB::class.java,
            TransactionDB::DATABASE_NAME.toString()
        ).build()
    }

    @Provides
    @Singleton
    fun providesTransactionRepository(
        db: TransactionDB
    ): TransactionRepository = TransactionRepositoryImpl(db.transactionDao)

    @Provides
    @Singleton
    fun providesBarcodeScannerRepository(
        scanner: GmsBarcodeScanner
    ): BarcodeScannerRepository = BarcodeScannerRepositoryImpl(scanner)

    @Provides
    fun provideBarCodeScanner(context: Context,options: GmsBarcodeScannerOptions):GmsBarcodeScanner{
        return GmsBarcodeScanning.getClient(context, options)
    }

    @Provides
    fun provideBarCodeOptions() : GmsBarcodeScannerOptions{
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }
}