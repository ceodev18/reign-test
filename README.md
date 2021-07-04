
# Reign Company Test

Introduction
------------

Architecture based on MVVM and Repository patterns. Implemented
Architecture principles follow Google recommended [Guide to app architecture](https://developer.android.com/jetpack/docs/guide).

![1_3u5JnmqONR4UnwRE6tEV3Q](https://user-images.githubusercontent.com/20958764/124402972-363f0800-dcf9-11eb-91f1-7ea0c3650349.png)

A simple app that loads information from an API REST to show one approach to using some of the best practices in Android Development. Including:  
 * ViewModel
 * LiveData
 * Hilt (for dependency injection)
 * Kotlin Coroutines
 * Retrofit
 * Room
 * Navigation
 
 
* Third party
  * [Kotlin Coroutines] for managing background threads with simplified code
     and reducing needs for callbacks.
  * [Dagger 2] A fast dependency injector.
  * [Retrofit 2] A configurable REST client.
  * [OkHttp 3 A type-safe HTTP client.
  * [GSON] A Json - Object converter using reflection.
  * [Timber] A logger.
 
 
 1st Use Case
-----------------------------------
* Open the app and fetch all data
* Swipe left to delete item
* Close app and turn on airplain mode (network of)
* See all availables except deleted

2nd Use Case
-----------------------------------
* Change airplane mode to network of wifi
* Swipe down to refresh
* Close app and turn on airplain mode (network of)
* After Swipe new data will laoded and deleted still not showing

 
