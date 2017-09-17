package com.noonight.pc.schedule.schedules

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

/**
 * Created by PC on 9/17/2017.
 */
open class RxBaseFragment : Fragment() {
    protected var subscription = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscription = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        if (!subscription.isUnsubscribed) {
            subscription.unsubscribe()
        }
        subscription.clear()
    }
}