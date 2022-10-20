package dev.dbuzin.android.core.coroutines

import kotlinx.coroutines.Job

class CompositeJob {

    private val hashMap = hashMapOf<Int, Job>()

    fun add(job: Job) {
        hashMap[job.hashCode()] = job
    }

    fun cancel(job: Job) {
        job.cancel()
        hashMap.remove(job.hashCode())
    }

    fun cancelAll() {
        hashMap.forEach {
            it.value.cancel()
        }
        hashMap.clear()
    }
}