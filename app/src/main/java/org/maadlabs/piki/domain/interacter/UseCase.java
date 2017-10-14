package org.maadlabs.piki.domain.interacter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by brainfreak on 10/12/17.
 */

public abstract class UseCase<T> {

    protected abstract Observable<T> buildObservable();

    DisposableObserver<T> mDisposableObserver;

    public void execute(DisposableObserver<T> observer) {

        mDisposableObserver = observer;
        buildObservable().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void removeObserver() {
        if (!mDisposableObserver.isDisposed())
            mDisposableObserver.dispose();
    }


}
