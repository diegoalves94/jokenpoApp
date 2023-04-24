package com.dgm.jokenpoApp.observers

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class CustomObserver : DefaultLifecycleObserver, LifecycleEventObserver {

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d("LifeCycle", "Observer $owner - onResume")
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Log.d("LifeCycle", "Observer $source - Event $event")
    }
}