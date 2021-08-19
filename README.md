# Repos App

### **Libraries Used**

* Navigation - to navigation between fragments
* Koin - for dependency injection
* Gson - for json convertation
* Retrofit - as HTTP client
* Glide - for loading image

### **Architecture**

As a skeleton for my app I took MVVM, because it's recommended by Google and works best with Jetpack Components. 
I divided my app to 3 main packages: ***data***, ***domain*** and ***ui***
In data package api interface and data repository located which are used to fetch data from network.
In domain package I hold my models and repository interface
In UI package I have adapter, 2 fragments and a ViewModel
And other 2 packages are ***di*** and ***utils***,  di used to declare modules, utils - to hold helper utils
