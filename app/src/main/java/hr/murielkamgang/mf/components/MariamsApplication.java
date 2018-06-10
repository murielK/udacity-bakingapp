package hr.murielkamgang.mf.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import hr.murielkamgang.mf.components.di.AppComponent;
import hr.murielkamgang.mf.components.di.DaggerAppComponent;
import hr.murielkamgang.mf.data.source.MigrationHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MariamsApplication extends DaggerApplication {

    private final Logger logger = LoggerFactory.getLogger(MariamsApplication.class);

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        final AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(throwable -> logger.error("Probably an undeliverableException: {}", throwable));

        Realm.init(this);

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .name("xmdb")
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .migration(new MigrationHelper())
                .build());
    }
}
